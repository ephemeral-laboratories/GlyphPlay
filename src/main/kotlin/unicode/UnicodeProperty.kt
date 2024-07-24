package unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.prettyPrintName
import garden.ephemeral.glyphplay.unicode.UnicodePropertyValue

/**
 * Key representing a single Unicode property.
 *
 * @property icuValue the value as given in ICU4j.
 * @property propertyGetter a lambda taking the code point and ICU4j's value, and giving back a property of
 *           the expected type.
 * @property propertyValueDescriber a lambda taking the property value and returning a human-readable
 *           description.
 * @param <T> the type of the property.
 */
class UnicodeProperty<T>(
    val icuValue: Int,
    val propertyGetter: (codePoint: Int, icuValue: Int) -> T,
    val propertyValueDescriber: (value: T, icuValue: Int) -> String,
) {
    val longName: String get() = UCharacter.getPropertyName(icuValue, UProperty.NameChoice.LONG).prettyPrintName()

    /**
     * Convenience method to get the value for a code point.
     *
     * @param codePoint the code point to get the property for.
     * @return the value.
     */
    fun valueForCodePoint(codePoint: Int): UnicodePropertyValue<T> {
        val value = propertyGetter(codePoint, icuValue)
        val description = propertyValueDescriber(value, icuValue)
        return UnicodePropertyValue(value, description)
    }

    override fun toString() = "UnicodeProperty(0x%x, %s)".format(icuValue, longName)
}
