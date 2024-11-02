package garden.ephemeral.glyphplay.search

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.unicode.CodePointDescription
import kotlin.time.measureTime

fun main() {
    val index = InMemoryTextIndex()

    val indexTime = measureTime {
        (UCharacter.MIN_CODE_POINT..UCharacter.MAX_CODE_POINT)
            .asSequence()
            .filter { codePoint -> UCharacter.isDefined(codePoint) }
            .forEach { codePoint -> index.index(codePoint) }
    }

    println("Index time: $indexTime")

    println("Results:")
    val searchTime = measureTime {
        index.search("smiling").forEach { result ->
            val description = CodePointDescription.ofCodePoint(result)
            println("  ${description.stringForm} ${description.uPlusForm} ${description.name}")
        }
    }
    println("Search time: $searchTime")
}
