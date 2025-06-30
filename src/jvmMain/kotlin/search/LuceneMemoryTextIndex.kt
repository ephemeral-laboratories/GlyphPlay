package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.util.formatAsDataSize
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.search.IndexSearcher
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

    companion object {
        /**
         * Entry point to building a new in-memory text index.
         *
         * @param block a callback block which receives an [IndexingScope] which can be
         *        used to add to the index.
         */
        fun build(block: IndexingScope.() -> Unit): LuceneMemoryTextIndex {
            val directory = ByteBuffersDirectory()

            val indexTime = measureTime {
                IndexWriter(directory, IndexWriterConfig(Analyzers.English)).use { indexWriter ->
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
