package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeBidiPairedBracketType(override val icuValue: Int) : IcuUnicodeValueEnum<UnicodeBidiPairedBracketType> {
    NONE(UCharacter.BidiPairedBracketType.NONE),
    OPEN(UCharacter.BidiPairedBracketType.OPEN),
    CLOSE(UCharacter.BidiPairedBracketType.CLOSE),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeBidiPairedBracketType>(
        enumType = UnicodeBidiPairedBracketType::class
    )
}
