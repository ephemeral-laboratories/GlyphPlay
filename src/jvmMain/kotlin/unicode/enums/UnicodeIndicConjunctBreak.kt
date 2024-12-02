package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeIndicConjunctBreak(override val icuValue: Int, val icuValueObject: UCharacter.IndicConjunctBreak) :
    IcuUnicodeValueEnum<UnicodeIndicConjunctBreak> {

    NONE(0, UCharacter.IndicConjunctBreak.NONE),
    CONSONANT(1, UCharacter.IndicConjunctBreak.CONSONANT),
    EXTEND(2, UCharacter.IndicConjunctBreak.EXTEND),
    LINKER(3, UCharacter.IndicConjunctBreak.LINKER),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeIndicConjunctBreak>(
        enumType = UnicodeIndicConjunctBreak::class,
    )
}
