package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeVerticalOrientation(override val icuValue: Int) :
    IcuUnicodeValueEnum<UnicodeVerticalOrientation> {

    ROTATED(UCharacter.VerticalOrientation.ROTATED),
    TRANSFORMED_ROTATED(UCharacter.VerticalOrientation.TRANSFORMED_ROTATED),
    TRANSFORMED_UPRIGHT(UCharacter.VerticalOrientation.TRANSFORMED_UPRIGHT),
    UPRIGHT(UCharacter.VerticalOrientation.UPRIGHT),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeVerticalOrientation>(
        enumType = UnicodeVerticalOrientation::class,
    )
}
