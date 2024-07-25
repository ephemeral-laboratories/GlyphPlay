package unicode

import garden.ephemeral.glyphplay.unicode.UnicodePropertyValue

/**
 * Key representing a single Unicode property.
 *
 * @property icuValue the value as given in ICU4j.
 * @property propertyValueGetter a lambda taking the code point and ICU4j's value, and giving back a property of
 *           the expected type.
 * @property propertyValueDescriber a lambda taking the property value and returning a human-readable
 *           description.
 * @param <T> the type of the property.
 */
class UnicodeProperty<T>(
    val propertyNameGetter: () -> String,
    val propertyValueGetter: (codePoint: Int) -> T,
    val propertyValueDescriber: (value: T) -> String,
) {
    val longName: String by lazy(propertyNameGetter)

    /**
     * Convenience method to get the value for a code point.
     *
     * @param codePoint the code point to get the property for.
     * @return the value.
     */
    fun valueForCodePoint(codePoint: Int): UnicodePropertyValue<T> {
        val value = propertyValueGetter(codePoint)
        val description = propertyValueDescriber(value)
        return UnicodePropertyValue(value, description)
    }

    override fun toString() = "UnicodeProperty(%s)".format(longName)
}
