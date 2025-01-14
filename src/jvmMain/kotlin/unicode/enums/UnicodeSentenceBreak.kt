package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeSentenceBreak(override val icuValue: Int, val typeString: String) :
    IcuUnicodeValueEnum<UnicodeSentenceBreak> {

    OTHER(UCharacter.SentenceBreak.OTHER, "Other"),
    ATERM(UCharacter.SentenceBreak.ATERM, "ATerm"),
    CLOSE(UCharacter.SentenceBreak.CLOSE, "Close"),
    FORMAT(UCharacter.SentenceBreak.FORMAT, "Format"),
    LOWER(UCharacter.SentenceBreak.LOWER, "Lower"),
    NUMERIC(UCharacter.SentenceBreak.NUMERIC, "Numeric"),
    OLETTER(UCharacter.SentenceBreak.OLETTER, "OLetter"),
    SEP(UCharacter.SentenceBreak.SEP, "Sep"),
    SP(UCharacter.SentenceBreak.SP, "Sp"),
    STERM(UCharacter.SentenceBreak.STERM, "STerm"),
    UPPER(UCharacter.SentenceBreak.UPPER, "Upper"),
    CR(UCharacter.SentenceBreak.CR, "CR"),
    EXTEND(UCharacter.SentenceBreak.EXTEND, "Extend"),
    LF(UCharacter.SentenceBreak.LF, "LF"),
    SCONTINUE(UCharacter.SentenceBreak.SCONTINUE, "SContinue"),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeSentenceBreak>(
        enumType = UnicodeSentenceBreak::class,
    )
}