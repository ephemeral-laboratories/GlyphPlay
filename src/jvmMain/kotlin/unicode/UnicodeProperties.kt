package garden.ephemeral.glyphplay.unicode

import androidx.compose.runtime.Composable
import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacter.IdentifierType
import com.ibm.icu.lang.UProperty
import com.ibm.icu.lang.UScript
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.text.Normalizer2
import com.ibm.icu.util.ULocale
import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.VersionInfoSummary
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
import garden.ephemeral.glyphplay.unicode.enums.UnicodeIndicConjunctBreak
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
import garden.ephemeral.glyphplay.unicode.unihan.UnihanProperties
import garden.ephemeral.glyphplay.util.formatToString
import garden.ephemeral.glyphplay.util.prettyPrintName
import garden.ephemeral.glyphs.glyphplay.generated.resources.Res
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_AGE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_ALPHABETIC
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_ASCII_HEX_DIGIT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BASIC_EMOJI
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BIDI_CLASS
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BIDI_CONTROL
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BIDI_MIRRORED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BIDI_MIRRORING_GLYPH
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BIDI_PAIRED_BRACKET
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BIDI_PAIRED_BRACKET_TYPE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_BLOCK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CANONICAL_COMBINING_CLASS
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CANONICAL_DECOMPOSITION
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CASED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CASE_FOLDING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CASE_IGNORABLE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CASE_SENSITIVE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CHANGES_WHEN_CASEFOLDED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CHANGES_WHEN_CASEMAPPED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CHANGES_WHEN_LOWERCASED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CHANGES_WHEN_NFKC_CASEFOLDED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CHANGES_WHEN_TITLECASED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_CHANGES_WHEN_UPPERCASED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_COMPATIBILITY_DECOMPOSITION
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_DASH
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_DECOMPOSITION_TYPE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_DEFAULT_IGNORABLE_CODE_POINT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_DEPRECATED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_DIACRITIC
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EAST_ASIAN_WIDTH
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EMOJI
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EMOJI_COMPONENT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EMOJI_KEYCAP_SEQUENCE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EMOJI_MODIFIER
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EMOJI_MODIFIER_BASE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EMOJI_PRESENTATION
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EXTENDED_NAME
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EXTENDED_PICTOGRAPHIC
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_EXTENDER
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_FULL_COMPOSITION_EXCLUSION
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_GENERAL_CATEGORY
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_GENERAL_CATEGORY_MASK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_GRAPHEME_BASE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_GRAPHEME_CLUSTER_BREAK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_GRAPHEME_EXTEND
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_GRAPHEME_LINK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_HANGUL_SYLLABLE_TYPE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_HEX_DIGIT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_HYPHEN
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_IDENTIFIER_STATUS
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_IDENTIFIER_TYPE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_IDEOGRAPHIC
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_IDS_BINARY_OPERATOR
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_IDS_TRINARY_OPERATOR
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_IDS_UNARY_OPERATOR
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_ID_COMPAT_MATH_CONTINUE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_ID_COMPAT_MATH_START
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_ID_CONTINUE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_ID_START
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_INDIC_CONJUNCT_BREAK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_INDIC_POSITIONAL_CATEGORY
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_INDIC_SYLLABIC_CATEGORY
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_JOINING_GROUP
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_JOINING_TYPE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_JOIN_CONTROL
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_LEAD_CANONICAL_COMBINING_CLASS
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_LINE_BREAK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_LOGICAL_ORDER_EXCEPTION
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_LOWERCASE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_LOWERCASE_MAPPING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_MATH
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_MODIFIER_COMBINING_MARK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NAME
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NAME_ALIAS
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFC_INERT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFC_QUICK_CHECK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFD_INERT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFD_QUICK_CHECK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFKC_INERT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFKC_QUICK_CHECK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFKD_INERT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NFKD_QUICK_CHECK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NONCHARACTER_CODE_POINT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NUMERIC_TYPE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_NumericValue
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_PATTERN_SYNTAX
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_PATTERN_WHITE_SPACE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_PLANE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_POSIX_ALNUM
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_POSIX_BLANK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_POSIX_GRAPH
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_POSIX_PRINT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_POSIX_XDIGIT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_PREPENDED_CONCATENATION_MARK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_QUOTATION_MARK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_RADICAL
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_REGIONAL_INDICATOR
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_RGI_EMOJI
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_RGI_EMOJI_FLAG_SEQUENCE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_RGI_EMOJI_MODIFIER_SEQUENCE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_RGI_EMOJI_TAG_SEQUENCE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_RGI_EMOJI_ZWJ_SEQUENCE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SCRIPT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SCRIPT_EXTENSIONS
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SEGMENT_STARTER
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SENTENCE_BREAK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SIMPLE_CASE_FOLDING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SIMPLE_LOWERCASE_MAPPING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SIMPLE_TITLECASE_MAPPING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SIMPLE_UPPERCASE_MAPPING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_SOFT_DOTTED
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_S_TERM
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_TERMINAL_PUNCTUATION
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_TITLECASE_MAPPING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_TRAIL_CANONICAL_COMBINING_CLASS
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_UNIFIED_IDEOGRAPH
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_UPPERCASE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_UPPERCASE_MAPPING
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_VARIATION_SELECTOR
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_VERTICAL_ORIENTATION
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_WHITE_SPACE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_WORD_BREAK
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_XID_CONTINUE
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_XID_START
import garden.ephemeral.glyphs.glyphplay.generated.resources.value_boolean_no
import garden.ephemeral.glyphs.glyphplay.generated.resources.value_boolean_yes
import garden.ephemeral.glyphs.glyphplay.generated.resources.value_number_no_numeric
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import java.util.BitSet
import java.util.EnumSet

