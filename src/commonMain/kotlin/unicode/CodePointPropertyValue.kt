package garden.ephemeral.glyphplay.unicode

import androidx.compose.runtime.Composable

/**
 * Container class holding the value of a Unicode property for a code point.
 *
 * @property value the raw value.
 * @property valueDescriber a function which can be called to describe the value.
 */
data class CodePointPropertyValue<T>(
    val value: T,
    val valueDescriber: @Composable (value: T) -> String,
)
