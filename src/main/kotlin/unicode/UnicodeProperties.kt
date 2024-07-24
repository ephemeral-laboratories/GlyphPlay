package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacter.IdentifierType
import com.ibm.icu.lang.UProperty
import com.ibm.icu.lang.UScript
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.util.ULocale
import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.codePointToString
import garden.ephemeral.glyphplay.formatToString
import unicode.UnicodeProperty
import java.util.BitSet
import java.util.EnumSet

object UnicodeProperties {
    interface PropertyCollection<T> {
        fun all(): Sequence<UnicodeProperty<out T>>
    }

    private val ENUM_VALUE_DESCRIBER = { value: UnicodeValueEnum<*>, _: Int -> value.longName }

    private val ENUM_SET_DESCRIBER = { value: Set<UnicodeValueEnum<*>>, _: Int ->
        value
            .map(UnicodeValueEnum<*>::longName)
            .formatToString()
    }

    object Booleans : PropertyCollection<Boolean> {
        private val BOOLEAN_GETTER = { codePoint: Int, type: Int ->
            UCharacter.getIntPropertyValue(codePoint, type) != 0
        }
        private val BOOLEAN_DESCRIBER = { value: Boolean, _: Int -> if (value) "Yes" else "No" }

        private fun booleanProperty(icuValue: Int) = UnicodeProperty(icuValue, BOOLEAN_GETTER, BOOLEAN_DESCRIBER)