object UnicodeProperties {

    private val ENUM_SET_DESCRIBER = @Composable { value: Set<UnicodeValueEnum<*>> ->
        value.map(UnicodeValueEnum<*>::longName).formatToString()
    }

    /**
     * Boolean properties.
     */
    object Booleans : CodePointPropertyCollection<Boolean> {
        private fun icuBooleanProperty(displayNameResource: StringResource, icuPropertyId: Int) = CodePointProperty(
            displayNameResource = displayNameResource,
            propertyValueGetter = { codePoint -> UCharacter.getIntPropertyValue(codePoint.value, icuPropertyId) != 0 },
            propertyValueDescriber = @Composable { value: Boolean ->
                stringResource(if (value) Res.string.value_boolean_yes else Res.string.value_boolean_no)
            })

        val ALPHABETIC = icuBooleanProperty(Res.string.name_ALPHABETIC, UProperty.ALPHABETIC)
        val ASCII_HEX_DIGIT = icuBooleanProperty(Res.string.name_ASCII_HEX_DIGIT, UProperty.ASCII_HEX_DIGIT)
        val BIDI_CONTROL = icuBooleanProperty(Res.string.name_BIDI_CONTROL, UProperty.BIDI_CONTROL)
        val BIDI_MIRRORED = icuBooleanProperty(Res.string.name_BIDI_MIRRORED, UProperty.BIDI_MIRRORED)
        val DASH = icuBooleanProperty(Res.string.name_DASH, UProperty.DASH)
        val DEFAULT_IGNORABLE_CODE_POINT =
            icuBooleanProperty(Res.string.name_DEFAULT_IGNORABLE_CODE_POINT, UProperty.DEFAULT_IGNORABLE_CODE_POINT)
        val DEPRECATED = icuBooleanProperty(Res.string.name_DEPRECATED, UProperty.DEPRECATED)
        val DIACRITIC = icuBooleanProperty(Res.string.name_DIACRITIC, UProperty.DIACRITIC)
        val EXTENDER = icuBooleanProperty(Res.string.name_EXTENDER, UProperty.EXTENDER)
        val FULL_COMPOSITION_EXCLUSION =
            icuBooleanProperty(Res.string.name_FULL_COMPOSITION_EXCLUSION, UProperty.FULL_COMPOSITION_EXCLUSION)
        val GRAPHEME_BASE = icuBooleanProperty(Res.string.name_GRAPHEME_BASE, UProperty.GRAPHEME_BASE)
        val GRAPHEME_EXTEND = icuBooleanProperty(Res.string.name_GRAPHEME_EXTEND, UProperty.GRAPHEME_EXTEND)
        val GRAPHEME_LINK = icuBooleanProperty(Res.string.name_GRAPHEME_LINK, UProperty.GRAPHEME_LINK)
        val HEX_DIGIT = icuBooleanProperty(Res.string.name_HEX_DIGIT, UProperty.HEX_DIGIT)
        val HYPHEN = icuBooleanProperty(Res.string.name_HYPHEN, UProperty.HYPHEN)
        val ID_CONTINUE = icuBooleanProperty(Res.string.name_ID_CONTINUE, UProperty.ID_CONTINUE)
        val ID_START = icuBooleanProperty(Res.string.name_ID_START, UProperty.ID_START)
        val IDEOGRAPHIC = icuBooleanProperty(Res.string.name_IDEOGRAPHIC, UProperty.IDEOGRAPHIC)
        val IDS_BINARY_OPERATOR = icuBooleanProperty(Res.string.name_IDS_BINARY_OPERATOR, UProperty.IDS_BINARY_OPERATOR)
        val IDS_TRINARY_OPERATOR =
            icuBooleanProperty(Res.string.name_IDS_TRINARY_OPERATOR, UProperty.IDS_TRINARY_OPERATOR)
        val JOIN_CONTROL = icuBooleanProperty(Res.string.name_JOIN_CONTROL, UProperty.JOIN_CONTROL)
        val LOGICAL_ORDER_EXCEPTION =
            icuBooleanProperty(Res.string.name_LOGICAL_ORDER_EXCEPTION, UProperty.LOGICAL_ORDER_EXCEPTION)
        val LOWERCASE = icuBooleanProperty(Res.string.name_LOWERCASE, UProperty.LOWERCASE)
        val MATH = icuBooleanProperty(Res.string.name_MATH, UProperty.MATH)
        val NON_CHARACTER_CODE_POINT =
            icuBooleanProperty(Res.string.name_NONCHARACTER_CODE_POINT, UProperty.NONCHARACTER_CODE_POINT)
        val QUOTATION_MARK = icuBooleanProperty(Res.string.name_QUOTATION_MARK, UProperty.QUOTATION_MARK)
        val RADICAL = icuBooleanProperty(Res.string.name_RADICAL, UProperty.RADICAL)
        val SOFT_DOTTED = icuBooleanProperty(Res.string.name_SOFT_DOTTED, UProperty.SOFT_DOTTED)
        val TERMINAL_PUNCTUATION =
            icuBooleanProperty(Res.string.name_TERMINAL_PUNCTUATION, UProperty.TERMINAL_PUNCTUATION)
        val UNIFIED_IDEOGRAPH = icuBooleanProperty(Res.string.name_UNIFIED_IDEOGRAPH, UProperty.UNIFIED_IDEOGRAPH)
        val UPPERCASE = icuBooleanProperty(Res.string.name_UPPERCASE, UProperty.UPPERCASE)
        val WHITE_SPACE = icuBooleanProperty(Res.string.name_WHITE_SPACE, UProperty.WHITE_SPACE)
        val XID_CONTINUE = icuBooleanProperty(Res.string.name_XID_CONTINUE, UProperty.XID_CONTINUE)
        val XID_START = icuBooleanProperty(Res.string.name_XID_START, UProperty.XID_START)
        val CASE_SENSITIVE = icuBooleanProperty(Res.string.name_CASE_SENSITIVE, UProperty.CASE_SENSITIVE)
        val S_TERM = icuBooleanProperty(Res.string.name_S_TERM, UProperty.S_TERM)
        val VARIATION_SELECTOR = icuBooleanProperty(Res.string.name_VARIATION_SELECTOR, UProperty.VARIATION_SELECTOR)
        val NFD_INERT = icuBooleanProperty(Res.string.name_NFD_INERT, UProperty.NFD_INERT)
        val NFKD_INERT = icuBooleanProperty(Res.string.name_NFKD_INERT, UProperty.NFKD_INERT)
        val NFC_INERT = icuBooleanProperty(Res.string.name_NFC_INERT, UProperty.NFC_INERT)
        val NFKC_INERT = icuBooleanProperty(Res.string.name_NFKC_INERT, UProperty.NFKC_INERT)
        val SEGMENT_STARTER = icuBooleanProperty(Res.string.name_SEGMENT_STARTER, UProperty.SEGMENT_STARTER)
        val PATTERN_SYNTAX = icuBooleanProperty(Res.string.name_PATTERN_SYNTAX, UProperty.PATTERN_SYNTAX)
        val PATTERN_WHITE_SPACE = icuBooleanProperty(Res.string.name_PATTERN_WHITE_SPACE, UProperty.PATTERN_WHITE_SPACE)
        val POSIX_ALNUM = icuBooleanProperty(Res.string.name_POSIX_ALNUM, UProperty.POSIX_ALNUM)
        val POSIX_BLANK = icuBooleanProperty(Res.string.name_POSIX_BLANK, UProperty.POSIX_BLANK)
        val POSIX_GRAPH = icuBooleanProperty(Res.string.name_POSIX_GRAPH, UProperty.POSIX_GRAPH)
        val POSIX_PRINT = icuBooleanProperty(Res.string.name_POSIX_PRINT, UProperty.POSIX_PRINT)
        val POSIX_XDIGIT = icuBooleanProperty(Res.string.name_POSIX_XDIGIT, UProperty.POSIX_XDIGIT)
        val CASED = icuBooleanProperty(Res.string.name_CASED, UProperty.CASED)
        val CASE_IGNORABLE = icuBooleanProperty(Res.string.name_CASE_IGNORABLE, UProperty.CASE_IGNORABLE)
        val CHANGES_WHEN_LOWERCASED =
            icuBooleanProperty(Res.string.name_CHANGES_WHEN_LOWERCASED, UProperty.CHANGES_WHEN_LOWERCASED)
        val CHANGES_WHEN_UPPERCASED =
            icuBooleanProperty(Res.string.name_CHANGES_WHEN_UPPERCASED, UProperty.CHANGES_WHEN_UPPERCASED)
        val CHANGES_WHEN_TITLECASED =
            icuBooleanProperty(Res.string.name_CHANGES_WHEN_TITLECASED, UProperty.CHANGES_WHEN_TITLECASED)
        val CHANGES_WHEN_CASEFOLDED =
            icuBooleanProperty(Res.string.name_CHANGES_WHEN_CASEFOLDED, UProperty.CHANGES_WHEN_CASEFOLDED)
        val CHANGES_WHEN_CASEMAPPED =
            icuBooleanProperty(Res.string.name_CHANGES_WHEN_CASEMAPPED, UProperty.CHANGES_WHEN_CASEMAPPED)
        val CHANGES_WHEN_NFKC_CASEFOLDED =
            icuBooleanProperty(Res.string.name_CHANGES_WHEN_NFKC_CASEFOLDED, UProperty.CHANGES_WHEN_NFKC_CASEFOLDED)
        val EMOJI = icuBooleanProperty(Res.string.name_EMOJI, UProperty.EMOJI)
        val EMOJI_PRESENTATION = icuBooleanProperty(Res.string.name_EMOJI_PRESENTATION, UProperty.EMOJI_PRESENTATION)
        val EMOJI_MODIFIER = icuBooleanProperty(Res.string.name_EMOJI_MODIFIER, UProperty.EMOJI_MODIFIER)
        val EMOJI_MODIFIER_BASE = icuBooleanProperty(Res.string.name_EMOJI_MODIFIER_BASE, UProperty.EMOJI_MODIFIER_BASE)
        val EMOJI_COMPONENT = icuBooleanProperty(Res.string.name_EMOJI_COMPONENT, UProperty.EMOJI_COMPONENT)
        val REGIONAL_INDICATOR = icuBooleanProperty(Res.string.name_REGIONAL_INDICATOR, UProperty.REGIONAL_INDICATOR)
        val PREPENDED_CONCATENATION_MARK =
            icuBooleanProperty(Res.string.name_PREPENDED_CONCATENATION_MARK, UProperty.PREPENDED_CONCATENATION_MARK)
        val EXTENDED_PICTOGRAPHIC =
            icuBooleanProperty(Res.string.name_EXTENDED_PICTOGRAPHIC, UProperty.EXTENDED_PICTOGRAPHIC)
        val BASIC_EMOJI = icuBooleanProperty(Res.string.name_BASIC_EMOJI, UProperty.BASIC_EMOJI)
        val EMOJI_KEYCAP_SEQUENCE =
            icuBooleanProperty(Res.string.name_EMOJI_KEYCAP_SEQUENCE, UProperty.EMOJI_KEYCAP_SEQUENCE)
        val RGI_EMOJI_MODIFIER_SEQUENCE =
            icuBooleanProperty(Res.string.name_RGI_EMOJI_MODIFIER_SEQUENCE, UProperty.RGI_EMOJI_MODIFIER_SEQUENCE)
        val RGI_EMOJI_FLAG_SEQUENCE =
            icuBooleanProperty(Res.string.name_RGI_EMOJI_FLAG_SEQUENCE, UProperty.RGI_EMOJI_FLAG_SEQUENCE)
        val RGI_EMOJI_TAG_SEQUENCE =
            icuBooleanProperty(Res.string.name_RGI_EMOJI_TAG_SEQUENCE, UProperty.RGI_EMOJI_TAG_SEQUENCE)
        val RGI_EMOJI_ZWJ_SEQUENCE =
            icuBooleanProperty(Res.string.name_RGI_EMOJI_ZWJ_SEQUENCE, UProperty.RGI_EMOJI_ZWJ_SEQUENCE)
        val RGI_EMOJI = icuBooleanProperty(Res.string.name_RGI_EMOJI, UProperty.RGI_EMOJI)
        val IDS_UNARY_OPERATOR = icuBooleanProperty(Res.string.name_IDS_UNARY_OPERATOR, UProperty.IDS_UNARY_OPERATOR)
        val ID_COMPAT_MATH_START =
            icuBooleanProperty(Res.string.name_ID_COMPAT_MATH_START, UProperty.ID_COMPAT_MATH_START)
        val ID_COMPAT_MATH_CONTINUE =
            icuBooleanProperty(Res.string.name_ID_COMPAT_MATH_CONTINUE, UProperty.ID_COMPAT_MATH_CONTINUE)
        val MODIFIER_COMBINING_MARK =
            icuBooleanProperty(Res.string.name_MODIFIER_COMBINING_MARK, UProperty.MODIFIER_COMBINING_MARK)

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
            POSIX_ALNUM,
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
            MODIFIER_COMBINING_MARK,
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
            displayNameResource: StringResource,
            icuPropertyId: Int,
            enumValueCompanion: IcuUnicodeValueEnum.Companion<T>
        ) = CodePointProperty(
            displayNameResource = displayNameResource,
            propertyValueGetter = { codePoint ->
                val icuPropertyValue = UCharacter.getIntPropertyValue(codePoint.value, icuPropertyId)
                enumValueCompanion.ofIcuValue(icuPropertyValue)
            },
            propertyValueDescriber = { value -> value.longName },
        )

