package garden.ephemeral.glyphplay.search2

import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import garden.ephemeral.glyphplay.unicode.UnicodePropertyValue
import unicode.UnicodeProperty

/**
 * Holder for properties for a single code point.
 * When initially constructed, only the minimal properties are initialised.
 * When the first property is fetched via [get], the map is lazily populated.
 *
 * @property codePoint the code point.
 */
class CodePointProperties private constructor(
    private val codePoint: Int,
    internal val storage: Map<UnicodeProperty<*>, UnicodePropertyValue<*>>,
) {
    /**
     * Convenience method to treat this object itself as a map.
     *
     * @param property the property to get.
     * @return the property value.
     */
    // Trusting the way we populated the map in the first place.
    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(property: UnicodeProperty<T>) = storage[property] as UnicodePropertyValue<T>

    // We could provide convenience getters too, but it's going to get messy fast. Example:
    // val isAlphabetic: Boolean get() = properties.get(UnicodeProperties.Booleans.ALPHABETIC).value as Boolean

    companion object {
        /**
         * Looks up properties for the given code point.
         *
         * @param codePoint the code point to look up.
         * @return the code point properties.
         */
        fun ofCodePoint(codePoint: Int): CodePointProperties {
            val storage = UnicodeProperties.all()
                .map { property -> property to property.valueForCodePoint(codePoint) }
                .toMap()
            return CodePointProperties(codePoint, storage)
        }
    }
}
