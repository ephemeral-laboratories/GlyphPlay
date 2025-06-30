package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.parseUPlusCodePoint
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.document.SortedNumericDocValuesField
import org.apache.lucene.queryparser.classic.ParseException
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.Query
import search.LuceneFields

object QueryParserHelper {
    private val charsToEscapeRegex = Regex("""(/)""")

    private class CustomQueryParser(defaultField: String, analyzer: Analyzer) : QueryParser(defaultField, analyzer) {
        init {
            // We want queries like '1/3' to become phrase queries, not weak boolean queries
            splitOnWhitespace = true
            autoGeneratePhraseQueries = true
        }

        override fun parse(query: String): Query {
            // Deliberately dodging some Lucene query features for now
            val saferQuery = charsToEscapeRegex.replace(query) { escape(it.groupValues[1]) }
            return super.parse(saferQuery)
        }

        private fun createCodePointQuery(codePoint: CodePoint): Query {
            return SortedNumericDocValuesField.newSlowExactQuery(LuceneFields.CODE_POINT, codePoint.value.toLong())
        }

        override fun newFieldQuery(analyzer: Analyzer, field: String?, queryText: String, quoted: Boolean): Query? {
            // Checking this here instead of in the other `createFieldQuery` method gives us the
            // intercept point before `autoGeneratePhraseQueries` is checked. Letting the base class
            // check that first results in them calling us with `quoted = true`, but we specifically
            // wanted to be able to quote these queries to avoid the special handling.
            if (!quoted && field == "all-text") {
                // Special bypass if the user types in a U+ sequence directly
                if (queryText.startsWith("U+")) {
                    if (queryText.length !in 6..10) {
                        throw ParseException("Invalid U+ sequence: $queryText")
                    }
                    try {
                        return createCodePointQuery(queryText.parseUPlusCodePoint())
                    } catch (_: NumberFormatException) {
                        throw ParseException("Invalid U+ sequence: $queryText")
                    }
                }
                // Special bypass if the term was exactly one character
                if (queryText.codePointCount(0, queryText.length) == 1) {
                    return createCodePointQuery(queryText.firstToCodePoint())
                }
            }

            return super.newFieldQuery(analyzer, field, queryText, quoted)
        }
    }

    private val queryParser = CustomQueryParser(defaultField = LuceneFields.ALL_TEXT, analyzer = Analyzers.English)

    internal fun parseQuery(text: String) = queryParser.parse(text)
}
