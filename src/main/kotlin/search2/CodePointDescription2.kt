package garden.ephemeral.glyphplay.search2

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.util.ULocale
import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.codePointToString
import garden.ephemeral.glyphplay.prettyPrintName
import java.util.BitSet

class CodePointDescription2 {

    class Property<T>(
        val icuValue: Int,
        val propertyGetter: (codePoint: Int, icuValue: Int) -> T,
        val propertyValueDescriber: (value: T, icuValue: Int) -> String,
    )

    object UnicodeProperties {
        private val BOOLEAN_GETTER = { codePoint: Int, type: Int ->
            UCharacter.getIntPropertyValue(codePoint, type) != 0
        }
        private val BOOLEAN_DESCRIBER = { value: Boolean, _: Int ->
            value.toString()
        }
        val IS_ALPHABETIC = Property(UProperty.ALPHABETIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ASCII_HEX_DIGIT = Property(UProperty.ASCII_HEX_DIGIT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_BIDI_CONTROL = Property(UProperty.BIDI_CONTROL, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_BIDI_MIRRORED = Property(UProperty.BIDI_MIRRORED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DASH = Property(UProperty.DASH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DEFAULT_IGNORABLE_CODE_POINT = Property(UProperty.DEFAULT_IGNORABLE_CODE_POINT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DEPRECATED = Property(UProperty.DEPRECATED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DIACRITIC = Property(UProperty.DIACRITIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EXTENDER = Property(UProperty.EXTENDER, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_FULL_COMPOSITION_EXCLUSION = Property(UProperty.FULL_COMPOSITION_EXCLUSION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_GRAPHEME_BASE = Property(UProperty.GRAPHEME_BASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_GRAPHEME_EXTEND = Property(UProperty.GRAPHEME_EXTEND, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_GRAPHEME_LINK = Property(UProperty.GRAPHEME_LINK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_HEX_DIGIT = Property(UProperty.HEX_DIGIT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_HYPHEN = Property(UProperty.HYPHEN, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_CONTINUE = Property(UProperty.ID_CONTINUE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_START = Property(UProperty.ID_START, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDEOGRAPHIC = Property(UProperty.IDEOGRAPHIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDS_BINARY_OPERATOR = Property(UProperty.IDS_BINARY_OPERATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDS_TRINARY_OPERATOR = Property(UProperty.IDS_TRINARY_OPERATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_JOIN_CONTROL = Property(UProperty.JOIN_CONTROL, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_LOGICAL_ORDER_EXCEPTION = Property(UProperty.LOGICAL_ORDER_EXCEPTION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_LOWERCASE = Property(UProperty.LOWERCASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_MATH = Property(UProperty.MATH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NON_CHARACTER_CODE_POINT = Property(UProperty.NONCHARACTER_CODE_POINT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_QUOTATION_MARK = Property(UProperty.QUOTATION_MARK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RADICAL = Property(UProperty.RADICAL, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_SOFT_DOTTED = Property(UProperty.SOFT_DOTTED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_TERMINAL_PUNCTUATION = Property(UProperty.TERMINAL_PUNCTUATION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_UNIFIED_IDEOGRAPH = Property(UProperty.UNIFIED_IDEOGRAPH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_UPPERCASE = Property(UProperty.UPPERCASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_WHITE_SPACE = Property(UProperty.WHITE_SPACE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_XID_CONTINUE = Property(UProperty.XID_CONTINUE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_XID_START = Property(UProperty.XID_START, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_CASE_SENSITIVE = Property(UProperty.CASE_SENSITIVE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_S_TERM = Property(UProperty.S_TERM, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_VARIATION_SELECTOR = Property(UProperty.VARIATION_SELECTOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFD_INERT = Property(UProperty.NFD_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFKD_INERT = Property(UProperty.NFKD_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFC_INERT = Property(UProperty.NFC_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFKC_INERT = Property(UProperty.NFKC_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_SEGMENT_STARTER = Property(UProperty.SEGMENT_STARTER, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_PATTERN_SYNTAX = Property(UProperty.PATTERN_SYNTAX, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_PATTERN_WHITE_SPACE = Property(UProperty.PATTERN_WHITE_SPACE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_ALPHANUM = Property(UProperty.POSIX_ALNUM, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_BLANK = Property(UProperty.POSIX_BLANK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_GRAPH = Property(UProperty.POSIX_GRAPH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_PRINT = Property(UProperty.POSIX_PRINT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_XDIGIT = Property(UProperty.POSIX_XDIGIT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_CASED = Property(UProperty.CASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_CASE_IGNORABLE = Property(UProperty.CASE_IGNORABLE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_LOWERCASED = Property(UProperty.CHANGES_WHEN_LOWERCASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_UPPERCASED = Property(UProperty.CHANGES_WHEN_UPPERCASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_TITLECASED = Property(UProperty.CHANGES_WHEN_TITLECASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_CASEFOLDED = Property(UProperty.CHANGES_WHEN_CASEFOLDED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_CASEMAPPED = Property(UProperty.CHANGES_WHEN_CASEMAPPED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_NFKC_CASEFOLDED = Property(UProperty.CHANGES_WHEN_NFKC_CASEFOLDED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI = Property(UProperty.EMOJI, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_PRESENTATION = Property(UProperty.EMOJI_PRESENTATION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_MODIFIER = Property(UProperty.EMOJI_MODIFIER, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_MODIFIER_BASE = Property(UProperty.EMOJI_MODIFIER_BASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_COMPONENT = Property(UProperty.EMOJI_COMPONENT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_REGIONAL_INDICATOR = Property(UProperty.REGIONAL_INDICATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_PREPENDED_CONCATENATION_MARK = Property(UProperty.PREPENDED_CONCATENATION_MARK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EXTENDED_PICTOGRAPHIC = Property(UProperty.EXTENDED_PICTOGRAPHIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_BASIC_EMOJI = Property(UProperty.BASIC_EMOJI, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_KEYCAP_SEQUENCE = Property(UProperty.EMOJI_KEYCAP_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_MODIFIER_SEQUENCE = Property(UProperty.RGI_EMOJI_MODIFIER_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_FLAG_SEQUENCE = Property(UProperty.RGI_EMOJI_FLAG_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_TAG_SEQUENCE = Property(UProperty.RGI_EMOJI_TAG_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_ZWJ_SEQUENCE = Property(UProperty.RGI_EMOJI_ZWJ_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI = Property(UProperty.RGI_EMOJI, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDS_UNARY_OPERATOR = Property(UProperty.IDS_UNARY_OPERATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_COMPAT_MATH_START = Property(UProperty.ID_COMPAT_MATH_START, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_COMPAT_MATH_CONTINUE = Property(UProperty.ID_COMPAT_MATH_CONTINUE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)

        private val INT_GETTER = UCharacter::getIntPropertyValue
        private val INT_DESCRIBER = { value: Int, icuValue: Int ->
            UCharacter.getPropertyValueName(icuValue, value, UProperty.NameChoice.LONG).prettyPrintName()
        }
        val BIDI_CLASS = Property(UProperty.BIDI_CLASS, INT_GETTER, INT_DESCRIBER)
        val BLOCK = Property(UProperty.BLOCK, INT_GETTER, INT_DESCRIBER)
        val CANONICAL_COMBINING_CLASS = Property(UProperty.CANONICAL_COMBINING_CLASS, INT_GETTER, INT_DESCRIBER)
        val DECOMPOSITION_TYPE = Property(UProperty.DECOMPOSITION_TYPE, INT_GETTER, INT_DESCRIBER)
        val EAST_ASIAN_WIDTH = Property(UProperty.EAST_ASIAN_WIDTH, INT_GETTER, INT_DESCRIBER)
        val GENERAL_CATEGORY = Property(UProperty.GENERAL_CATEGORY, INT_GETTER, INT_DESCRIBER)
        val JOINING_GROUP = Property(UProperty.JOINING_GROUP, INT_GETTER, INT_DESCRIBER)
        val JOINING_TYPE = Property(UProperty.JOINING_TYPE, INT_GETTER, INT_DESCRIBER)
        val LINE_BREAK = Property(UProperty.LINE_BREAK, INT_GETTER, INT_DESCRIBER)
        val NUMERIC_TYPE = Property(UProperty.NUMERIC_TYPE, INT_GETTER, INT_DESCRIBER)
        val SCRIPT = Property(UProperty.SCRIPT, INT_GETTER, INT_DESCRIBER)
        val HANGUL_SYLLABLE_TYPE = Property(UProperty.HANGUL_SYLLABLE_TYPE, INT_GETTER, INT_DESCRIBER)
        val NFD_QUICK_CHECK = Property(UProperty.NFD_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val NFKD_QUICK_CHECK = Property(UProperty.NFKD_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val NFC_QUICK_CHECK = Property(UProperty.NFC_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val NFKC_QUICK_CHECK = Property(UProperty.NFKC_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val LEAD_CANONICAL_COMBINING_CLASS = Property(UProperty.LEAD_CANONICAL_COMBINING_CLASS, INT_GETTER, INT_DESCRIBER)
        val TRAIL_CANONICAL_COMBINING_CLASS = Property(UProperty.TRAIL_CANONICAL_COMBINING_CLASS, INT_GETTER, INT_DESCRIBER)
        val GRAPHEME_CLUSTER_BREAK = Property(UProperty.GRAPHEME_CLUSTER_BREAK, INT_GETTER, INT_DESCRIBER)
        val SENTENCE_BREAK = Property(UProperty.SENTENCE_BREAK, INT_GETTER, INT_DESCRIBER)
        val WORD_BREAK = Property(UProperty.WORD_BREAK, INT_GETTER, INT_DESCRIBER)
        val BIDI_PAIRED_BRACKET_TYPE = Property(UProperty.BIDI_PAIRED_BRACKET_TYPE, INT_GETTER, INT_DESCRIBER)
        val INDIC_POSITIONAL_CATEGORY = Property(UProperty.INDIC_POSITIONAL_CATEGORY, INT_GETTER, INT_DESCRIBER)
        val INDIC_SYLLABIC_CATEGORY = Property(UProperty.INDIC_SYLLABIC_CATEGORY, INT_GETTER, INT_DESCRIBER)
        val VERTICAL_ORIENTATION = Property(UProperty.VERTICAL_ORIENTATION, INT_GETTER, INT_DESCRIBER)

        val GENERAL_CATEGORY_MASK = Property<BitSet>(
            UProperty.GENERAL_CATEGORY_MASK,
            { _, _ -> TODO("No idea how to implement this; ICU doesn't say what it corresponds to exactly") },
            { _, _ -> TODO() }
        )

        private val DOUBLE_DESCRIBER = { value: Double, _: Int -> value.toString() }
        val NUMERIC_VALUE = Property(
            UProperty.NUMERIC_VALUE,
            { cp, _ -> UCharacter.getUnicodeNumericValue(cp) },
            DOUBLE_DESCRIBER
        )

        val STRING_DESCRIBER = { value: String, _: Int -> value }

        val AGE = Property<VersionInfo>(UProperty.AGE, { cp, _ -> UCharacter.getAge(cp) }, { v, _ -> v.toString() })

        val BIDI_MIRRORING_GLYPH = Property(
            UProperty.BIDI_MIRRORING_GLYPH,
            { cp, _ ->  UCharacter.getMirror(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val CASE_FOLDING = Property<String>(
            UProperty.CASE_FOLDING,
            { cp, _ -> UCharacter.foldCase(cp.codePointToString(), true) },
            STRING_DESCRIBER
        )

        val SIMPLE_CASE_FOLDING = Property(
            UProperty.SIMPLE_CASE_FOLDING,
            { cp, _ -> UCharacter.foldCase(cp, true).codePointToString() },
            STRING_DESCRIBER
        )

        // ISO_COMMENT is deprecated

        val LOWERCASE_MAPPING = Property(
            UProperty.LOWERCASE_MAPPING,
            { cp, _ -> UCharacter.toLowerCase(ULocale.ROOT, cp.codePointToString()) },
            STRING_DESCRIBER
        )

        val SIMPLE_LOWERCASE_MAPPING = Property(
            UProperty.SIMPLE_LOWERCASE_MAPPING,
            { cp, _ -> UCharacter.toLowerCase(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val NAME = Property(
            UProperty.NAME,
            { cp, _ -> UCharacter.getName(cp) },
            STRING_DESCRIBER
        )

        val UPPERCASE_MAPPING = Property(
            UProperty.UPPERCASE_MAPPING,
            { cp, _ -> UCharacter.toUpperCase(ULocale.ROOT, cp.codePointToString()) },
            STRING_DESCRIBER
        )

        val SIMPLE_UPPERCASE_MAPPING = Property(
            UProperty.SIMPLE_UPPERCASE_MAPPING,
            { cp, _ -> UCharacter.toUpperCase(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val TITLECASE_MAPPING = Property(
            UProperty.TITLECASE_MAPPING,
            { cp, _ -> UCharacter.toTitleCase(ULocale.ROOT, cp.codePointToString(), BreakIterator.getWordInstance(ULocale.ROOT)) },
            STRING_DESCRIBER
        )

        val SIMPLE_TITLECASE_MAPPING = Property(
            UProperty.SIMPLE_TITLECASE_MAPPING,
            { cp, _ -> UCharacter.toTitleCase(cp).codePointToString() },
            STRING_DESCRIBER
        )

        // UNICODE_1_NAME is deprecated

        val BIDI_PAIRED_BRACKET = Property(
            UProperty.BIDI_PAIRED_BRACKET,
            { cp, _ -> UCharacter.getBidiPairedBracket(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val SCRIPT_EXTENSIONS = Property(
            UProperty.SCRIPT_EXTENSIONS,
            { cp, _ ->
                // Relevant methods:
                // UScript.hasScript()
//                 UScript.getScriptExtensions()
//                UCharacter.getPropertyValueName()
//                UCharacter.getStringPropertyValue()
                TODO("How to implement? And what property type to return?")
            },
            STRING_DESCRIBER
        )


//        fun allProperties(): Sequence<Property<*>> {
//            return this::class.members.asSequence()
//                .filter { m -> m.returnType == Property::class }
//                .map { m -> m.call()!! as Property<*> }
//        }
    }

}

fun main() {
}
