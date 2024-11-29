package garden.ephemeral.glyphplay.search

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import garden.ephemeral.glyphplay.unicode.unihan.UnihanData
import garden.ephemeral.glyphplay.unicode.unihan.UnihanDatabaseCategory
import garden.ephemeral.glyphplay.unicode.unihan.UnihanDatabaseProperty
import kotlin.time.measureTime

internal class InMemoryTextIndex : SearchableIndex {
    private val allIndex = mutableMapOf<String, MutableSet<CodePoint>>()
    private val nameIndex = mutableMapOf<String, MutableSet<CodePoint>>()
    private val nameAliasIndex = mutableMapOf<String, MutableSet<CodePoint>>()
    private val readingIndex = mutableMapOf<String, MutableSet<CodePoint>>()

    fun index(codePoint: CodePoint) {
        tokenizeForIndex(UCharacter.getName(codePoint.value)).forEach { term ->
            addToIndex(term, codePoint, allIndex)
            addToIndex(term, codePoint, nameIndex)
        }
        tokenizeForIndex(UCharacter.getNameAlias(codePoint.value)).forEach { term ->
            addToIndex(term, codePoint, allIndex)
            addToIndex(term, codePoint, nameAliasIndex)
        }
        UnihanDatabaseProperty.entries
            .filter { it.category == UnihanDatabaseCategory.Readings }
            .flatMap { tokenizeForIndex(UnihanData.lookup(codePoint, it)) }
            .forEach { term ->
                addToIndex(term, codePoint, allIndex)
                addToIndex(term, codePoint, readingIndex)
            }
    }

    override fun search(query: String): Sequence<CodePoint> {
        var viableResults: Set<CodePoint>? = null

        val searchTime = measureTime {
            for (term in tokenizeQuery(query)) {
                val matches = if (term.startsWith("u+")) {
                    // Special bypass if the user types in a U+ sequence directly
                    setOf(CodePoint(term.substring("u+".length).toInt(16)))
                } else if (term.codePointCount(0, term.length) == 1) {
                    // Special bypass if the term was exactly one character
                    setOf(term.firstToCodePoint())
                } else {
                    allIndex[term] ?: emptySet()
                }

                viableResults = viableResults?.union(matches) ?: matches
            }
        }
        logger.info("Search time: $searchTime")

        // TODO: Should this actually return all when it's null? How do we return all?
        return viableResults?.asSequence() ?: emptySequence()
    }

    // Basic normalisation of the text has to match between indexing and search, otherwise
    // nothing will be found.
    private fun normalise(string: String?) = (string ?: "").lowercase()

    private fun List<String>.removeEmptyStrings() = this.filter(String::isNotEmpty)

    private fun tokenizeForIndex(string: String?): List<String> {
        // Indexing must remove punctuation from the indexed data, because otherwise it will
        // never match with user queries, where we don't want to remove it
        return normalise(string).split(Regex("""\W+""")).removeEmptyStrings()
    }

    private fun tokenizeQuery(string: String?): List<String> {
        // We explicitly keep the punctuation for the user query so that they can type in
        // punctuation characters to search for those directly.
        return normalise(string).split(Regex("""\s+""")).removeEmptyStrings()
    }

    companion object {
        private fun addToIndex(term: String, codePoint: CodePoint, index: MutableMap<String, MutableSet<CodePoint>>) {
            index.computeIfAbsent(term) { mutableSetOf() }.add(codePoint)
        }
    }
}
