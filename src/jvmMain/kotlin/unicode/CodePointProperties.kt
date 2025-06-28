package garden.ephemeral.glyphplay.unicode

/**
 * Holder for properties for a single code point.
 * When initially constructed, only the minimal properties are initialised.
 * When the first property is fetched via [get], the map is lazily populated.
 *
 * @property codePoint the code point.
 */
class CodePointProperties private constructor(
    private val codePoint: CodePoint,
    internal val storage: Map<CodePointProperty<*>, CodePointPropertyValue<*>>,
) {
    /**
     * Convenience method to treat this object itself as a map.
     *
     * @param property the property to get.
     * @return the property value.
     */
    // Trusting the way we populated the map in the first place.
    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(property: CodePointProperty<T>) = storage[property] as CodePointPropertyValue<T>?

    // We could provide convenience getters too, but it's going to get messy fast. Example:
    // val isAlphabetic: Boolean get() = properties.get(UnicodeProperties.Booleans.ALPHABETIC).value as Boolean

    companion object {
        /**
         * Looks up properties for the given code point.
         *
         * @param codePoint the code point to look up the properties for.
         * @return the code point properties.
         */
        fun ofCodePoint(codePoint: CodePoint): CodePointProperties {
            val storage = UnicodeProperties.all()
                .mapNotNull { property ->
                    property.wrappedValueForCodePoint(codePoint)?.let { property to it }
                }
                .toMap()
            return CodePointProperties(codePoint, storage)
        }
    }
}
