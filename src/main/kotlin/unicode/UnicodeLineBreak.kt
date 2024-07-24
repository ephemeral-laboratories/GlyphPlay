package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.prettyPrintName

enum class UnicodeLineBreak(val icuValue: Int, val typeString: String) {
    UNKNOWN(UCharacter.LineBreak.UNKNOWN, "UNKNOWN"),
    AMBIGUOUS(UCharacter.LineBreak.AMBIGUOUS, "AMBIGUOUS"),
    ALPHABETIC(UCharacter.LineBreak.ALPHABETIC, "ALPHABETIC"),
    BREAK_BOTH(UCharacter.LineBreak.BREAK_BOTH, "BREAK_BOTH"),
    BREAK_AFTER(UCharacter.LineBreak.BREAK_AFTER, "BREAK_AFTER"),
    BREAK_BEFORE(UCharacter.LineBreak.BREAK_BEFORE, "BREAK_BEFORE"),
    MANDATORY_BREAK(UCharacter.LineBreak.MANDATORY_BREAK, "MANDATORY_BREAK"),
    CONTINGENT_BREAK(UCharacter.LineBreak.CONTINGENT_BREAK, "CONTINGENT_BREAK"),
    CLOSE_PUNCTUATION(UCharacter.LineBreak.CLOSE_PUNCTUATION, "CLOSE_PUNCTUATION"),
    COMBINING_MARK(UCharacter.LineBreak.COMBINING_MARK, "COMBINING_MARK"),
    CARRIAGE_RETURN(UCharacter.LineBreak.CARRIAGE_RETURN, "CARRIAGE_RETURN"),
    EXCLAMATION(UCharacter.LineBreak.EXCLAMATION, "EXCLAMATION"),
    GLUE(UCharacter.LineBreak.GLUE, "GLUE"),
    HYPHEN(UCharacter.LineBreak.HYPHEN, "HYPHEN"),
    IDEOGRAPHIC(UCharacter.LineBreak.IDEOGRAPHIC, "IDEOGRAPHIC"),
    INSEPARABLE(UCharacter.LineBreak.INSEPARABLE, "INSEPARABLE"),
    INFIX_NUMERIC(UCharacter.LineBreak.INFIX_NUMERIC, "INFIX_NUMERIC"),
    LINE_FEED(UCharacter.LineBreak.LINE_FEED, "LINE_FEED"),
    NONSTARTER(UCharacter.LineBreak.NONSTARTER, "NONSTARTER"),
    NUMERIC(UCharacter.LineBreak.NUMERIC, "NUMERIC"),
    OPEN_PUNCTUATION(UCharacter.LineBreak.OPEN_PUNCTUATION, "OPEN_PUNCTUATION"),
    POSTFIX_NUMERIC(UCharacter.LineBreak.POSTFIX_NUMERIC, "POSTFIX_NUMERIC"),
    PREFIX_NUMERIC(UCharacter.LineBreak.PREFIX_NUMERIC, "PREFIX_NUMERIC"),
    QUOTATION(UCharacter.LineBreak.QUOTATION, "QUOTATION"),
    COMPLEX_CONTEXT(UCharacter.LineBreak.COMPLEX_CONTEXT, "COMPLEX_CONTEXT"),
    SURROGATE(UCharacter.LineBreak.SURROGATE, "SURROGATE"),
    SPACE(UCharacter.LineBreak.SPACE, "SPACE"),
    BREAK_SYMBOLS(UCharacter.LineBreak.BREAK_SYMBOLS, "BREAK_SYMBOLS"),
    ZWSPACE(UCharacter.LineBreak.ZWSPACE, "ZWSPACE"),
    NEXT_LINE(UCharacter.LineBreak.NEXT_LINE, "NL"),
    WORD_JOINER(UCharacter.LineBreak.WORD_JOINER, "WJ"),
    H2(UCharacter.LineBreak.H2, "H2"),
    H3(UCharacter.LineBreak.H3, "H3"),
    JL(UCharacter.LineBreak.JL, "JL"),
    JT(UCharacter.LineBreak.JT, "JT"),
    CLOSE_PARENTHESIS(UCharacter.LineBreak.CLOSE_PARENTHESIS, "CP"),
    CONDITIONAL_JAPANESE_STARTER(UCharacter.LineBreak.CONDITIONAL_JAPANESE_STARTER, "CJ"),
    HEBREW_LETTER(UCharacter.LineBreak.HEBREW_LETTER, "HL"),
    REGIONAL_INDICATOR(UCharacter.LineBreak.REGIONAL_INDICATOR, "RI"),
    E_BASE(UCharacter.LineBreak.E_BASE, "EB"),
    E_MODIFIER(UCharacter.LineBreak.E_MODIFIER, "EM"),
    ZWJ(UCharacter.LineBreak.ZWJ, "ZWJ"),
    AKSARA(UCharacter.LineBreak.AKSARA, "AK"),
    AKSARA_PREBASE(UCharacter.LineBreak.AKSARA_PREBASE, "AP"),
    AKSARA_START(UCharacter.LineBreak.AKSARA_START, "AS"),
    VIRAMA_FINAL(UCharacter.LineBreak.VIRAMA_FINAL, "VF"),
    VIRAMA(UCharacter.LineBreak.VIRAMA, "VI"),
    JV(UCharacter.LineBreak.JV, "JV"),
    ;

    val longName: String get() = name.prettyPrintName()

    companion object {
        fun ofIcuValue(icuValue: Int) = entries.find { entry -> entry.icuValue == icuValue }
            ?: throw IllegalArgumentException("Unknown line break ID: $icuValue")

        fun ofCodePoint(codePoint: Int) = ofIcuValue(UCharacter.getIntPropertyValue(codePoint, UProperty.LINE_BREAK))
    }
}