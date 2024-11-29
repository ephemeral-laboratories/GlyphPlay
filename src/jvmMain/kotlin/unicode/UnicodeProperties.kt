package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacter.IdentifierType
import com.ibm.icu.lang.UProperty
import com.ibm.icu.lang.UScript
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.text.Normalizer2
import com.ibm.icu.util.ULocale
import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.VersionInfoSummary
import garden.ephemeral.glyphplay.formatToString
import garden.ephemeral.glyphplay.normalize
import garden.ephemeral.glyphplay.prettyPrintName
import garden.ephemeral.glyphplay.unicode.enums.IcuUnicodeValueEnum
import garden.ephemeral.glyphplay.unicode.enums.UnicodeBidiPairedBracketType
import garden.ephemeral.glyphplay.unicode.enums.UnicodeBlock
import garden.ephemeral.glyphplay.unicode.enums.UnicodeCanonicalCombiningClass
import garden.ephemeral.glyphplay.unicode.enums.UnicodeCharacterCategory
import garden.ephemeral.glyphplay.unicode.enums.UnicodeCharacterDirection
import garden.ephemeral.glyphplay.unicode.enums.UnicodeDecompositionType
import garden.ephemeral.glyphplay.unicode.enums.UnicodeEastAsianWidth
import garden.ephemeral.glyphplay.unicode.enums.UnicodeGraphemeClusterBreak
import garden.ephemeral.glyphplay.unicode.enums.UnicodeHangulSyllableType
import garden.ephemeral.glyphplay.unicode.enums.UnicodeIdentifierStatus
import garden.ephemeral.glyphplay.unicode.enums.UnicodeIdentifierType
import garden.ephemeral.glyphplay.unicode.enums.UnicodeIndicPositionalCategory
import garden.ephemeral.glyphplay.unicode.enums.UnicodeIndicSyllabicCategory
import garden.ephemeral.glyphplay.unicode.enums.UnicodeJoiningGroup
import garden.ephemeral.glyphplay.unicode.enums.UnicodeJoiningType
import garden.ephemeral.glyphplay.unicode.enums.UnicodeLineBreak
import garden.ephemeral.glyphplay.unicode.enums.UnicodeNumericType
import garden.ephemeral.glyphplay.unicode.enums.UnicodePlane
import garden.ephemeral.glyphplay.unicode.enums.UnicodeQuickCheckResult
import garden.ephemeral.glyphplay.unicode.enums.UnicodeScript
import garden.ephemeral.glyphplay.unicode.enums.UnicodeSentenceBreak
import garden.ephemeral.glyphplay.unicode.enums.UnicodeValueEnum
import garden.ephemeral.glyphplay.unicode.enums.UnicodeVerticalOrientation
import garden.ephemeral.glyphplay.unicode.enums.UnicodeWordBreak
import java.util.BitSet
import java.util.EnumSet

object UnicodeProperties {

    private fun nameForIcuProperty(icuPropertyId: Int) =
        UCharacter.getPropertyName(icuPropertyId, UProperty.NameChoice.LONG)
            .prettyPrintName()

    private val ENUM_SET_DESCRIBER = { value: Set<UnicodeValueEnum<*>> ->
        value
            .map(UnicodeValueEnum<*>::longName)
            .formatToString()
    }

