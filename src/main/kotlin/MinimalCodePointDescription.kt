package garden.ephemeral.glyphplay

import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import garden.ephemeral.glyphplay.unicode.enums.UnicodeCharacterCategory

/**
 * More cut down version of [CodePointDescription] for the times when you only need
 * the minimal information.
 */
open class MinimalCodePointDescription(val codePoint: Int) {
    val uPlusForm = codePoint.toUPlusString()
    val stringForm = codePoint.codePointToString()

    val name = UnicodeProperties.Strings.NAME.valueForCodePoint(codePoint).description

    /**
     * Derived value of string form for presentation in UI.
     * Use this when you don't want the string to mangle some other text, if the value happens to be
     * something like a combining mark.
     */
    val stringFormForUI
        get() =
            when (UnicodeProperties.Ints.GENERAL_CATEGORY.valueForCodePoint(codePoint).value) {
                UnicodeCharacterCategory.NON_SPACING_MARK,
                UnicodeCharacterCategory.ENCLOSING_MARK,
                UnicodeCharacterCategory.COMBINING_SPACING_MARK,
                -> "$DOTTED_CIRCLE$stringForm"

                else -> stringForm
            }

    companion object {
        // https://en.wikipedia.org/wiki/Dotted_circle
        private const val DOTTED_CIRCLE = "◌"

        private fun Int.toUPlusString() = "U+%04X".format(this)

        fun ofCodePoint(codePoint: Int) = MinimalCodePointDescription(codePoint)
    }
}
