package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.parseUPlusCodePoint
import garden.ephemeral.glyphplay.util.formatAsDataSize
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.en.EnglishAnalyzer
import org.apache.lucene.document.SortedNumericDocValuesField
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.queryparser.classic.QueryParserBase
import org.apache.lucene.search.BooleanClause
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.Query
import org.apache.lucene.store.ByteBuffersDirectory
import search.IndexingScope
import search.LuceneFields
import kotlin.time.measureTime
import kotlin.time.measureTimedValue


internal class LuceneMemoryTextIndex private constructor(private val indexSearcher: IndexSearcher) : SearchableIndex {
    override fun search(queryString: String): Sequence<CodePoint> {
        val (matches, searchTime) = measureTimedValue {
            val query = QueryParserHelper.parseQuery(queryString)
            indexSearcher.search(query, CodePointSetCollectorManager())
        }
        logger.info("Search time: $searchTime")

        return matches.asSequence()
    }

    object QueryParserHelper {
        class CustomQueryParser : QueryParser(LuceneFields.ALL_TEXT, analyzer) {
            init {
                // We want queries like '1/3' to become phrase queries, not weak boolean queries
                splitOnWhitespace = true
                autoGeneratePhraseQueries = true
            }

            override fun parse(query: String): Query {
                val saferQuery = charsToEscapeRegex.replace(query) { QueryParserBase.escape(it.groupValues[1]) }
                return super.parse(saferQuery)
            }

            private fun createCodePointQuery(codePoint: CodePoint): Query {
                return SortedNumericDocValuesField.newSlowExactQuery(LuceneFields.CODE_POINT, codePoint.value.toLong())
            }

            override fun createFieldQuery(
                analyzer: Analyzer?,
                operator: BooleanClause.Occur?,
                field: String?,
                queryText: String,
                quoted: Boolean,
                phraseSlop: Int
            ) = when {
                // Special bypass if the user types in a U+ sequence directly
                queryText.startsWith("U+") -> createCodePointQuery(queryText.parseUPlusCodePoint())

                // Special bypass if the term was exactly one character
                queryText.codePointCount(0, queryText.length) == 1 ->
                    createCodePointQuery(queryText.firstToCodePoint())

                else -> super.createFieldQuery(analyzer, operator, field, queryText, quoted, phraseSlop)
            }

            companion object {
                private val charsToEscapeRegex = Regex("""([/])""")
            }
        }

        private val queryParser = CustomQueryParser()

        internal fun parseQuery(text: String) = queryParser.parse(text)
    }

    companion object {
        private val analyzer: Analyzer = EnglishAnalyzer()

        /**
         * Entry point to building a new in-memory text index.
         *
         * @param block a callback block which receives an [IndexingScope] which can be
         *        used to add to the index.
         */
        fun build(block: IndexingScope.() -> Unit): LuceneMemoryTextIndex {
            val directory = ByteBuffersDirectory()

            val indexTime = measureTime {
                IndexWriter(directory, IndexWriterConfig(analyzer)).use { indexWriter ->
                    val scope: IndexingScope = object : IndexingScope {
                        override fun index(codePoint: CodePoint) {
                            val document = LuceneFields.buildDocument(codePoint)
                            indexWriter.addDocument(document)
                        }
                    }
                    scope.block()
                }
            }

            val formattedSize = directory.listAll().sumOf { name -> directory.fileLength(name) }.formatAsDataSize()
            logger.info("Index time: $indexTime (estimated memory usage: $formattedSize)")

            val (indexSearcher, openTime) = measureTimedValue {
                IndexSearcher(DirectoryReader.open(directory))
            }

            logger.info("Index open time: $openTime")

            return LuceneMemoryTextIndex(indexSearcher)
        }
    }
}
