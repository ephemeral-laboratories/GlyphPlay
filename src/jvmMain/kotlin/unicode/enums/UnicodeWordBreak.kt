package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeWordBreak(override val icuValue: Int, val typeString: String) : IcuUnicodeValueEnum<UnicodeWordBreak> {
    OTHER(UCharacter.WordBreak.OTHER, "Other"),
    ALETTER(UCharacter.WordBreak.ALETTER, "ALetter"),
    FORMAT(UCharacter.WordBreak.FORMAT, "Format"),
    KATAKANA(UCharacter.WordBreak.KATAKANA, "Katakana"),
    MIDLETTER(UCharacter.WordBreak.MIDLETTER, "MidLetter"),
    MIDNUM(UCharacter.WordBreak.MIDNUM, "MidNum"),
    NUMERIC(UCharacter.WordBreak.NUMERIC, "Numeric"),
    EXTENDNUMLET(UCharacter.WordBreak.EXTENDNUMLET, "ExtendNumLet"),
    CR(UCharacter.WordBreak.CR, "CR"),
    EXTEND(UCharacter.WordBreak.EXTEND, "Extend"),
    LF(UCharacter.WordBreak.LF, "LF"),
    MIDNUMLET(UCharacter.WordBreak.MIDNUMLET, "MidNumLet"),
    NEWLINE(UCharacter.WordBreak.NEWLINE, "Newline"),
    REGIONAL_INDICATOR(UCharacter.WordBreak.REGIONAL_INDICATOR, "RI"),
    HEBREW_LETTER(UCharacter.WordBreak.HEBREW_LETTER, "HL"),
    SINGLE_QUOTE(UCharacter.WordBreak.SINGLE_QUOTE, "SQ"),
    DOUBLE_QUOTE(UCharacter.WordBreak.DOUBLE_QUOTE, "DQ"),
    E_BASE(UCharacter.WordBreak.E_BASE, "EB"),
    E_BASE_GAZ(UCharacter.WordBreak.E_BASE_GAZ, "EBG"),
    E_MODIFIER(UCharacter.WordBreak.E_MODIFIER, "EM"),
    GLUE_AFTER_ZWJ(UCharacter.WordBreak.GLUE_AFTER_ZWJ, "GAZ"),
    ZWJ(UCharacter.WordBreak.ZWJ, "ZWJ"),
    WSEGSPACE(UCharacter.WordBreak.WSEGSPACE, "WSegSpace"),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeWordBreak>(
        enumType = UnicodeWordBreak::class,
    )
}
