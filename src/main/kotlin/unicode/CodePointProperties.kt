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
    val map = UnicodeProperties.all()
        .map { property -> property to property.valueForCodePoint(codePoint) }
        .toMap()

    // We could provide convenience getters too, but it's going to get messy fast. Example:
    // val isAlphabetic: Boolean get() = properties.get(UnicodeProperties.Booleans.ALPHABETIC).value as Boolean

    companion object {
        fun ofCodePoint(codePoint: Int) = CodePointProperties(codePoint)
    }
}

fun main() {
    val map = CodePointProperties.ofCodePoint("ば".codePointAt(0)).map
    println(map[UnicodeProperties.Ints.CANONICAL_COMBINING_CLASS]!!.description)
}
