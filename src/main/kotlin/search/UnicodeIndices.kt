package garden.ephemeral.glyphplay.search

import com.ibm.icu.lang.UCharacter
import kotlin.time.measureTime

object UnicodeIndices {
    val instance: SearchableIndex by lazy {
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

        logger.info("Index time: $indexTime")
        index
    }
}