        val ALPHABETIC = booleanProperty(UProperty.ALPHABETIC)
        val ASCII_HEX_DIGIT = booleanProperty(UProperty.ASCII_HEX_DIGIT)
        val BIDI_CONTROL = booleanProperty(UProperty.BIDI_CONTROL)
        val BIDI_MIRRORED = booleanProperty(UProperty.BIDI_MIRRORED)
        val DASH = booleanProperty(UProperty.DASH)
        val DEFAULT_IGNORABLE_CODE_POINT = booleanProperty(UProperty.DEFAULT_IGNORABLE_CODE_POINT)
        val DEPRECATED = booleanProperty(UProperty.DEPRECATED)
        val DIACRITIC = booleanProperty(UProperty.DIACRITIC)
        val EXTENDER = booleanProperty(UProperty.EXTENDER)
        val FULL_COMPOSITION_EXCLUSION = booleanProperty(UProperty.FULL_COMPOSITION_EXCLUSION)
        val GRAPHEME_BASE = booleanProperty(UProperty.GRAPHEME_BASE)
        val GRAPHEME_EXTEND = booleanProperty(UProperty.GRAPHEME_EXTEND)
        val GRAPHEME_LINK = booleanProperty(UProperty.GRAPHEME_LINK)
        val HEX_DIGIT = booleanProperty(UProperty.HEX_DIGIT)
        val HYPHEN = booleanProperty(UProperty.HYPHEN)
        val ID_CONTINUE = booleanProperty(UProperty.ID_CONTINUE)
        val ID_START = booleanProperty(UProperty.ID_START)
        val IDEOGRAPHIC = booleanProperty(UProperty.IDEOGRAPHIC)
        val IDS_BINARY_OPERATOR = booleanProperty(UProperty.IDS_BINARY_OPERATOR)
        val IDS_TRINARY_OPERATOR = booleanProperty(UProperty.IDS_TRINARY_OPERATOR)
        val JOIN_CONTROL = booleanProperty(UProperty.JOIN_CONTROL)
        val LOGICAL_ORDER_EXCEPTION = booleanProperty(UProperty.LOGICAL_ORDER_EXCEPTION)
        val LOWERCASE = booleanProperty(UProperty.LOWERCASE)
        val MATH = booleanProperty(UProperty.MATH)
        val NON_CHARACTER_CODE_POINT = booleanProperty(UProperty.NONCHARACTER_CODE_POINT)
        val QUOTATION_MARK = booleanProperty(UProperty.QUOTATION_MARK)
        val RADICAL = booleanProperty(UProperty.RADICAL)
        val SOFT_DOTTED = booleanProperty(UProperty.SOFT_DOTTED)
        val TERMINAL_PUNCTUATION = booleanProperty(UProperty.TERMINAL_PUNCTUATION)
        val UNIFIED_IDEOGRAPH = booleanProperty(UProperty.UNIFIED_IDEOGRAPH)
        val UPPERCASE = booleanProperty(UProperty.UPPERCASE)
        val WHITE_SPACE = booleanProperty(UProperty.WHITE_SPACE)
        val XID_CONTINUE = booleanProperty(UProperty.XID_CONTINUE)
        val XID_START = booleanProperty(UProperty.XID_START)
        val CASE_SENSITIVE = booleanProperty(UProperty.CASE_SENSITIVE)
        val S_TERM = booleanProperty(UProperty.S_TERM)
        val VARIATION_SELECTOR = booleanProperty(UProperty.VARIATION_SELECTOR)
        val NFD_INERT = booleanProperty(UProperty.NFD_INERT)
        val NFKD_INERT = booleanProperty(UProperty.NFKD_INERT)
        val NFC_INERT = booleanProperty(UProperty.NFC_INERT)
        val NFKC_INERT = booleanProperty(UProperty.NFKC_INERT)
        val SEGMENT_STARTER = booleanProperty(UProperty.SEGMENT_STARTER)
        val PATTERN_SYNTAX = booleanProperty(UProperty.PATTERN_SYNTAX)
        val PATTERN_WHITE_SPACE = booleanProperty(UProperty.PATTERN_WHITE_SPACE)
        val POSIX_ALPHANUM = booleanProperty(UProperty.POSIX_ALNUM)
        val POSIX_BLANK = booleanProperty(UProperty.POSIX_BLANK)
        val POSIX_GRAPH = booleanProperty(UProperty.POSIX_GRAPH)
        val POSIX_PRINT = booleanProperty(UProperty.POSIX_PRINT)
        val POSIX_XDIGIT = booleanProperty(UProperty.POSIX_XDIGIT)
        val CASED = booleanProperty(UProperty.CASED)
        val CASE_IGNORABLE = booleanProperty(UProperty.CASE_IGNORABLE)
        val CHANGES_WHEN_LOWERCASED = booleanProperty(UProperty.CHANGES_WHEN_LOWERCASED)
        val CHANGES_WHEN_UPPERCASED = booleanProperty(UProperty.CHANGES_WHEN_UPPERCASED)
        val CHANGES_WHEN_TITLECASED = booleanProperty(UProperty.CHANGES_WHEN_TITLECASED)
        val CHANGES_WHEN_CASEFOLDED = booleanProperty(UProperty.CHANGES_WHEN_CASEFOLDED)
        val CHANGES_WHEN_CASEMAPPED = booleanProperty(UProperty.CHANGES_WHEN_CASEMAPPED)
        val CHANGES_WHEN_NFKC_CASEFOLDED = booleanProperty(UProperty.CHANGES_WHEN_NFKC_CASEFOLDED)
        val EMOJI = booleanProperty(UProperty.EMOJI)
        val EMOJI_PRESENTATION = booleanProperty(UProperty.EMOJI_PRESENTATION)
        val EMOJI_MODIFIER = booleanProperty(UProperty.EMOJI_MODIFIER)
        val EMOJI_MODIFIER_BASE = booleanProperty(UProperty.EMOJI_MODIFIER_BASE)
        val EMOJI_COMPONENT = booleanProperty(UProperty.EMOJI_COMPONENT)
        val REGIONAL_INDICATOR = booleanProperty(UProperty.REGIONAL_INDICATOR)
        val PREPENDED_CONCATENATION_MARK = booleanProperty(UProperty.PREPENDED_CONCATENATION_MARK)
        val EXTENDED_PICTOGRAPHIC = booleanProperty(UProperty.EXTENDED_PICTOGRAPHIC)
        val BASIC_EMOJI = booleanProperty(UProperty.BASIC_EMOJI)
        val EMOJI_KEYCAP_SEQUENCE = booleanProperty(UProperty.EMOJI_KEYCAP_SEQUENCE)
        val RGI_EMOJI_MODIFIER_SEQUENCE = booleanProperty(UProperty.RGI_EMOJI_MODIFIER_SEQUENCE)
        val RGI_EMOJI_FLAG_SEQUENCE = booleanProperty(UProperty.RGI_EMOJI_FLAG_SEQUENCE)
        val RGI_EMOJI_TAG_SEQUENCE = booleanProperty(UProperty.RGI_EMOJI_TAG_SEQUENCE)
        val RGI_EMOJI_ZWJ_SEQUENCE = booleanProperty(UProperty.RGI_EMOJI_ZWJ_SEQUENCE)
        val RGI_EMOJI = booleanProperty(UProperty.RGI_EMOJI)
        val IDS_UNARY_OPERATOR = booleanProperty(UProperty.IDS_UNARY_OPERATOR)
        val ID_COMPAT_MATH_START = booleanProperty(UProperty.ID_COMPAT_MATH_START)
        val ID_COMPAT_MATH_CONTINUE = booleanProperty(UProperty.ID_COMPAT_MATH_CONTINUE)

