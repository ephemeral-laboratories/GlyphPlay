package garden.ephemeral.glyphplay

import com.ibm.icu.lang.UCharacter

/**
 * More cut down version of [CodePointDescription] for the times when you only need
 * the minimal information.
 */
open class MinimalCodePointDescription(val codePoint: Int) {
    val uPlusForm = codePoint.toUPlusString()
    val stringForm = String(codePoints = intArrayOf(codePoint), offset = 0, length = 1)
    val name = formatCodePointName(codePoint)

    companion object {
        fun of(codePoint: Int) = MinimalCodePointDescription(codePoint)

        private fun formatCodePointName(codePoint: Int): String {
            return UCharacter.getName(codePoint)?.prettyPrintName()
                ?: "(Name Missing)"
        }
    }
}
