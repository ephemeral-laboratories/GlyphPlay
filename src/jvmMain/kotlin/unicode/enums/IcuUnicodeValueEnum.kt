package garden.ephemeral.glyphplay.unicode.enums

import kotlin.reflect.KClass

/**
 * A Unicode value enum
 */
interface IcuUnicodeValueEnum<T : IcuUnicodeValueEnum<T>> : UnicodeValueEnum<T> {
    /**
     * The ICU value of the enum value.
     */
    val icuValue: Int

    /**
     * Common interface for enums of ICU property values.
     *
     * @param <T> the type of the enum value.
     */
    interface Companion<T : IcuUnicodeValueEnum<T>> : UnicodeValueEnum.Companion<T> {
        /**
         * Looks up an enum value from its ICU value.
         *
         * @param icuValue the ICU value.
         * @return the enum value.
         */
        fun ofIcuValue(icuValue: Int): T
    }

    open class CompanionImpl<T : IcuUnicodeValueEnum<T>>(enumType: KClass<T>) :
        UnicodeValueEnum.CompanionImpl<T>(enumType), Companion<T> {

        override fun ofIcuValue(icuValue: Int): T = entries
            .find { entry -> entry.icuValue == icuValue }
            ?: throw IllegalArgumentException("Unknown ICU value for enum ${enumType.simpleName}: $icuValue")
    }
}
