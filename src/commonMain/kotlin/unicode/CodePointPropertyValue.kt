package garden.ephemeral.glyphplay.unicode

/**
 * Container class holding the value of a Unicode property for a code point.
 *
 * @property value the raw value.
 * @property property the property which was used to get this value. Can be used to get a
 *           description of the value.
 */
data class CodePointPropertyValue<T>(
    val value: T?,
    val property: CodePointProperty<T>,
)