        override fun all(): Sequence<UnicodeProperty<Boolean>> = sequenceOf(
            ALPHABETIC,
            ASCII_HEX_DIGIT,
            BIDI_CONTROL,
            BIDI_MIRRORED,
            DASH,
            DEFAULT_IGNORABLE_CODE_POINT,
            DEPRECATED,
            DIACRITIC,
            EXTENDER,
            FULL_COMPOSITION_EXCLUSION,
            GRAPHEME_BASE,
            GRAPHEME_EXTEND,
            GRAPHEME_LINK,
            HEX_DIGIT,
            HYPHEN,
            ID_CONTINUE,
            ID_START,
            IDEOGRAPHIC,
            IDS_BINARY_OPERATOR,
            IDS_TRINARY_OPERATOR,
            JOIN_CONTROL,
            LOGICAL_ORDER_EXCEPTION,
            LOWERCASE,
            MATH,
            NON_CHARACTER_CODE_POINT,
            QUOTATION_MARK,
            RADICAL,
            SOFT_DOTTED,
            TERMINAL_PUNCTUATION,
            UNIFIED_IDEOGRAPH,
            UPPERCASE,
            WHITE_SPACE,
            XID_CONTINUE,
            XID_START,
            CASE_SENSITIVE,
            S_TERM,
            VARIATION_SELECTOR,
            NFD_INERT,
            NFKD_INERT,
            NFC_INERT,
            NFKC_INERT,
            SEGMENT_STARTER,
            PATTERN_SYNTAX,
            PATTERN_WHITE_SPACE,
            POSIX_ALPHANUM,
            POSIX_BLANK,
            POSIX_GRAPH,
            POSIX_PRINT,
            POSIX_XDIGIT,
            CASED,
            CASE_IGNORABLE,
            CHANGES_WHEN_LOWERCASED,
            CHANGES_WHEN_UPPERCASED,
            CHANGES_WHEN_TITLECASED,
            CHANGES_WHEN_CASEFOLDED,
            CHANGES_WHEN_CASEMAPPED,
            CHANGES_WHEN_NFKC_CASEFOLDED,
            EMOJI,
            EMOJI_PRESENTATION,
            EMOJI_MODIFIER,
            EMOJI_MODIFIER_BASE,
            EMOJI_COMPONENT,
            REGIONAL_INDICATOR,
            PREPENDED_CONCATENATION_MARK,
            EXTENDED_PICTOGRAPHIC,
            BASIC_EMOJI,
            EMOJI_KEYCAP_SEQUENCE,
            RGI_EMOJI_MODIFIER_SEQUENCE,
            RGI_EMOJI_FLAG_SEQUENCE,
            RGI_EMOJI_TAG_SEQUENCE,
            RGI_EMOJI_ZWJ_SEQUENCE,
            RGI_EMOJI,
            IDS_UNARY_OPERATOR,
            ID_COMPAT_MATH_START,
            ID_COMPAT_MATH_CONTINUE,
        )
    }

    object Ints : PropertyCollection<Any> {
        private fun <T : UnicodeValueEnum<T>> enumProperty(
            icuValue: Int,
            enumValueCompanion: UnicodeValueEnum.Companion<T>
        ) = UnicodeProperty(
            icuValue = icuValue,
            propertyGetter = { codePoint, _ ->
                val icuPropertyValue = UCharacter.getIntPropertyValue(codePoint, icuValue)
                enumValueCompanion.ofIcuValue(icuPropertyValue)
            },
            propertyValueDescriber = ENUM_VALUE_DESCRIBER,
        )

