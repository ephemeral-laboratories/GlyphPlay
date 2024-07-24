package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacter.IdentifierType
import com.ibm.icu.lang.UCharacterCategory
import com.ibm.icu.lang.UProperty
import com.ibm.icu.lang.UScript
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.util.ULocale
import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.codePointToString
import garden.ephemeral.glyphplay.formatToString
import garden.ephemeral.glyphplay.prettyPrintName
import unicode.UnicodeProperty
import java.util.BitSet
import java.util.EnumSet

object UnicodeProperties {
    interface PropertyCollection<T> {
        fun all(): Sequence<UnicodeProperty<out T>>
    }

    object Booleans : PropertyCollection<Boolean> {
        init {
            @Suppress("DEPRECATION")
            check(UProperty.BINARY_START == 0 && UProperty.BINARY_LIMIT == 75) { "Boolean properties not as expected!" }
        }

        private val BOOLEAN_GETTER = { codePoint: Int, type: Int ->
            UCharacter.getIntPropertyValue(codePoint, type) != 0
        }
        private val BOOLEAN_DESCRIBER = { value: Boolean, _: Int -> if (value) "Yes" else "No" }

        val IS_ALPHABETIC = UnicodeProperty(UProperty.ALPHABETIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ASCII_HEX_DIGIT = UnicodeProperty(UProperty.ASCII_HEX_DIGIT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_BIDI_CONTROL = UnicodeProperty(UProperty.BIDI_CONTROL, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_BIDI_MIRRORED = UnicodeProperty(UProperty.BIDI_MIRRORED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DASH = UnicodeProperty(UProperty.DASH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DEFAULT_IGNORABLE_CODE_POINT =
            UnicodeProperty(UProperty.DEFAULT_IGNORABLE_CODE_POINT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DEPRECATED = UnicodeProperty(UProperty.DEPRECATED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_DIACRITIC = UnicodeProperty(UProperty.DIACRITIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EXTENDER = UnicodeProperty(UProperty.EXTENDER, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_FULL_COMPOSITION_EXCLUSION =
            UnicodeProperty(UProperty.FULL_COMPOSITION_EXCLUSION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_GRAPHEME_BASE = UnicodeProperty(UProperty.GRAPHEME_BASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_GRAPHEME_EXTEND = UnicodeProperty(UProperty.GRAPHEME_EXTEND, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_GRAPHEME_LINK = UnicodeProperty(UProperty.GRAPHEME_LINK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_HEX_DIGIT = UnicodeProperty(UProperty.HEX_DIGIT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_HYPHEN = UnicodeProperty(UProperty.HYPHEN, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_CONTINUE = UnicodeProperty(UProperty.ID_CONTINUE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_START = UnicodeProperty(UProperty.ID_START, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDEOGRAPHIC = UnicodeProperty(UProperty.IDEOGRAPHIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDS_BINARY_OPERATOR = UnicodeProperty(UProperty.IDS_BINARY_OPERATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDS_TRINARY_OPERATOR = UnicodeProperty(UProperty.IDS_TRINARY_OPERATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_JOIN_CONTROL = UnicodeProperty(UProperty.JOIN_CONTROL, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_LOGICAL_ORDER_EXCEPTION =
            UnicodeProperty(UProperty.LOGICAL_ORDER_EXCEPTION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_LOWERCASE = UnicodeProperty(UProperty.LOWERCASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_MATH = UnicodeProperty(UProperty.MATH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NON_CHARACTER_CODE_POINT =
            UnicodeProperty(UProperty.NONCHARACTER_CODE_POINT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_QUOTATION_MARK = UnicodeProperty(UProperty.QUOTATION_MARK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RADICAL = UnicodeProperty(UProperty.RADICAL, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_SOFT_DOTTED = UnicodeProperty(UProperty.SOFT_DOTTED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_TERMINAL_PUNCTUATION = UnicodeProperty(UProperty.TERMINAL_PUNCTUATION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_UNIFIED_IDEOGRAPH = UnicodeProperty(UProperty.UNIFIED_IDEOGRAPH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_UPPERCASE = UnicodeProperty(UProperty.UPPERCASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_WHITE_SPACE = UnicodeProperty(UProperty.WHITE_SPACE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_XID_CONTINUE = UnicodeProperty(UProperty.XID_CONTINUE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_XID_START = UnicodeProperty(UProperty.XID_START, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_CASE_SENSITIVE = UnicodeProperty(UProperty.CASE_SENSITIVE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_S_TERM = UnicodeProperty(UProperty.S_TERM, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_VARIATION_SELECTOR = UnicodeProperty(UProperty.VARIATION_SELECTOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFD_INERT = UnicodeProperty(UProperty.NFD_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFKD_INERT = UnicodeProperty(UProperty.NFKD_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFC_INERT = UnicodeProperty(UProperty.NFC_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_NFKC_INERT = UnicodeProperty(UProperty.NFKC_INERT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_SEGMENT_STARTER = UnicodeProperty(UProperty.SEGMENT_STARTER, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_PATTERN_SYNTAX = UnicodeProperty(UProperty.PATTERN_SYNTAX, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_PATTERN_WHITE_SPACE = UnicodeProperty(UProperty.PATTERN_WHITE_SPACE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_ALPHANUM = UnicodeProperty(UProperty.POSIX_ALNUM, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_BLANK = UnicodeProperty(UProperty.POSIX_BLANK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_GRAPH = UnicodeProperty(UProperty.POSIX_GRAPH, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_PRINT = UnicodeProperty(UProperty.POSIX_PRINT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_POSIX_XDIGIT = UnicodeProperty(UProperty.POSIX_XDIGIT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_CASED = UnicodeProperty(UProperty.CASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_CASE_IGNORABLE = UnicodeProperty(UProperty.CASE_IGNORABLE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_LOWERCASED =
            UnicodeProperty(UProperty.CHANGES_WHEN_LOWERCASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_UPPERCASED =
            UnicodeProperty(UProperty.CHANGES_WHEN_UPPERCASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_TITLECASED =
            UnicodeProperty(UProperty.CHANGES_WHEN_TITLECASED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_CASEFOLDED =
            UnicodeProperty(UProperty.CHANGES_WHEN_CASEFOLDED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_CASEMAPPED =
            UnicodeProperty(UProperty.CHANGES_WHEN_CASEMAPPED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val CHANGES_WHEN_NFKC_CASEFOLDED =
            UnicodeProperty(UProperty.CHANGES_WHEN_NFKC_CASEFOLDED, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI = UnicodeProperty(UProperty.EMOJI, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_PRESENTATION = UnicodeProperty(UProperty.EMOJI_PRESENTATION, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_MODIFIER = UnicodeProperty(UProperty.EMOJI_MODIFIER, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_MODIFIER_BASE = UnicodeProperty(UProperty.EMOJI_MODIFIER_BASE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_COMPONENT = UnicodeProperty(UProperty.EMOJI_COMPONENT, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_REGIONAL_INDICATOR = UnicodeProperty(UProperty.REGIONAL_INDICATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_PREPENDED_CONCATENATION_MARK =
            UnicodeProperty(UProperty.PREPENDED_CONCATENATION_MARK, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EXTENDED_PICTOGRAPHIC =
            UnicodeProperty(UProperty.EXTENDED_PICTOGRAPHIC, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_BASIC_EMOJI = UnicodeProperty(UProperty.BASIC_EMOJI, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_EMOJI_KEYCAP_SEQUENCE =
            UnicodeProperty(UProperty.EMOJI_KEYCAP_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_MODIFIER_SEQUENCE =
            UnicodeProperty(UProperty.RGI_EMOJI_MODIFIER_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_FLAG_SEQUENCE =
            UnicodeProperty(UProperty.RGI_EMOJI_FLAG_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_TAG_SEQUENCE =
            UnicodeProperty(UProperty.RGI_EMOJI_TAG_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI_ZWJ_SEQUENCE =
            UnicodeProperty(UProperty.RGI_EMOJI_ZWJ_SEQUENCE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_RGI_EMOJI = UnicodeProperty(UProperty.RGI_EMOJI, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_IDS_UNARY_OPERATOR = UnicodeProperty(UProperty.IDS_UNARY_OPERATOR, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_COMPAT_MATH_START = UnicodeProperty(UProperty.ID_COMPAT_MATH_START, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)
        val IS_ID_COMPAT_MATH_CONTINUE =
            UnicodeProperty(UProperty.ID_COMPAT_MATH_CONTINUE, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)

        override fun all(): Sequence<UnicodeProperty<Boolean>> = sequenceOf(
            IS_ALPHABETIC,
            IS_ASCII_HEX_DIGIT,
            IS_BIDI_CONTROL,
            IS_BIDI_MIRRORED,
            IS_DASH,
            IS_DEFAULT_IGNORABLE_CODE_POINT,
            IS_DEPRECATED,
            IS_DIACRITIC,
            IS_EXTENDER,
            IS_FULL_COMPOSITION_EXCLUSION,
            IS_GRAPHEME_BASE,
            IS_GRAPHEME_EXTEND,
            IS_GRAPHEME_LINK,
            IS_HEX_DIGIT,
            IS_HYPHEN,
            IS_ID_CONTINUE,
            IS_ID_START,
            IS_IDEOGRAPHIC,
            IS_IDS_BINARY_OPERATOR,
            IS_IDS_TRINARY_OPERATOR,
            IS_JOIN_CONTROL,
            IS_LOGICAL_ORDER_EXCEPTION,
            IS_LOWERCASE,
            IS_MATH,
            IS_NON_CHARACTER_CODE_POINT,
            IS_QUOTATION_MARK,
            IS_RADICAL,
            IS_SOFT_DOTTED,
            IS_TERMINAL_PUNCTUATION,
            IS_UNIFIED_IDEOGRAPH,
            IS_UPPERCASE,
            IS_WHITE_SPACE,
            IS_XID_CONTINUE,
            IS_XID_START,
            IS_CASE_SENSITIVE,
            IS_S_TERM,
            IS_VARIATION_SELECTOR,
            IS_NFD_INERT,
            IS_NFKD_INERT,
            IS_NFC_INERT,
            IS_NFKC_INERT,
            IS_SEGMENT_STARTER,
            IS_PATTERN_SYNTAX,
            IS_PATTERN_WHITE_SPACE,
            IS_POSIX_ALPHANUM,
            IS_POSIX_BLANK,
            IS_POSIX_GRAPH,
            IS_POSIX_PRINT,
            IS_POSIX_XDIGIT,
            IS_CASED,
            IS_CASE_IGNORABLE,
            CHANGES_WHEN_LOWERCASED,
            CHANGES_WHEN_UPPERCASED,
            CHANGES_WHEN_TITLECASED,
            CHANGES_WHEN_CASEFOLDED,
            CHANGES_WHEN_CASEMAPPED,
            CHANGES_WHEN_NFKC_CASEFOLDED,
            IS_EMOJI,
            IS_EMOJI_PRESENTATION,
            IS_EMOJI_MODIFIER,
            IS_EMOJI_MODIFIER_BASE,
            IS_EMOJI_COMPONENT,
            IS_REGIONAL_INDICATOR,
            IS_PREPENDED_CONCATENATION_MARK,
            IS_EXTENDED_PICTOGRAPHIC,
            IS_BASIC_EMOJI,
            IS_EMOJI_KEYCAP_SEQUENCE,
            IS_RGI_EMOJI_MODIFIER_SEQUENCE,
            IS_RGI_EMOJI_FLAG_SEQUENCE,
            IS_RGI_EMOJI_TAG_SEQUENCE,
            IS_RGI_EMOJI_ZWJ_SEQUENCE,
            IS_RGI_EMOJI,
            IS_IDS_UNARY_OPERATOR,
            IS_ID_COMPAT_MATH_START,
            IS_ID_COMPAT_MATH_CONTINUE,
        )
    }

    object Ints : PropertyCollection<Int> {
        init {
            @Suppress("DEPRECATION")
            check(UProperty.INT_START == 0x1000 && UProperty.INT_LIMIT == 0x101A) { "Int properties not as expected!" }
        }

        private val INT_GETTER = UCharacter::getIntPropertyValue
        private val INT_DESCRIBER = { value: Int, icuValue: Int ->
            UCharacter.getPropertyValueName(icuValue, value, UProperty.NameChoice.LONG).prettyPrintName()
        }

        val BIDI_CLASS = UnicodeProperty(UProperty.BIDI_CLASS, INT_GETTER, INT_DESCRIBER)
        val BLOCK = UnicodeProperty(UProperty.BLOCK, INT_GETTER, INT_DESCRIBER)
        val CANONICAL_COMBINING_CLASS = UnicodeProperty(UProperty.CANONICAL_COMBINING_CLASS, INT_GETTER, INT_DESCRIBER)
        val DECOMPOSITION_TYPE = UnicodeProperty(UProperty.DECOMPOSITION_TYPE, INT_GETTER, INT_DESCRIBER)
        val EAST_ASIAN_WIDTH = UnicodeProperty(UProperty.EAST_ASIAN_WIDTH, INT_GETTER, INT_DESCRIBER)
        val GENERAL_CATEGORY = UnicodeProperty(UProperty.GENERAL_CATEGORY, INT_GETTER, INT_DESCRIBER)
        val JOINING_GROUP = UnicodeProperty(UProperty.JOINING_GROUP, INT_GETTER, INT_DESCRIBER)
        val JOINING_TYPE = UnicodeProperty(UProperty.JOINING_TYPE, INT_GETTER, INT_DESCRIBER)
        val LINE_BREAK = UnicodeProperty(UProperty.LINE_BREAK, INT_GETTER, INT_DESCRIBER)
        val NUMERIC_TYPE = UnicodeProperty(UProperty.NUMERIC_TYPE, INT_GETTER, INT_DESCRIBER)
        val SCRIPT = UnicodeProperty(UProperty.SCRIPT, INT_GETTER, INT_DESCRIBER)
        val HANGUL_SYLLABLE_TYPE = UnicodeProperty(UProperty.HANGUL_SYLLABLE_TYPE, INT_GETTER, INT_DESCRIBER)
        val NFD_QUICK_CHECK = UnicodeProperty(UProperty.NFD_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val NFKD_QUICK_CHECK = UnicodeProperty(UProperty.NFKD_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val NFC_QUICK_CHECK = UnicodeProperty(UProperty.NFC_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val NFKC_QUICK_CHECK = UnicodeProperty(UProperty.NFKC_QUICK_CHECK, INT_GETTER, INT_DESCRIBER)
        val LEAD_CANONICAL_COMBINING_CLASS =
            UnicodeProperty(UProperty.LEAD_CANONICAL_COMBINING_CLASS, INT_GETTER, INT_DESCRIBER)
        val TRAIL_CANONICAL_COMBINING_CLASS =
            UnicodeProperty(UProperty.TRAIL_CANONICAL_COMBINING_CLASS, INT_GETTER, INT_DESCRIBER)
        val GRAPHEME_CLUSTER_BREAK = UnicodeProperty(UProperty.GRAPHEME_CLUSTER_BREAK, INT_GETTER, INT_DESCRIBER)
        val SENTENCE_BREAK = UnicodeProperty(UProperty.SENTENCE_BREAK, INT_GETTER, INT_DESCRIBER)
        val WORD_BREAK = UnicodeProperty(UProperty.WORD_BREAK, INT_GETTER, INT_DESCRIBER)
        val BIDI_PAIRED_BRACKET_TYPE = UnicodeProperty(UProperty.BIDI_PAIRED_BRACKET_TYPE, INT_GETTER, INT_DESCRIBER)
        val INDIC_POSITIONAL_CATEGORY = UnicodeProperty(UProperty.INDIC_POSITIONAL_CATEGORY, INT_GETTER, INT_DESCRIBER)
        val INDIC_SYLLABIC_CATEGORY = UnicodeProperty(UProperty.INDIC_SYLLABIC_CATEGORY, INT_GETTER, INT_DESCRIBER)
        val VERTICAL_ORIENTATION = UnicodeProperty(UProperty.VERTICAL_ORIENTATION, INT_GETTER, INT_DESCRIBER)
        val IDENTIFIER_STATUS = UnicodeProperty(UProperty.IDENTIFIER_STATUS, INT_GETTER, INT_DESCRIBER)

        override fun all(): Sequence<UnicodeProperty<Int>> = sequenceOf(
            BIDI_CLASS,
            BLOCK,
            CANONICAL_COMBINING_CLASS,
            DECOMPOSITION_TYPE,
            EAST_ASIAN_WIDTH,
            GENERAL_CATEGORY,
            JOINING_GROUP,
            JOINING_TYPE,
            LINE_BREAK,
            NUMERIC_TYPE,
            SCRIPT,
            HANGUL_SYLLABLE_TYPE,
            NFD_QUICK_CHECK,
            NFKD_QUICK_CHECK,
            NFC_QUICK_CHECK,
            NFKC_QUICK_CHECK,
            LEAD_CANONICAL_COMBINING_CLASS,
            TRAIL_CANONICAL_COMBINING_CLASS,
            GRAPHEME_CLUSTER_BREAK,
            SENTENCE_BREAK,
            WORD_BREAK,
            BIDI_PAIRED_BRACKET_TYPE,
            INDIC_POSITIONAL_CATEGORY,
            INDIC_SYLLABIC_CATEGORY,
            VERTICAL_ORIENTATION,
            IDENTIFIER_STATUS,
        )
    }

    object Masks : PropertyCollection<Int> {
        init {
            @Suppress("DEPRECATION")
            check(UProperty.MASK_START == 0x2000 && UProperty.MASK_LIMIT == 0x2001) { "Mask properties not as expected!" }
        }

        val GENERAL_CATEGORY_MASK = UnicodeProperty(
            UProperty.GENERAL_CATEGORY_MASK,
            { codePoint, icuValue -> UCharacter.getIntPropertyValue(codePoint, icuValue) },
            { value, icuValue ->
                var temp = value
                val result = mutableListOf<String>()
                while (temp != 0) {
                    val highestBit = temp.takeHighestOneBit()
                    val highestBitValue = temp.countTrailingZeroBits()
                    result += UCharacterCategory.toString(highestBitValue)
                    temp = temp.xor(highestBit)
                }
                result.formatToString()
            }
        )

        override fun all(): Sequence<UnicodeProperty<Int>> = sequenceOf(
            GENERAL_CATEGORY_MASK
        )
    }

    object Doubles : PropertyCollection<Double> {
        init {
            @Suppress("DEPRECATION")
            check(UProperty.DOUBLE_START == 0x3000 && UProperty.DOUBLE_LIMIT == 0x3001) { "Double properties not as expected!" }
        }

        private val DOUBLE_DESCRIBER = { value: Double, _: Int -> value.toString() }

        val NUMERIC_VALUE = UnicodeProperty(
            UProperty.NUMERIC_VALUE,
            { cp, _ -> UCharacter.getUnicodeNumericValue(cp) },
            DOUBLE_DESCRIBER
        )

        override fun all(): Sequence<UnicodeProperty<Double>> = sequenceOf(
            NUMERIC_VALUE
        )
    }

    object Strings : PropertyCollection<Any> {
        init {
            @Suppress("DEPRECATION")
            check(UProperty.STRING_START == 0x4000 && UProperty.STRING_LIMIT == 0x400E) { "String properties not as expected!" }
        }

        val STRING_DESCRIBER = { value: String, _: Int -> value }

        val AGE =
            UnicodeProperty<VersionInfo>(UProperty.AGE, { cp, _ -> UCharacter.getAge(cp) }, { v, _ -> v.toString() })

        val BIDI_MIRRORING_GLYPH = UnicodeProperty(
            UProperty.BIDI_MIRRORING_GLYPH,
            { cp, _ -> UCharacter.getMirror(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val CASE_FOLDING = UnicodeProperty<String>(
            UProperty.CASE_FOLDING,
            { cp, _ -> UCharacter.foldCase(cp.codePointToString(), true) },
            STRING_DESCRIBER
        )

        @Suppress("DEPRECATION")
        @Deprecated("Deprecated since ICU 49")
        val ISO_COMMENT = UnicodeProperty(
            UProperty.ISO_COMMENT,
            { cp, _ ->
                // Documented method is `UCharacter.getISOComment` but this returns null
                ""
            },
            STRING_DESCRIBER
        )

        val LOWERCASE_MAPPING = UnicodeProperty(
            UProperty.LOWERCASE_MAPPING,
            { cp, _ -> UCharacter.toLowerCase(ULocale.ROOT, cp.codePointToString()) },
            STRING_DESCRIBER
        )

        val NAME = UnicodeProperty(
            UProperty.NAME,
            { cp, _ -> UCharacter.getName(cp) },
            STRING_DESCRIBER
        )

        val SIMPLE_CASE_FOLDING = UnicodeProperty(
            UProperty.SIMPLE_CASE_FOLDING,
            { cp, _ -> UCharacter.foldCase(cp, true).codePointToString() },
            STRING_DESCRIBER
        )

        val SIMPLE_LOWERCASE_MAPPING = UnicodeProperty(
            UProperty.SIMPLE_LOWERCASE_MAPPING,
            { cp, _ -> UCharacter.toLowerCase(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val SIMPLE_TITLECASE_MAPPING = UnicodeProperty(
            UProperty.SIMPLE_TITLECASE_MAPPING,
            { cp, _ -> UCharacter.toTitleCase(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val SIMPLE_UPPERCASE_MAPPING = UnicodeProperty(
            UProperty.SIMPLE_UPPERCASE_MAPPING,
            { cp, _ -> UCharacter.toUpperCase(cp).codePointToString() },
            STRING_DESCRIBER
        )

        val TITLECASE_MAPPING = UnicodeProperty(
            UProperty.TITLECASE_MAPPING,
            { cp, _ ->
                UCharacter.toTitleCase(
                    ULocale.ROOT,
                    cp.codePointToString(),
                    BreakIterator.getWordInstance(ULocale.ROOT)
                )
            },
            STRING_DESCRIBER
        )

        @Suppress("DEPRECATION")
        @Deprecated("Deprecated since ICU 49")
        val UNICODE_1_NAME = UnicodeProperty(
            UProperty.UNICODE_1_NAME,
            { cp, _ ->
                // Documented method is `UCharacter.getName1_0` but this returns null
                ""
            },
            STRING_DESCRIBER
        )

        val UPPERCASE_MAPPING = UnicodeProperty(
            UProperty.UPPERCASE_MAPPING,
            { cp, _ -> UCharacter.toUpperCase(ULocale.ROOT, cp.codePointToString()) },
            STRING_DESCRIBER
        )

        val BIDI_PAIRED_BRACKET = UnicodeProperty(
            UProperty.BIDI_PAIRED_BRACKET,
            { cp, _ -> UCharacter.getBidiPairedBracket(cp).codePointToString() },
            STRING_DESCRIBER
        )

        override fun all(): Sequence<UnicodeProperty<out Any>> = sequenceOf(
            AGE,
            BIDI_MIRRORING_GLYPH,
            CASE_FOLDING,
            // ISO_COMMENT is deprecated,
            LOWERCASE_MAPPING,
            NAME,
            SIMPLE_CASE_FOLDING,
            SIMPLE_LOWERCASE_MAPPING,
            SIMPLE_TITLECASE_MAPPING,
            SIMPLE_UPPERCASE_MAPPING,
            TITLECASE_MAPPING,
            // UNICODE_1_NAME is deprecated,
            UPPERCASE_MAPPING,
            BIDI_PAIRED_BRACKET,
        )
    }

    object Other : PropertyCollection<Any> {
        init {
            @Suppress("DEPRECATION")
            check(UProperty.OTHER_PROPERTY_START == 0x7000 && UProperty.OTHER_PROPERTY_LIMIT == 0x7002) { "Other properties not as expected!" }
        }

        val SCRIPT_EXTENSIONS = UnicodeProperty(
            UProperty.SCRIPT_EXTENSIONS,
            { codePoint, _ ->
                UnicodeScript.buildSetFromBitSet(
                    BitSet().apply {
                        UScript.getScriptExtensions(codePoint, this)
                    }
                )
            },
            { value, _ ->
                value
                    .map(UnicodeScript::longName)
                    .formatToString()
            }
        )

        val IDENTIFIER_TYPE = UnicodeProperty<Set<IdentifierType>>(
            UProperty.IDENTIFIER_TYPE,
            { codePoint, _ ->
                EnumSet.noneOf(IdentifierType::class.java).apply {
                    UCharacter.getIdentifierTypes(codePoint, this)
                }
            },
            { value, _ ->
                value
                    .map { element -> element.name.prettyPrintName() }
                    .formatToString()
            }
        )

        override fun all(): Sequence<UnicodeProperty<out Any>> = sequenceOf(
            SCRIPT_EXTENSIONS,
            IDENTIFIER_TYPE,
        )
    }

    // XXX: Is there a way to automate getting the full list without using reflection?
    fun all(): Sequence<UnicodeProperty<*>> = sequenceOf(
        Booleans::all,
        Ints::all,
        Masks::all,
        Doubles::all,
        Strings::all,
        Other::all,
    ).flatMap { prop -> prop.invoke() }

}

