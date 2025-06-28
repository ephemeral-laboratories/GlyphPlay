package garden.ephemeral.glyphplay.unicode

import androidx.compose.runtime.Composable

/**
 * Container class holding the value of a Unicode property for a code point.
 *
 * @property value the raw value.
 * @property property the property which was used to get this value. Used to get a
 *           description of the value.
 */
data class CodePointPropertyValue<T>(
    val value: T,
    val property: CodePointProperty<T>,
) {
    @Composable
    fun describeValue() = property.propertyValueDescriber(value)
}
