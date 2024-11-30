package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import garden.ephemeral.glyphplay.util.formatAsDataSize
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.en.EnglishAnalyzer
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.store.ByteBuffersDirectory
import search.IndexingScope
import search.LuceneFields
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

internal class LuceneMemoryTextIndex private constructor(private val indexSearcher: IndexSearcher) : SearchableIndex {
    override fun search(query: String): Sequence<CodePoint> {
        var viableResults: Set<CodePoint>? = null

        val searchTime = measureTime {
            // Currently still tokenising the query ourselves, because ew have two paths here which
            // don't need the text index. If we could convince Lucene to handle those too..?
            // We explicitly keep the punctuation for the user query so that they can type in
            // punctuation characters to search for those directly.
            val queryTerms = query.split(Regex("""\s+""")).filter(String::isNotEmpty)

            for (term in queryTerms) {
                val matches = if (term.startsWith("U+")) {
                    // Special bypass if the user types in a U+ sequence directly
                    setOf(CodePoint(term.substring("U+".length).toInt(16)))
                } else if (term.codePointCount(0, term.length) == 1) {
                    // Special bypass if the term was exactly one character
                    setOf(term.firstToCodePoint())
                } else {
                    val termQuery = queryParser.parse(term)
                    indexSearcher.search(termQuery, CodePointSetCollectorManager())
                }

                viableResults = viableResults?.union(matches) ?: matches
            }
        }
        logger.info("Search time: $searchTime")

        // TODO: Should this actually return all when it's null? How do we return all?
        return viableResults?.asSequence() ?: emptySequence()
    }

    companion object {
        private val analyzer: Analyzer = EnglishAnalyzer()
        private val queryParser = QueryParser(LuceneFields.ALL_TEXT, analyzer)

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
