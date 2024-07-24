package garden.ephemeral.glyphplay.search2

import garden.ephemeral.glyphplay.unicode.UnicodeProperties

/**
 * Holder for properties for a single code point.
 *
 * @property codePoint the code point.
 */
class CodePointProperties private constructor(private val codePoint: Int) {
    /**
     * A map of all property values.
     */
    val properties = UnicodeProperties.all()
        .map { property ->
            property to property.getValue(codePoint) }
        .toMap()

    // We could provide convenience getters too, but it's going to get messy fast. Example:
    // val isAlphabetic: Boolean get() = properties.get(UnicodeProperties.Booleans.IS_ALPHABETIC).value as Boolean

    companion object {
        fun of(codePoint: Int) = CodePointProperties(codePoint)
    }
}