        val BIDI_CLASS =
            icuEnumProperty(Res.string.name_BIDI_CLASS, UProperty.BIDI_CLASS, UnicodeCharacterDirection.Companion)
        val BLOCK = icuEnumProperty(Res.string.name_BLOCK, UProperty.BLOCK, UnicodeBlock.Companion)
        val CANONICAL_COMBINING_CLASS = icuEnumProperty(
            Res.string.name_CANONICAL_COMBINING_CLASS,
            UProperty.CANONICAL_COMBINING_CLASS,
            UnicodeCanonicalCombiningClass.Companion
        )
        val DECOMPOSITION_TYPE = icuEnumProperty(
            Res.string.name_DECOMPOSITION_TYPE, UProperty.DECOMPOSITION_TYPE, UnicodeDecompositionType.Companion
        )
        val EAST_ASIAN_WIDTH = icuEnumProperty(
            Res.string.name_EAST_ASIAN_WIDTH, UProperty.EAST_ASIAN_WIDTH, UnicodeEastAsianWidth.Companion
        )
        val GENERAL_CATEGORY = icuEnumProperty(
            Res.string.name_GENERAL_CATEGORY, UProperty.GENERAL_CATEGORY, UnicodeCharacterCategory.Companion
        )
        val JOINING_GROUP =
            icuEnumProperty(Res.string.name_JOINING_GROUP, UProperty.JOINING_GROUP, UnicodeJoiningGroup.Companion)
        val JOINING_TYPE =
            icuEnumProperty(Res.string.name_JOINING_TYPE, UProperty.JOINING_TYPE, UnicodeJoiningType.Companion)
        val LINE_BREAK = icuEnumProperty(Res.string.name_LINE_BREAK, UProperty.LINE_BREAK, UnicodeLineBreak.Companion)
        val NUMERIC_TYPE =
            icuEnumProperty(Res.string.name_NUMERIC_TYPE, UProperty.NUMERIC_TYPE, UnicodeNumericType.Companion)
        val SCRIPT = icuEnumProperty(Res.string.name_SCRIPT, UProperty.SCRIPT, UnicodeScript.Companion)
        val HANGUL_SYLLABLE_TYPE = icuEnumProperty(
            Res.string.name_HANGUL_SYLLABLE_TYPE, UProperty.HANGUL_SYLLABLE_TYPE, UnicodeHangulSyllableType.Companion
        )
        val NFD_QUICK_CHECK = icuEnumProperty(
            Res.string.name_NFD_QUICK_CHECK, UProperty.NFD_QUICK_CHECK, UnicodeQuickCheckResult.Companion
        )
        val NFKD_QUICK_CHECK = icuEnumProperty(
            Res.string.name_NFKD_QUICK_CHECK, UProperty.NFKD_QUICK_CHECK, UnicodeQuickCheckResult.Companion
        )
        val NFC_QUICK_CHECK = icuEnumProperty(
            Res.string.name_NFC_QUICK_CHECK, UProperty.NFC_QUICK_CHECK, UnicodeQuickCheckResult.Companion
        )
        val NFKC_QUICK_CHECK = icuEnumProperty(
            Res.string.name_NFKC_QUICK_CHECK, UProperty.NFKC_QUICK_CHECK, UnicodeQuickCheckResult.Companion
        )
        val LEAD_CANONICAL_COMBINING_CLASS = icuEnumProperty(
            Res.string.name_LEAD_CANONICAL_COMBINING_CLASS,
            UProperty.LEAD_CANONICAL_COMBINING_CLASS,
            UnicodeCanonicalCombiningClass.Companion
        )
        val TRAIL_CANONICAL_COMBINING_CLASS = icuEnumProperty(
            Res.string.name_TRAIL_CANONICAL_COMBINING_CLASS,
            UProperty.TRAIL_CANONICAL_COMBINING_CLASS,
            UnicodeCanonicalCombiningClass.Companion
        )
        val GRAPHEME_CLUSTER_BREAK = icuEnumProperty(
            Res.string.name_GRAPHEME_CLUSTER_BREAK,
            UProperty.GRAPHEME_CLUSTER_BREAK,
            UnicodeGraphemeClusterBreak.Companion
        )
        val SENTENCE_BREAK =
            icuEnumProperty(Res.string.name_SENTENCE_BREAK, UProperty.SENTENCE_BREAK, UnicodeSentenceBreak.Companion)
        val WORD_BREAK = icuEnumProperty(Res.string.name_WORD_BREAK, UProperty.WORD_BREAK, UnicodeWordBreak.Companion)
        val BIDI_PAIRED_BRACKET_TYPE = icuEnumProperty(
            Res.string.name_BIDI_PAIRED_BRACKET_TYPE,
            UProperty.BIDI_PAIRED_BRACKET_TYPE,
            UnicodeBidiPairedBracketType.Companion
        )
        val INDIC_POSITIONAL_CATEGORY = icuEnumProperty(
            Res.string.name_INDIC_POSITIONAL_CATEGORY,
            UProperty.INDIC_POSITIONAL_CATEGORY,
            UnicodeIndicPositionalCategory.Companion
        )
        val INDIC_SYLLABIC_CATEGORY = icuEnumProperty(
            Res.string.name_INDIC_SYLLABIC_CATEGORY,
            UProperty.INDIC_SYLLABIC_CATEGORY,
            UnicodeIndicSyllabicCategory.Companion
        )
        val VERTICAL_ORIENTATION = icuEnumProperty(
            Res.string.name_VERTICAL_ORIENTATION, UProperty.VERTICAL_ORIENTATION, UnicodeVerticalOrientation.Companion
        )
        val IDENTIFIER_STATUS = icuEnumProperty(
            Res.string.name_IDENTIFIER_STATUS, UProperty.IDENTIFIER_STATUS, UnicodeIdentifierStatus.Companion
        )
        val INDIC_CONJUNCT_BREAK = icuEnumProperty(
            Res.string.name_INDIC_CONJUNCT_BREAK, UProperty.INDIC_CONJUNCT_BREAK, UnicodeIndicConjunctBreak.Companion
        )

