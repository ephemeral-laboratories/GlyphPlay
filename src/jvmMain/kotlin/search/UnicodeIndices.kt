package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint
import kotlin.time.measureTime

object UnicodeIndices {
    val instance: SearchableIndex by lazy {
        val index = InMemoryTextIndex()

        val indexTime = measureTime {
            CodePoint.allValidCodePoints.forEach(index::index)
        }

        logger.info("Index time: $indexTime")
        index
    }
}
