package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

/**
 * Enumeration of values for the East_Asian_Width property.
 *
 * @property icuValue the value used by ICU4J
 * @property shortName the short name used in the Unicode Character Database.
 */
enum class UnicodeEastAsianWidth(override val icuValue: Int, val shortName: String) : IcuUnicodeValueEnum<UnicodeEastAsianWidth> {
    NEUTRAL(UCharacter.EastAsianWidth.NEUTRAL, "N"),
    AMBIGUOUS(UCharacter.EastAsianWidth.AMBIGUOUS, "A"),
    HALFWIDTH(UCharacter.EastAsianWidth.HALFWIDTH, "H"),
    FULLWIDTH(UCharacter.EastAsianWidth.FULLWIDTH, "F"),
    NARROW(UCharacter.EastAsianWidth.NARROW, "Na"),
    WIDE(UCharacter.EastAsianWidth.WIDE, "W"),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeEastAsianWidth>(
        enumType = UnicodeEastAsianWidth::class,
    ) {
        /**
         * Looks up the enum value by the short name used in Unicode.
         *
         * @param shortName the short name to look for.
         * @return the enum value.
         */
        fun forShortName(shortName: String) =
            UnicodeEastAsianWidth.Companion.entries.first { it.shortName == shortName }
    }
}
