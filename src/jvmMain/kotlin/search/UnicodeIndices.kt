package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint

object UnicodeIndices {
    val instance: SearchableIndex by lazy {
        LuceneMemoryTextIndex.build {
            CodePoint.allValidCodePoints.forEach { ch -> index(ch) }
        }
    }
}