    /**
     * Boolean properties.
     */
    object Booleans : CodePointPropertyCollection<Boolean> {
        private fun icuBooleanProperty(icuPropertyId: Int) = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(icuPropertyId) },
            propertyValueGetter = { codePoint -> UCharacter.getIntPropertyValue(codePoint.value, icuPropertyId) != 0 },
            propertyValueDescriber = { value: Boolean -> if (value) "Yes" else "No" }
        )

        val ALPHABETIC = icuBooleanProperty(UProperty.ALPHABETIC)
        val ASCII_HEX_DIGIT = icuBooleanProperty(UProperty.ASCII_HEX_DIGIT)
        val BIDI_CONTROL = icuBooleanProperty(UProperty.BIDI_CONTROL)
        val BIDI_MIRRORED = icuBooleanProperty(UProperty.BIDI_MIRRORED)
        val DASH = icuBooleanProperty(UProperty.DASH)
        val DEFAULT_IGNORABLE_CODE_POINT = icuBooleanProperty(UProperty.DEFAULT_IGNORABLE_CODE_POINT)
        val DEPRECATED = icuBooleanProperty(UProperty.DEPRECATED)
        val DIACRITIC = icuBooleanProperty(UProperty.DIACRITIC)
        val EXTENDER = icuBooleanProperty(UProperty.EXTENDER)
        val FULL_COMPOSITION_EXCLUSION = icuBooleanProperty(UProperty.FULL_COMPOSITION_EXCLUSION)
        val GRAPHEME_BASE = icuBooleanProperty(UProperty.GRAPHEME_BASE)
        val GRAPHEME_EXTEND = icuBooleanProperty(UProperty.GRAPHEME_EXTEND)
        val GRAPHEME_LINK = icuBooleanProperty(UProperty.GRAPHEME_LINK)
        val HEX_DIGIT = icuBooleanProperty(UProperty.HEX_DIGIT)
        val HYPHEN = icuBooleanProperty(UProperty.HYPHEN)
        val ID_CONTINUE = icuBooleanProperty(UProperty.ID_CONTINUE)
        val ID_START = icuBooleanProperty(UProperty.ID_START)
        val IDEOGRAPHIC = icuBooleanProperty(UProperty.IDEOGRAPHIC)
        val IDS_BINARY_OPERATOR = icuBooleanProperty(UProperty.IDS_BINARY_OPERATOR)
        val IDS_TRINARY_OPERATOR = icuBooleanProperty(UProperty.IDS_TRINARY_OPERATOR)
        val JOIN_CONTROL = icuBooleanProperty(UProperty.JOIN_CONTROL)
        val LOGICAL_ORDER_EXCEPTION = icuBooleanProperty(UProperty.LOGICAL_ORDER_EXCEPTION)
        val LOWERCASE = icuBooleanProperty(UProperty.LOWERCASE)
        val MATH = icuBooleanProperty(UProperty.MATH)
        val NON_CHARACTER_CODE_POINT = icuBooleanProperty(UProperty.NONCHARACTER_CODE_POINT)
        val QUOTATION_MARK = icuBooleanProperty(UProperty.QUOTATION_MARK)
        val RADICAL = icuBooleanProperty(UProperty.RADICAL)
        val SOFT_DOTTED = icuBooleanProperty(UProperty.SOFT_DOTTED)
        val TERMINAL_PUNCTUATION = icuBooleanProperty(UProperty.TERMINAL_PUNCTUATION)
        val UNIFIED_IDEOGRAPH = icuBooleanProperty(UProperty.UNIFIED_IDEOGRAPH)
        val UPPERCASE = icuBooleanProperty(UProperty.UPPERCASE)
        val WHITE_SPACE = icuBooleanProperty(UProperty.WHITE_SPACE)
        val XID_CONTINUE = icuBooleanProperty(UProperty.XID_CONTINUE)
        val XID_START = icuBooleanProperty(UProperty.XID_START)
        val CASE_SENSITIVE = icuBooleanProperty(UProperty.CASE_SENSITIVE)
        val S_TERM = icuBooleanProperty(UProperty.S_TERM)
        val VARIATION_SELECTOR = icuBooleanProperty(UProperty.VARIATION_SELECTOR)
        val NFD_INERT = icuBooleanProperty(UProperty.NFD_INERT)
        val NFKD_INERT = icuBooleanProperty(UProperty.NFKD_INERT)
        val NFC_INERT = icuBooleanProperty(UProperty.NFC_INERT)
        val NFKC_INERT = icuBooleanProperty(UProperty.NFKC_INERT)
        val SEGMENT_STARTER = icuBooleanProperty(UProperty.SEGMENT_STARTER)
        val PATTERN_SYNTAX = icuBooleanProperty(UProperty.PATTERN_SYNTAX)
        val PATTERN_WHITE_SPACE = icuBooleanProperty(UProperty.PATTERN_WHITE_SPACE)
        val POSIX_ALPHANUM = icuBooleanProperty(UProperty.POSIX_ALNUM)
        val POSIX_BLANK = icuBooleanProperty(UProperty.POSIX_BLANK)
        val POSIX_GRAPH = icuBooleanProperty(UProperty.POSIX_GRAPH)
        val POSIX_PRINT = icuBooleanProperty(UProperty.POSIX_PRINT)
        val POSIX_XDIGIT = icuBooleanProperty(UProperty.POSIX_XDIGIT)
        val CASED = icuBooleanProperty(UProperty.CASED)
        val CASE_IGNORABLE = icuBooleanProperty(UProperty.CASE_IGNORABLE)
        val CHANGES_WHEN_LOWERCASED = icuBooleanProperty(UProperty.CHANGES_WHEN_LOWERCASED)
        val CHANGES_WHEN_UPPERCASED = icuBooleanProperty(UProperty.CHANGES_WHEN_UPPERCASED)
        val CHANGES_WHEN_TITLECASED = icuBooleanProperty(UProperty.CHANGES_WHEN_TITLECASED)
        val CHANGES_WHEN_CASEFOLDED = icuBooleanProperty(UProperty.CHANGES_WHEN_CASEFOLDED)
        val CHANGES_WHEN_CASEMAPPED = icuBooleanProperty(UProperty.CHANGES_WHEN_CASEMAPPED)
        val CHANGES_WHEN_NFKC_CASEFOLDED = icuBooleanProperty(UProperty.CHANGES_WHEN_NFKC_CASEFOLDED)
        val EMOJI = icuBooleanProperty(UProperty.EMOJI)
        val EMOJI_PRESENTATION = icuBooleanProperty(UProperty.EMOJI_PRESENTATION)
        val EMOJI_MODIFIER = icuBooleanProperty(UProperty.EMOJI_MODIFIER)
        val EMOJI_MODIFIER_BASE = icuBooleanProperty(UProperty.EMOJI_MODIFIER_BASE)
        val EMOJI_COMPONENT = icuBooleanProperty(UProperty.EMOJI_COMPONENT)
        val REGIONAL_INDICATOR = icuBooleanProperty(UProperty.REGIONAL_INDICATOR)
        val PREPENDED_CONCATENATION_MARK = icuBooleanProperty(UProperty.PREPENDED_CONCATENATION_MARK)
        val EXTENDED_PICTOGRAPHIC = icuBooleanProperty(UProperty.EXTENDED_PICTOGRAPHIC)
        val BASIC_EMOJI = icuBooleanProperty(UProperty.BASIC_EMOJI)
        val EMOJI_KEYCAP_SEQUENCE = icuBooleanProperty(UProperty.EMOJI_KEYCAP_SEQUENCE)
        val RGI_EMOJI_MODIFIER_SEQUENCE = icuBooleanProperty(UProperty.RGI_EMOJI_MODIFIER_SEQUENCE)
        val RGI_EMOJI_FLAG_SEQUENCE = icuBooleanProperty(UProperty.RGI_EMOJI_FLAG_SEQUENCE)
        val RGI_EMOJI_TAG_SEQUENCE = icuBooleanProperty(UProperty.RGI_EMOJI_TAG_SEQUENCE)
        val RGI_EMOJI_ZWJ_SEQUENCE = icuBooleanProperty(UProperty.RGI_EMOJI_ZWJ_SEQUENCE)
        val RGI_EMOJI = icuBooleanProperty(UProperty.RGI_EMOJI)
        val IDS_UNARY_OPERATOR = icuBooleanProperty(UProperty.IDS_UNARY_OPERATOR)
        val ID_COMPAT_MATH_START = icuBooleanProperty(UProperty.ID_COMPAT_MATH_START)
        val ID_COMPAT_MATH_CONTINUE = icuBooleanProperty(UProperty.ID_COMPAT_MATH_CONTINUE)

        override fun all(): Sequence<CodePointProperty<Boolean>> = sequenceOf(
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

    /**
     * Int properties.
     *
     * Although all the properties are treated by ICU as ints, the actual types we
     * return are almost always enums, the improved type-safety.
     */
    object Ints : CodePointPropertyCollection<UnicodeValueEnum<*>> {
        private fun <T : IcuUnicodeValueEnum<T>> icuEnumProperty(
            icuPropertyId: Int,
            enumValueCompanion: IcuUnicodeValueEnum.Companion<T>
        ) = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(icuPropertyId) },
            propertyValueGetter = { codePoint ->
                val icuPropertyValue = UCharacter.getIntPropertyValue(codePoint.value, icuPropertyId)
                enumValueCompanion.ofIcuValue(icuPropertyValue)
            },
            propertyValueDescriber = { value -> value.longName },
        )

        val BIDI_CLASS = icuEnumProperty(UProperty.BIDI_CLASS, UnicodeCharacterDirection.Companion)
        val BLOCK = icuEnumProperty(UProperty.BLOCK, UnicodeBlock.Companion)
        val CANONICAL_COMBINING_CLASS = icuEnumProperty(UProperty.CANONICAL_COMBINING_CLASS, UnicodeCanonicalCombiningClass.Companion)
        val DECOMPOSITION_TYPE = icuEnumProperty(UProperty.DECOMPOSITION_TYPE, UnicodeDecompositionType.Companion)
        val EAST_ASIAN_WIDTH = icuEnumProperty(UProperty.EAST_ASIAN_WIDTH, UnicodeEastAsianWidth.Companion)
        val GENERAL_CATEGORY = icuEnumProperty(UProperty.GENERAL_CATEGORY, UnicodeCharacterCategory.Companion)
        val JOINING_GROUP = icuEnumProperty(UProperty.JOINING_GROUP, UnicodeJoiningGroup.Companion)
        val JOINING_TYPE = icuEnumProperty(UProperty.JOINING_TYPE, UnicodeJoiningType.Companion)
        val LINE_BREAK = icuEnumProperty(UProperty.LINE_BREAK, UnicodeLineBreak.Companion)
        val NUMERIC_TYPE = icuEnumProperty(UProperty.NUMERIC_TYPE, UnicodeNumericType.Companion)
        val SCRIPT = icuEnumProperty(UProperty.SCRIPT, UnicodeScript.Companion)
        val HANGUL_SYLLABLE_TYPE = icuEnumProperty(UProperty.HANGUL_SYLLABLE_TYPE, UnicodeHangulSyllableType.Companion)
        val NFD_QUICK_CHECK = icuEnumProperty(UProperty.NFD_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val NFKD_QUICK_CHECK = icuEnumProperty(UProperty.NFKD_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val NFC_QUICK_CHECK = icuEnumProperty(UProperty.NFC_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val NFKC_QUICK_CHECK = icuEnumProperty(UProperty.NFKC_QUICK_CHECK, UnicodeQuickCheckResult.Companion)
        val LEAD_CANONICAL_COMBINING_CLASS = icuEnumProperty(UProperty.LEAD_CANONICAL_COMBINING_CLASS, UnicodeCanonicalCombiningClass.Companion)
        val TRAIL_CANONICAL_COMBINING_CLASS = icuEnumProperty(UProperty.TRAIL_CANONICAL_COMBINING_CLASS, UnicodeCanonicalCombiningClass.Companion)
        val GRAPHEME_CLUSTER_BREAK = icuEnumProperty(UProperty.GRAPHEME_CLUSTER_BREAK, UnicodeGraphemeClusterBreak.Companion)
        val SENTENCE_BREAK = icuEnumProperty(UProperty.SENTENCE_BREAK, UnicodeSentenceBreak.Companion)
        val WORD_BREAK = icuEnumProperty(UProperty.WORD_BREAK, UnicodeWordBreak.Companion)
        val BIDI_PAIRED_BRACKET_TYPE = icuEnumProperty(UProperty.BIDI_PAIRED_BRACKET_TYPE, UnicodeBidiPairedBracketType.Companion)
        val INDIC_POSITIONAL_CATEGORY = icuEnumProperty(UProperty.INDIC_POSITIONAL_CATEGORY, UnicodeIndicPositionalCategory.Companion)
        val INDIC_SYLLABIC_CATEGORY = icuEnumProperty(UProperty.INDIC_SYLLABIC_CATEGORY, UnicodeIndicSyllabicCategory.Companion)
        val VERTICAL_ORIENTATION = icuEnumProperty(UProperty.VERTICAL_ORIENTATION, UnicodeVerticalOrientation.Companion)
        val IDENTIFIER_STATUS = icuEnumProperty(UProperty.IDENTIFIER_STATUS, UnicodeIdentifierStatus.Companion)

        val PLANE = CodePointProperty(
            propertyNameGetter = { "Plane" },
            propertyValueGetter = { cp -> UnicodePlane.ofCodePoint(cp) },
            propertyValueDescriber = { value -> value.longName },
        )

        override fun all(): Sequence<CodePointProperty<out UnicodeValueEnum<*>>> = sequenceOf(
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
            PLANE,
        )
    }

    /**
     * Mask properties.
     *
     * Although masks are returned by ICU as ints, we generally convert them to sets.
     */
    object Masks : CodePointPropertyCollection<Set<UnicodeValueEnum<*>>> {
        val GENERAL_CATEGORY_MASK = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.GENERAL_CATEGORY_MASK) },
            propertyValueGetter = { codePoint ->
                val mask = UCharacter.getIntPropertyValue(codePoint.value, UProperty.GENERAL_CATEGORY_MASK)
                UnicodeCharacterCategory.buildSetFromMask(mask)
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
        )

        override fun all(): Sequence<CodePointProperty<out Set<UnicodeValueEnum<*>>>> = sequenceOf(
            GENERAL_CATEGORY_MASK
        )
    }

    /**
     * Double properties.
     */
    object Doubles : CodePointPropertyCollection<Double> {
        private val DOUBLE_DESCRIBER = { value: Double ->
            if (value == UCharacter.NO_NUMERIC_VALUE) {
                "No Numeric Value"
            } else {
                value.toString()
            }
        }

        val NUMERIC_VALUE = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.NUMERIC_VALUE) },
            propertyValueGetter = { cp -> UCharacter.getUnicodeNumericValue(cp.value) },
            propertyValueDescriber = DOUBLE_DESCRIBER
        )

        override fun all(): Sequence<CodePointProperty<Double>> = sequenceOf(
            NUMERIC_VALUE
        )
    }

    /**
     * String properties.
     */
    object Strings : CodePointPropertyCollection<Any> {
        private val STRING_DESCRIBER = { value: String -> value }
        private val NAME_DESCRIBER = { value: String -> value.prettyPrintName() }

        val AGE = CodePointProperty<VersionInfo>(
            propertyNameGetter = { nameForIcuProperty(UProperty.AGE) },
            propertyValueGetter = { cp -> UCharacter.getAge(cp.value) },
            propertyValueDescriber = { versionInfo -> VersionInfoSummary.of(versionInfo).fullDescription },
        )

        val BIDI_MIRRORING_GLYPH = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.BIDI_MIRRORING_GLYPH) },
            propertyValueGetter = { cp -> CodePoint(UCharacter.getMirror(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val CASE_FOLDING = CodePointProperty<String>(
            propertyNameGetter = { nameForIcuProperty(UProperty.CASE_FOLDING) },
            propertyValueGetter = { cp -> UCharacter.foldCase(cp.toString(), true) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        // ISO_COMMENT omitted as ICU4J deprecated it

        val LOWERCASE_MAPPING = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.LOWERCASE_MAPPING) },
            propertyValueGetter = { cp -> UCharacter.toLowerCase(ULocale.ROOT, cp.toString()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val NAME = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.NAME) },
            propertyValueGetter = { cp -> UCharacter.getName(cp.value) ?: "" },
            propertyValueDescriber = NAME_DESCRIBER,
        )

        // Not present in ICU4J as a property.
        val NAME_ALIAS = CodePointProperty(
            propertyNameGetter = { "Name Alias" },
            propertyValueGetter = { cp -> UCharacter.getNameAlias(cp.value) ?: "" },
            propertyValueDescriber = NAME_DESCRIBER,
        )

        // Not present in ICU4J as a property.
        val EXTENDED_NAME = CodePointProperty(
            propertyNameGetter = { "Extended Name" },
            propertyValueGetter = { cp -> UCharacter.getExtendedName(cp.value) ?: "" },
            propertyValueDescriber = NAME_DESCRIBER,
        )

        val SIMPLE_CASE_FOLDING = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.SIMPLE_CASE_FOLDING) },
            propertyValueGetter = { cp -> CodePoint(UCharacter.foldCase(cp.value, true)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_LOWERCASE_MAPPING = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.SIMPLE_LOWERCASE_MAPPING) },
            propertyValueGetter = { cp -> CodePoint(UCharacter.toLowerCase(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_TITLECASE_MAPPING = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.SIMPLE_TITLECASE_MAPPING) },
            propertyValueGetter = { cp -> CodePoint(UCharacter.toTitleCase(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_UPPERCASE_MAPPING = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.SIMPLE_UPPERCASE_MAPPING) },
            propertyValueGetter = { cp -> CodePoint(UCharacter.toUpperCase(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val TITLECASE_MAPPING = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.TITLECASE_MAPPING) },
            propertyValueGetter = { codePoint ->
                UCharacter.toTitleCase(
                    ULocale.ROOT,
                    codePoint.toString(),
                    BreakIterator.getWordInstance(ULocale.ROOT)
                )
            },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        // UNICODE_1_NAME omitted as ICU4J deprecated it

        val UPPERCASE_MAPPING = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.UPPERCASE_MAPPING) },
            propertyValueGetter = { cp -> UCharacter.toUpperCase(ULocale.ROOT, cp.toString()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val BIDI_PAIRED_BRACKET = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.BIDI_PAIRED_BRACKET) },
            propertyValueGetter = { cp -> CodePoint(UCharacter.getBidiPairedBracket(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val CANONICAL_DECOMPOSITION = CodePointProperty(
            propertyNameGetter = { "Canonical Decomposition" },
            propertyValueGetter = { cp -> cp.toString().normalize(Normalizer2.getNFDInstance()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )
        val COMPATIBILITY_DECOMPOSITION = CodePointProperty(
            propertyNameGetter = { "Compatibility Decomposition" },
            propertyValueGetter = { cp -> cp.toString().normalize(Normalizer2.getNFKDInstance()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        /**
         * Although all the properties are treated by ICU as strings, the actual types we
         * return are sometimes more specific because we have interpreted the string.
         */
        override fun all(): Sequence<CodePointProperty<out Any>> = sequenceOf(
            AGE,
            BIDI_MIRRORING_GLYPH,
            CASE_FOLDING,
            LOWERCASE_MAPPING,
            NAME,
            NAME_ALIAS,
            EXTENDED_NAME,
            SIMPLE_CASE_FOLDING,
            SIMPLE_LOWERCASE_MAPPING,
            SIMPLE_TITLECASE_MAPPING,
            SIMPLE_UPPERCASE_MAPPING,
            TITLECASE_MAPPING,
            UPPERCASE_MAPPING,
            BIDI_PAIRED_BRACKET,
            CANONICAL_DECOMPOSITION,
            COMPATIBILITY_DECOMPOSITION,
        )
    }

    /**
     * Other properties.
     */
    object Other : CodePointPropertyCollection<Any> {
        val SCRIPT_EXTENSIONS = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.SCRIPT_EXTENSIONS) },
            propertyValueGetter = { codePoint ->
                UnicodeScript.buildSetFromBitSet(
                    BitSet().apply {
                        UScript.getScriptExtensions(codePoint.value, this)
                    }
                )
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
        )

        val IDENTIFIER_TYPE = CodePointProperty(
            propertyNameGetter = { nameForIcuProperty(UProperty.IDENTIFIER_TYPE) },
            propertyValueGetter = { codePoint ->
                UnicodeIdentifierType.buildSet(
                    EnumSet.noneOf(IdentifierType::class.java).apply {
                        UCharacter.getIdentifierTypes(codePoint.value, this)
                    }
                )
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
        )

        override fun all(): Sequence<CodePointProperty<out Any>> = sequenceOf(
            SCRIPT_EXTENSIONS,
            IDENTIFIER_TYPE,
        )
    }

    // XXX: Is there a way to automate getting the full list without using reflection?
    fun all(): Sequence<CodePointProperty<*>> = sequenceOf(
        Booleans::all,
        Ints::all,
        Masks::all,
        Doubles::all,
        Strings::all,
        Other::all,
    ).flatMap { prop -> prop.invoke() }

}

