package garden.ephemeral.glyphplay.unicode

import androidx.compose.runtime.Composable
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.NonNls
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

/**
 * Class representing a single property code points can have.
 *
 * @property displayNameResource a function generating the property's name.
 * @property propertyValueGetter a function taking the code point and ICU4j's value, and giving back a property of
 *           the expected type.
 * @property propertyValueDescriber a function taking the property value and returning a human-readable
 *           description.
 * @param <T> the type of the property.
 */
class CodePointProperty<T>(
    val displayNameResource: StringResource,
    val propertyValueGetter: (codePoint: CodePoint) -> T?,
    val propertyValueDescriber: @Composable (value: T) -> String,
) {
    /**
     * Gets the value for a code point.
     *
     * @param codePoint the code point to get the property for.
     * @return the value.
     */
    fun valueForCodePoint(codePoint: CodePoint) = propertyValueGetter(codePoint)

    /**
     * Get the value for a code point, wrapped up in a structure which hopefully retains
     * the generics information, which should allow the caller to get a description of the
     * property value without jumping through hoops to work around generics issues.
     *
     * @param codePoint the code point to get the property for.
     * @return the wrapped value.
     */
    fun wrappedValueForCodePoint(codePoint: CodePoint) = CodePointPropertyValue(
        value = valueForCodePoint(codePoint),
        property = this,
    )

    @NonNls
    override fun toString() = runBlocking {
        "CodePointProperty(%s)".format(getString(displayNameResource))
    }
}