        val BIDI_CLASS = enumProperty(UProperty.BIDI_CLASS, UnicodeCharacterDirection.Companion)
        val BLOCK = enumProperty(UProperty.BLOCK, UnicodeBlock.Companion)
        val CANONICAL_COMBINING_CLASS = enumProperty(UProperty.CANONICAL_COMBINING_CLASS, UnicodeCanonicalCombiningClass.Companion)
        val DECOMPOSITION_TYPE = enumProperty(UProperty.DECOMPOSITION_TYPE, UnicodeDecompositionType.Companion)
        val EAST_ASIAN_WIDTH = enumProperty(UProperty.EAST_ASIAN_WIDTH, UnicodeEastAsianWidth.Companion)
        val GENERAL_CATEGORY = enumProperty(UProperty.GENERAL_CATEGORY, UnicodeCharacterCategory.Companion)
        val JOINING_GROUP = enumProperty(UProperty.JOINING_GROUP, UnicodeJoiningGroup.Companion)
        val JOINING_TYPE = enumProperty(UProperty.JOINING_TYPE, UnicodeJoiningType.Companion)
        val LINE_BREAK = enumProperty(UProperty.LINE_BREAK, UnicodeLineBreak.Companion)
        val NUMERIC_TYPE = enumProperty(UProperty.NUMERIC_TYPE, UnicodeNumericType.Companion)
        val SCRIPT = enumProperty(UProperty.SCRIPT, UnicodeScript.Companion)
        val HANGUL_SYLLABLE_TYPE = enumProperty(UProperty.HANGUL_SYLLABLE_TYPE, UnicodeHangulSyllableType.Companion)
        val NFD_QUICK_CHECK = enumProperty(UProperty.NFD_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val NFKD_QUICK_CHECK = enumProperty(UProperty.NFKD_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val NFC_QUICK_CHECK = enumProperty(UProperty.NFC_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val NFKC_QUICK_CHECK = enumProperty(UProperty.NFKC_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val LEAD_CANONICAL_COMBINING_CLASS = enumProperty(UProperty.LEAD_CANONICAL_COMBINING_CLASS, UnicodeCanonicalCombiningClass.Companion)
        val TRAIL_CANONICAL_COMBINING_CLASS = enumProperty(UProperty.TRAIL_CANONICAL_COMBINING_CLASS, UnicodeCanonicalCombiningClass.Companion)
        val GRAPHEME_CLUSTER_BREAK = enumProperty(UProperty.GRAPHEME_CLUSTER_BREAK, UnicodeGraphemeClusterBreak.Companion)
        val SENTENCE_BREAK = enumProperty(UProperty.SENTENCE_BREAK, UnicodeSentenceBreak.Companion)
        val WORD_BREAK = enumProperty(UProperty.WORD_BREAK, UnicodeWordBreak.Companion)
        val BIDI_PAIRED_BRACKET_TYPE = enumProperty(UProperty.BIDI_PAIRED_BRACKET_TYPE, UnicodeBidiPairedBracketType.Companion)
        val INDIC_POSITIONAL_CATEGORY = enumProperty(UProperty.INDIC_POSITIONAL_CATEGORY, UnicodeIndicPositionalCategory.Companion)
        val INDIC_SYLLABIC_CATEGORY = enumProperty(UProperty.INDIC_SYLLABIC_CATEGORY, UnicodeIndicSyllabicCategory.Companion)
        val VERTICAL_ORIENTATION = enumProperty(UProperty.VERTICAL_ORIENTATION, UnicodeVerticalOrientation.Companion)
        val IDENTIFIER_STATUS = enumProperty(UProperty.IDENTIFIER_STATUS, UnicodeIdentifierStatus.Companion)

        /**
         * Although all the properties are treated by ICU as ints, the actual types we
         * return are almost always enums, the improved type-safety.
         */
        override fun all(): Sequence<UnicodeProperty<out Any>> = sequenceOf(
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

    object Masks : PropertyCollection<Any> {
        val GENERAL_CATEGORY_MASK = UnicodeProperty(
            icuValue = UProperty.GENERAL_CATEGORY_MASK,
            propertyGetter = { codePoint, icuValue ->
                val mask = UCharacter.getIntPropertyValue(codePoint, icuValue)
                UnicodeCharacterCategory.buildSetFromMask(mask)
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
        )

        /**
         * Although masks are returned by ICU as ints, we generally convert them to sets.
         */
        override fun all(): Sequence<UnicodeProperty<out Any>> = sequenceOf(
            GENERAL_CATEGORY_MASK
        )
    }

    object Doubles : PropertyCollection<Double> {
        private val DOUBLE_DESCRIBER = { value: Double, _: Int ->
            if (value == UCharacter.NO_NUMERIC_VALUE) {
                "No Numeric Value"
            } else {
                value.toString()
            }
        }

        val NUMERIC_VALUE = UnicodeProperty(
            icuValue = UProperty.NUMERIC_VALUE,
            propertyGetter = { cp, _ -> UCharacter.getUnicodeNumericValue(cp) },
            propertyValueDescriber = DOUBLE_DESCRIBER
        )

        override fun all(): Sequence<UnicodeProperty<Double>> = sequenceOf(
            NUMERIC_VALUE
        )
    }

    object Strings : PropertyCollection<Any> {
        private val STRING_DESCRIBER = { value: String, _: Int -> value }

        val AGE = UnicodeProperty<VersionInfo>(
            icuValue = UProperty.AGE,
            propertyGetter = { cp, _ -> UCharacter.getAge(cp) },
            propertyValueDescriber = { versionInfo, _ ->
                listOf(versionInfo.major, versionInfo.minor, versionInfo.milli, versionInfo.micro)
                    .dropLastWhile { part -> part == 0 }
                    .joinToString(separator = ".")
            }
        )

        val BIDI_MIRRORING_GLYPH = UnicodeProperty(
            icuValue = UProperty.BIDI_MIRRORING_GLYPH,
            propertyGetter = { cp, _ -> UCharacter.getMirror(cp).codePointToString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val CASE_FOLDING = UnicodeProperty<String>(
            icuValue = UProperty.CASE_FOLDING,
            propertyGetter = { cp, _ -> UCharacter.foldCase(cp.codePointToString(), true) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        // ISO_COMMENT omitted as ICU4J deprecated it

        val LOWERCASE_MAPPING = UnicodeProperty(
            icuValue = UProperty.LOWERCASE_MAPPING,
            propertyGetter = { cp, _ -> UCharacter.toLowerCase(ULocale.ROOT, cp.codePointToString()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val NAME = UnicodeProperty(
            icuValue = UProperty.NAME,
            propertyGetter = { cp, _ -> UCharacter.getName(cp) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_CASE_FOLDING = UnicodeProperty(
            icuValue = UProperty.SIMPLE_CASE_FOLDING,
            propertyGetter = { cp, _ -> UCharacter.foldCase(cp, true).codePointToString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_LOWERCASE_MAPPING = UnicodeProperty(
            icuValue = UProperty.SIMPLE_LOWERCASE_MAPPING,
            propertyGetter = { cp, _ -> UCharacter.toLowerCase(cp).codePointToString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_TITLECASE_MAPPING = UnicodeProperty(
            icuValue = UProperty.SIMPLE_TITLECASE_MAPPING,
            propertyGetter = { cp, _ -> UCharacter.toTitleCase(cp).codePointToString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_UPPERCASE_MAPPING = UnicodeProperty(
            icuValue = UProperty.SIMPLE_UPPERCASE_MAPPING,
            propertyGetter = { cp, _ -> UCharacter.toUpperCase(cp).codePointToString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val TITLECASE_MAPPING = UnicodeProperty(
            icuValue = UProperty.TITLECASE_MAPPING,
            propertyGetter = { cp, _ ->
                UCharacter.toTitleCase(
                    ULocale.ROOT,
                    cp.codePointToString(),
                    BreakIterator.getWordInstance(ULocale.ROOT)
                )
            },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        // UNICODE_1_NAME omitted as ICU4J deprecated it

        val UPPERCASE_MAPPING = UnicodeProperty(
            icuValue = UProperty.UPPERCASE_MAPPING,
            propertyGetter = { cp, _ -> UCharacter.toUpperCase(ULocale.ROOT, cp.codePointToString()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val BIDI_PAIRED_BRACKET = UnicodeProperty(
            icuValue = UProperty.BIDI_PAIRED_BRACKET,
            propertyGetter = { cp, _ -> UCharacter.getBidiPairedBracket(cp).codePointToString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        /**
         * Although all the properties are treated by ICU as strings, the actual types we
         * return are sometimes more specific because we have interpreted the string.
         */
        override fun all(): Sequence<UnicodeProperty<out Any>> = sequenceOf(
            AGE,
            BIDI_MIRRORING_GLYPH,
            CASE_FOLDING,
            LOWERCASE_MAPPING,
            NAME,
            SIMPLE_CASE_FOLDING,
            SIMPLE_LOWERCASE_MAPPING,
            SIMPLE_TITLECASE_MAPPING,
            SIMPLE_UPPERCASE_MAPPING,
            TITLECASE_MAPPING,
            UPPERCASE_MAPPING,
            BIDI_PAIRED_BRACKET,
        )
    }

    object Other : PropertyCollection<Any> {
        val SCRIPT_EXTENSIONS = UnicodeProperty(
            icuValue = UProperty.SCRIPT_EXTENSIONS,
            propertyGetter = { codePoint, _ ->
                UnicodeScript.buildSetFromBitSet(
                    BitSet().apply {
                        UScript.getScriptExtensions(codePoint, this)
                    }
                )
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
        )

        val IDENTIFIER_TYPE = UnicodeProperty(
            icuValue = UProperty.IDENTIFIER_TYPE,
            propertyGetter = { codePoint, _ ->
                UnicodeIdentifierType.buildSet(
                    EnumSet.noneOf(IdentifierType::class.java).apply {
                        UCharacter.getIdentifierTypes(codePoint, this)
                    }
                )
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
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

