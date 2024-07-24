package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.prettyPrintName

enum class UnicodeSentenceBreak(val icuValue: Int, val typeString: String) {
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

    val longName: String get() = name.prettyPrintName()

    companion object {
        fun ofIcuValue(icuValue: Int) = entries.find { entry -> entry.icuValue == icuValue }
            ?: throw IllegalArgumentException("Unknown sentence break ID: $icuValue")

        fun ofCodePoint(codePoint: Int) = ofIcuValue(UCharacter.getIntPropertyValue(codePoint, UProperty.SENTENCE_BREAK))
    }
}