        val PLANE = CodePointProperty(
            displayNameResource = Res.string.name_PLANE,
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
            INDIC_CONJUNCT_BREAK,
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
            displayNameResource = Res.string.name_GENERAL_CATEGORY_MASK,
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
        private val DOUBLE_DESCRIBER = @Composable { value: Double ->
            if (value == UCharacter.NO_NUMERIC_VALUE) {
                stringResource(Res.string.value_number_no_numeric)
            } else {
                // TODO: Not ideal for locale reasons. Consider European locales, e.g. German
                value.toString()
            }
        }

        val NUMERIC_VALUE = CodePointProperty(
            displayNameResource = Res.string.name_NumericValue,
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
        private val STRING_DESCRIBER = @Composable { value: String -> value }
        private val NAME_DESCRIBER = @Composable { value: String -> value.prettyPrintName() }

        val AGE = CodePointProperty<VersionInfo>(
            displayNameResource = Res.string.name_AGE,
            propertyValueGetter = { cp -> UCharacter.getAge(cp.value) },
            propertyValueDescriber = { versionInfo -> VersionInfoSummary.of(versionInfo).fullDescription },
        )

        val BIDI_MIRRORING_GLYPH = CodePointProperty(
            displayNameResource = Res.string.name_BIDI_MIRRORING_GLYPH,
            propertyValueGetter = { cp -> CodePoint(UCharacter.getMirror(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val CASE_FOLDING = CodePointProperty(
            displayNameResource = Res.string.name_CASE_FOLDING,
            propertyValueGetter = { cp -> UCharacter.foldCase(cp.toString(), true) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        // ISO_COMMENT omitted as ICU4J deprecated it

        val LOWERCASE_MAPPING = CodePointProperty(
            displayNameResource = Res.string.name_LOWERCASE_MAPPING,
            propertyValueGetter = { cp -> UCharacter.toLowerCase(ULocale.ROOT, cp.toString()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val NAME = CodePointProperty(
            displayNameResource = Res.string.name_NAME,
            propertyValueGetter = { cp -> UCharacter.getName(cp.value) ?: "" },
            propertyValueDescriber = NAME_DESCRIBER,
        )

        // Not present in ICU4J as a property.
        val NAME_ALIAS = CodePointProperty(
            displayNameResource = Res.string.name_NAME_ALIAS,
            propertyValueGetter = { codePoint ->
                UCharacter.getNameAlias(codePoint.value)
                    ?.takeIf { n -> n != UCharacter.getName(codePoint.value) }
            },
            propertyValueDescriber = NAME_DESCRIBER,
        )

        // Not present in ICU4J as a property.
        val EXTENDED_NAME = CodePointProperty(
            displayNameResource = Res.string.name_EXTENDED_NAME,
            propertyValueGetter = { codePoint ->
                UCharacter.getExtendedName(codePoint.value)
                    ?.takeIf { n -> n != UCharacter.getName(codePoint.value) }
            },
            propertyValueDescriber = NAME_DESCRIBER,
        )

        val SIMPLE_CASE_FOLDING = CodePointProperty(
            displayNameResource = Res.string.name_SIMPLE_CASE_FOLDING,
            propertyValueGetter = { cp -> CodePoint(UCharacter.foldCase(cp.value, true)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_LOWERCASE_MAPPING = CodePointProperty(
            displayNameResource = Res.string.name_SIMPLE_LOWERCASE_MAPPING,
            propertyValueGetter = { cp -> CodePoint(UCharacter.toLowerCase(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_TITLECASE_MAPPING = CodePointProperty(
            displayNameResource = Res.string.name_SIMPLE_TITLECASE_MAPPING,
            propertyValueGetter = { cp -> CodePoint(UCharacter.toTitleCase(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val SIMPLE_UPPERCASE_MAPPING = CodePointProperty(
            displayNameResource = Res.string.name_SIMPLE_UPPERCASE_MAPPING,
            propertyValueGetter = { cp -> CodePoint(UCharacter.toUpperCase(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val TITLECASE_MAPPING = CodePointProperty(
            displayNameResource = Res.string.name_TITLECASE_MAPPING,
            propertyValueGetter = { codePoint ->
                UCharacter.toTitleCase(
                    ULocale.ROOT, codePoint.toString(), BreakIterator.getWordInstance(ULocale.ROOT)
                )
            },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        // UNICODE_1_NAME omitted as ICU4J deprecated it

        val UPPERCASE_MAPPING = CodePointProperty(
            displayNameResource = Res.string.name_UPPERCASE_MAPPING,
            propertyValueGetter = { cp -> UCharacter.toUpperCase(ULocale.ROOT, cp.toString()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val BIDI_PAIRED_BRACKET = CodePointProperty(
            displayNameResource = Res.string.name_BIDI_PAIRED_BRACKET,
            propertyValueGetter = { cp -> CodePoint(UCharacter.getBidiPairedBracket(cp.value)).toString() },
            propertyValueDescriber = STRING_DESCRIBER,
        )

        val CANONICAL_DECOMPOSITION = CodePointProperty(
            displayNameResource = Res.string.name_CANONICAL_DECOMPOSITION,
            propertyValueGetter = { cp -> Normalizer2.getNFDInstance().normalize(cp.toString()) },
            propertyValueDescriber = STRING_DESCRIBER,
        )
        val COMPATIBILITY_DECOMPOSITION = CodePointProperty(
            displayNameResource = Res.string.name_COMPATIBILITY_DECOMPOSITION,
            propertyValueGetter = { cp -> Normalizer2.getNFKDInstance().normalize(cp.toString()) },
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
            displayNameResource = Res.string.name_SCRIPT_EXTENSIONS,
            propertyValueGetter = { codePoint ->
                UnicodeScript.buildSetFromBitSet(BitSet().apply {
                    UScript.getScriptExtensions(codePoint.value, this)
                })
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
        )

        val IDENTIFIER_TYPE = CodePointProperty(
            displayNameResource = Res.string.name_IDENTIFIER_TYPE,
            propertyValueGetter = { codePoint ->
                UnicodeIdentifierType.buildSet(EnumSet.noneOf(IdentifierType::class.java).apply {
                    UCharacter.getIdentifierTypes(codePoint.value, this)
                })
            },
            propertyValueDescriber = ENUM_SET_DESCRIBER,
        )

        override fun all(): Sequence<CodePointProperty<out Any>> = sequenceOf(
            SCRIPT_EXTENSIONS,
            IDENTIFIER_TYPE,
        )
    }

    // XXX: Is there a way to automate getting the full list without using reflection?
    private fun allLocal() = sequenceOf(Booleans, Ints, Masks, Doubles, Strings, Other)

    fun all(): Sequence<CodePointProperty<*>> = (allLocal() + UnihanProperties.allCollections())
        .flatMap { collection -> collection.all() }

}

