package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter

enum class UnicodeBidiPairedBracketType(override val icuValue: Int) : UnicodeValueEnum<UnicodeBidiPairedBracketType> {
    NONE(UCharacter.BidiPairedBracketType.NONE),
    OPEN(UCharacter.BidiPairedBracketType.OPEN),
    CLOSE(UCharacter.BidiPairedBracketType.CLOSE),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodeBidiPairedBracketType>(
        enumType = UnicodeBidiPairedBracketType::class
    )
}
