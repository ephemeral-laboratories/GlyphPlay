package garden.ephemeral.glyphplay.unicode

/**
 * Container class holding the value of a Unicode property for a code point.
 *
 * @property value the raw value.
 * @property description a formatted description of the value.
 */
data class UnicodePropertyValue<T>(
    val value: T,
    val description: String,
)
