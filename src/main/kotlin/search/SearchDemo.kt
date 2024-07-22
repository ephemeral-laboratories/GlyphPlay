package garden.ephemeral.glyphplay.search

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.toUPlusString
import kotlin.time.measureTime

fun main() {
    val index = InMemoryTextIndex()

    val indexTime = measureTime {
        (UCharacter.MIN_CODE_POINT..UCharacter.MAX_CODE_POINT)
            .asSequence()
            .filter { UCharacter.isValidCodePoint(it) }
            .filter { UCharacter.isDefined(it) }
            .forEach { codePoint ->
                index.index(codePoint)
            }
    }

    println("Index time: $indexTime")

    println("Results:")
    val searchTime = measureTime {
        index.search("smiling").forEach { result ->
            println("  ${String(intArrayOf(result), 0, 1)} ${result.toUPlusString()} ${UCharacter.getName(result)}")
        }
    }
    println("Search time: $searchTime")
}
