package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeEastAsianWidth(override val icuValue: Int) : IcuUnicodeValueEnum<UnicodeEastAsianWidth> {
    NEUTRAL(UCharacter.EastAsianWidth.NEUTRAL),
    AMBIGUOUS(UCharacter.EastAsianWidth.AMBIGUOUS),
    HALFWIDTH(UCharacter.EastAsianWidth.HALFWIDTH),
    FULLWIDTH(UCharacter.EastAsianWidth.FULLWIDTH),
    NARROW(UCharacter.EastAsianWidth.NARROW),
    WIDE(UCharacter.EastAsianWidth.WIDE),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeEastAsianWidth>(
        enumType = UnicodeEastAsianWidth::class,
    )
}
