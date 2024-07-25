package garden.ephemeral.glyphplay.unicode.enums

import garden.ephemeral.glyphplay.prettyPrintName
import kotlin.reflect.KClass

/**
 * Common interface for enum classes which implement a more type-safe alternative
 * to ICU4J's property types, which tend to just be ints.
 *
 * @param <T> the type of the enum value.
 */
interface UnicodeValueEnum<T : UnicodeValueEnum<T>> {
    /**
     * The name of the enum. Implementing classes usually get this for free by being enums.
     */
    val name: String

    /**
     * A longer name for the property, presented to users.
     *
     * XXX: Not localisation-friendly, so should eventually replace with some kind of `LocalisedString`.
     *
     * This default implementation just pretty-prints the enum value.
     */
    val longName: String get() = name.prettyPrintName()

    /**
     * Common interface for enums of ICU property values.
     *
     * @param <T> the type of the enum value.
     */
    interface Companion<T : UnicodeValueEnum<T>> {
        val entries: List<T>
    }

    /**
     * Common interface for the companion object of ICU property value enums.
     *
     * @param enumType the type of the corresponding enum value.
     * @param <T> the type of the corresponding enum value.
     */
    open class CompanionImpl<T : UnicodeValueEnum<T>>(
        protected val enumType: KClass<T>
    ) : Companion<T> {
        // Can't use the nicer Kotlin API to get the enum values, so we're stuck with Java's one, I think.
        override val entries by lazy { enumType.java.enumConstants.toList() }

    }
}
