package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeIndicPositionalCategory(override val icuValue: Int) :
    IcuUnicodeValueEnum<UnicodeIndicPositionalCategory> {

    NA(UCharacter.IndicPositionalCategory.NA),
    BOTTOM(UCharacter.IndicPositionalCategory.BOTTOM),
    BOTTOM_AND_LEFT(UCharacter.IndicPositionalCategory.BOTTOM_AND_LEFT),
    BOTTOM_AND_RIGHT(UCharacter.IndicPositionalCategory.BOTTOM_AND_RIGHT),
    LEFT(UCharacter.IndicPositionalCategory.LEFT),
    LEFT_AND_RIGHT(UCharacter.IndicPositionalCategory.LEFT_AND_RIGHT),
    OVERSTRUCK(UCharacter.IndicPositionalCategory.OVERSTRUCK),
    RIGHT(UCharacter.IndicPositionalCategory.RIGHT),
    TOP(UCharacter.IndicPositionalCategory.TOP),
    TOP_AND_BOTTOM(UCharacter.IndicPositionalCategory.TOP_AND_BOTTOM),
    TOP_AND_BOTTOM_AND_RIGHT(UCharacter.IndicPositionalCategory.TOP_AND_BOTTOM_AND_RIGHT),
    TOP_AND_LEFT(UCharacter.IndicPositionalCategory.TOP_AND_LEFT),
    TOP_AND_LEFT_AND_RIGHT(UCharacter.IndicPositionalCategory.TOP_AND_LEFT_AND_RIGHT),
    TOP_AND_RIGHT(UCharacter.IndicPositionalCategory.TOP_AND_RIGHT),
    VISUAL_ORDER_LEFT(UCharacter.IndicPositionalCategory.VISUAL_ORDER_LEFT),
    TOP_AND_BOTTOM_AND_LEFT(UCharacter.IndicPositionalCategory.TOP_AND_BOTTOM_AND_LEFT),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeIndicPositionalCategory>(
        enumType = UnicodeIndicPositionalCategory::class,
    )
}
