package garden.ephemeral.glyphplay.unicode

import garden.ephemeral.glyphplay.unicode.enums.UnicodeCharacterCategory

class CodePointDescription private constructor(val codePoint: CodePoint) {
    /**
     * Map containing all property values. Or, at least, all the properties which are
     * retrievable from ICU4J using property IDs.
     */
    private val allProperties by lazy { CodePointProperties.ofCodePoint(codePoint) }

    operator fun <T> get(x: CodePointProperty<T>) = allProperties[x]

    val name = UnicodeProperties.Strings.NAME.valueForCodePoint(codePoint) ?: ""

    val uPlusForm = codePoint.toUPlusString()
    val stringForm = codePoint.toString()

    /**
     * Derived value of string form for presentation in UI.
     * Use this when you don't want the string to mangle some other text, if the value happens to be
     * something like a combining mark.
     */
    val stringFormForUI
        get() =
            when (UnicodeProperties.Ints.GENERAL_CATEGORY.valueForCodePoint(codePoint)) {
                UnicodeCharacterCategory.NON_SPACING_MARK,
                UnicodeCharacterCategory.ENCLOSING_MARK,
                UnicodeCharacterCategory.COMBINING_SPACING_MARK,
                -> "$DOTTED_CIRCLE$stringForm"

                else -> stringForm
            }

    companion object {
        // https://en.wikipedia.org/wiki/Dotted_circle
        private const val DOTTED_CIRCLE = "◌"

        fun ofCodePoint(codePoint: CodePoint) = CodePointDescription(codePoint)
    }
}
