package garden.ephemeral.glyphplay.unicode

/**
 * Class representing a single property code points can have.
 *
 * @property propertyNameGetter a function generating the property's name.
 * @property propertyValueGetter a function taking the code point and ICU4j's value, and giving back a property of
 *           the expected type.
 * @property propertyValueDescriber a function taking the property value and returning a human-readable
 *           description.
 * @param <T> the type of the property.
 */
class CodePointProperty<T>(
    val propertyNameGetter: () -> String,
    val propertyValueGetter: (codePoint: CodePoint) -> T,
    val propertyValueDescriber: (value: T) -> String,
) {
    val longName: String by lazy(propertyNameGetter)

    /**
     * Convenience method to get the value for a code point.
     *
     * @param codePoint the code point to get the property for.
     * @return the value.
     */
    fun valueForCodePoint(codePoint: CodePoint): CodePointPropertyValue<T> {
        val value = propertyValueGetter(codePoint)
        val description = propertyValueDescriber(value)
        return CodePointPropertyValue(value, description)
    }

    override fun toString() = "CodePointProperty(%s)".format(longName)
}
