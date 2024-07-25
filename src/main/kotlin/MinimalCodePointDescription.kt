package garden.ephemeral.glyphplay

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacterCategory
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.unicode.UnicodeProperties

/**
 * More cut down version of [CodePointDescription] for the times when you only need
 * the minimal information.
 */
open class MinimalCodePointDescription(val codePoint: Int) {
    val uPlusForm = codePoint.toUPlusString()
    val stringForm = codePoint.codePointToString()

    val name = formatCodePointName(codePoint)

    /**
     * Derived value of string form for presentation in UI.
     * Use this when you don't want the string to mangle some other text, if the value happens to be
     * something like a combining mark.
     */
    val stringFormForUI get() =
        when (UCharacter.getIntPropertyValue(codePoint, UProperty.GENERAL_CATEGORY)) {
            UCharacterCategory.NON_SPACING_MARK.toInt(),
            UCharacterCategory.ENCLOSING_MARK.toInt(),
            UCharacterCategory.COMBINING_SPACING_MARK.toInt() -> "$DOTTED_CIRCLE$stringForm"
            else -> stringForm
        }

    companion object {
        // https://en.wikipedia.org/wiki/Dotted_circle
        private const val DOTTED_CIRCLE = "◌"

        fun ofCodePoint(codePoint: Int) = MinimalCodePointDescription(codePoint)

        private fun formatCodePointName(codePoint: Int): String {
            return UnicodeProperties.Strings.NAME.valueForCodePoint(codePoint).value
                ?.prettyPrintName()
                ?: "(Name Missing)"
        }
    }
}
