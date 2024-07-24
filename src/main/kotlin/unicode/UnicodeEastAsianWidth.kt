package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty

enum class UnicodeEastAsianWidth(override val icuValue: Int) : UnicodeValueEnum<UnicodeEastAsianWidth> {
    NEUTRAL(UCharacter.EastAsianWidth.NEUTRAL),
    AMBIGUOUS(UCharacter.EastAsianWidth.AMBIGUOUS),
    HALFWIDTH(UCharacter.EastAsianWidth.HALFWIDTH),
    FULLWIDTH(UCharacter.EastAsianWidth.FULLWIDTH),
    NARROW(UCharacter.EastAsianWidth.NARROW),
    WIDE(UCharacter.EastAsianWidth.WIDE),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodeEastAsianWidth>(
        enumType = UnicodeEastAsianWidth::class,
        icuPropertyId = UProperty.EAST_ASIAN_WIDTH,
    )
}
