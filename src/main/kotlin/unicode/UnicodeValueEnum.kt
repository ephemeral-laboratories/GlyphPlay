package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
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
     * The ICU value of the enum value.
     */
    val icuValue: Int

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

        /**
         * Looks up an enum value from its ICU value.
         *
         * @param icuValue the ICU value.
         * @return the enum value.
         */
        fun ofIcuValue(icuValue: Int): T

        fun ofCodePoint(codePoint: Int): T
    }

    /**
     * Common interface for the companion object of ICU property value enums.
     *
     * @param enumType the type of the corresponding enum value.
     * @param icuPropertyId the int value of the enum value according to ICU.
     * @param <T> the type of the corresponding enum value.
     */
    open class CompanionImpl<T : UnicodeValueEnum<T>>(
        private val enumType: KClass<T>,
        private val icuPropertyId: Int
    ) : Companion<T> {
        // Can't use the nicer Kotlin API to get the enum values, so we're stuck with Java's one, I think.
        override val entries by lazy { enumType.java.enumConstants.toList() }

        override fun ofIcuValue(icuValue: Int): T = entries
            .find { entry -> entry.icuValue == icuValue }
            ?: throw IllegalArgumentException("Unknown ICU value for enum ${enumType::class.simpleName}: $icuValue")

        override fun ofCodePoint(codePoint: Int) =
            ofIcuValue(UCharacter.getIntPropertyValue(codePoint, icuPropertyId))
    }
}
