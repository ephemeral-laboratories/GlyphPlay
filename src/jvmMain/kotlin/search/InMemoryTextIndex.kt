package garden.ephemeral.glyphplay.search

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.unicode.CodePoint
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
        tokenize(UCharacter.getName(codePoint.value)).forEach { term ->
            addToIndex(term, codePoint, allIndex)
            addToIndex(term, codePoint, nameIndex)
        }
        tokenize(UCharacter.getNameAlias(codePoint.value)).forEach { term ->
            addToIndex(term, codePoint, allIndex)
            addToIndex(term, codePoint, nameAliasIndex)
        }
        UnihanDatabaseProperty.entries
            .filter { it.category == UnihanDatabaseCategory.Readings }
            .flatMap { tokenize(UnihanData.lookup(codePoint, it)) }
            .forEach { term ->
                addToIndex(term, codePoint, allIndex)
                addToIndex(term, codePoint, readingIndex)
            }
    }

    override fun search(query: String): Sequence<CodePoint> {
        var viableResults: Set<CodePoint>? = null

        val searchTime = measureTime {
            for (term in tokenize(query)) {
                val matches = if (term.startsWith("u+")) {
                    // Special bypass if the user types in a U+ sequence directly
                    setOf(CodePoint(term.substring("u+".length).toInt(16)))
                } else if (term.codePointCount(0, term.length) == 1) {
                    // Special bypass if the term was exactly one character
                    setOf(CodePoint(term.codePointAt(0)))
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

    private fun tokenize(string: String?): List<String> {
        return (string ?: "").lowercase().split(Regex("""\s+""")).dropWhile { it == "" }
    }

    companion object {
        private fun addToIndex(term: String, codePoint: CodePoint, index: MutableMap<String, MutableSet<CodePoint>>) {
            index.computeIfAbsent(term) { mutableSetOf() }.add(codePoint)
        }
    }
}
