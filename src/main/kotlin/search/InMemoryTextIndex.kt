package garden.ephemeral.glyphplay.search

import com.ibm.icu.lang.UCharacter
import kotlin.time.measureTime

internal class InMemoryTextIndex : SearchableIndex {
    private val allIndex = mutableMapOf<String, MutableSet<Int>>()
    private val nameIndex = mutableMapOf<String, MutableSet<Int>>()
    private val nameAliasIndex = mutableMapOf<String, MutableSet<Int>>()

    fun index(codePoint: Int) {
        tokenize(UCharacter.getName(codePoint)).forEach { term ->
            addToIndex(term, codePoint, allIndex)
            addToIndex(term, codePoint, nameIndex)
        }
        tokenize(UCharacter.getNameAlias(codePoint)).forEach { term ->
            addToIndex(term, codePoint, allIndex)
            addToIndex(term, codePoint, nameAliasIndex)
        }
    }

    override fun search(query: String): Sequence<Int> {
        var viableResults: Set<Int>? = null

        val searchTime = measureTime {
            for (term in tokenize(query)) {
                val matches = if (term.startsWith("u+")) {
                    // Special bypass if the user types in a U+ sequence directly
                    setOf(term.substring("u+".length).toInt(16))
                } else if (term.codePointCount(0, term.length) == 1) {
                    // Special bypass if the term was exactly one character
                    setOf(term.codePointAt(0))
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
        return (string ?: "").lowercase().split(' ').dropWhile { it == "" }
    }

    companion object {
        private fun addToIndex(term: String, codePoint: Int, index: MutableMap<String, MutableSet<Int>>) {
            index.computeIfAbsent(term) { mutableSetOf() }.add(codePoint)
        }
    }
}
