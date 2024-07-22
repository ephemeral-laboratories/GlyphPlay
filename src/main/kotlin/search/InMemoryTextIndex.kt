package garden.ephemeral.glyphplay.search

import com.ibm.icu.lang.UCharacter
import kotlin.time.measureTime

internal class InMemoryTextIndex : SearchableIndex {
    private val allIndex = mutableMapOf<String, MutableSet<Int>>()
    private val nameIndex = mutableMapOf<String, MutableSet<Int>>()
    private val nameAliasIndex = mutableMapOf<String, MutableSet<Int>>()

    fun index(codePoint: Int) {
        tokenize(UCharacter.getName(codePoint)).forEach { term ->
            allIndex.computeIfAbsent(term) { mutableSetOf() }.add(codePoint)
            nameIndex.computeIfAbsent(term) { mutableSetOf() }.add(codePoint)
        }
        tokenize(UCharacter.getNameAlias(codePoint)).forEach { term ->
            allIndex.computeIfAbsent(term) { mutableSetOf() }.add(codePoint)
            nameAliasIndex.computeIfAbsent(term) { mutableSetOf() }.add(codePoint)
        }
    }

    override fun search(query: String): Sequence<Int> {
        var viableResults: Set<Int>? = null

        val searchTime = measureTime {
            for (term in tokenize(query)) {
                val matches = allIndex[term] ?: return emptySequence()
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
}
