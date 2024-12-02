package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
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
import garden.ephemeral.glyphplay.unicode.enums.UnicodeVerticalOrientation
import garden.ephemeral.glyphplay.unicode.enums.UnicodeWordBreak
import garden.ephemeral.glyphplay.unicode.unihan.UnihanProperties
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.maps.shouldHaveSize

@Subject<CodePointProperties>()
class CodePointPropertiesTest : FreeSpec({
    "CodePointProperties" - {
        val properties = CodePointProperties.ofCodePoint("ば".firstToCodePoint()).storage

        "should contain all expected properties" {
            properties.shouldHaveSize(224)
            val unwrappedProperties = properties.map { (k, v) -> k to v.value }.toMap()
            unwrappedProperties.shouldContainExactly(
                mapOf(
                    // Booleans
                    UnicodeProperties.Booleans.NON_CHARACTER_CODE_POINT to false,
                    UnicodeProperties.Booleans.ALPHABETIC to true,
                    UnicodeProperties.Booleans.ASCII_HEX_DIGIT to false,
                    UnicodeProperties.Booleans.BIDI_CONTROL to false,
                    UnicodeProperties.Booleans.BIDI_MIRRORED to false,
                    UnicodeProperties.Booleans.DASH to false,
                    UnicodeProperties.Booleans.DEFAULT_IGNORABLE_CODE_POINT to false,
                    UnicodeProperties.Booleans.DEPRECATED to false,
                    UnicodeProperties.Booleans.DIACRITIC to false,
                    UnicodeProperties.Booleans.EXTENDER to false,
                    UnicodeProperties.Booleans.FULL_COMPOSITION_EXCLUSION to false,
                    UnicodeProperties.Booleans.GRAPHEME_BASE to true,
                    UnicodeProperties.Booleans.GRAPHEME_EXTEND to false,
                    UnicodeProperties.Booleans.GRAPHEME_LINK to false,
                    UnicodeProperties.Booleans.HEX_DIGIT to false,
                    UnicodeProperties.Booleans.HYPHEN to false,
                    UnicodeProperties.Booleans.ID_CONTINUE to true,
                    UnicodeProperties.Booleans.ID_START to true,
                    UnicodeProperties.Booleans.IDEOGRAPHIC to false,
                    UnicodeProperties.Booleans.IDS_BINARY_OPERATOR to false,
                    UnicodeProperties.Booleans.IDS_TRINARY_OPERATOR to false,
                    UnicodeProperties.Booleans.JOIN_CONTROL to false,
                    UnicodeProperties.Booleans.LOGICAL_ORDER_EXCEPTION to false,
                    UnicodeProperties.Booleans.LOWERCASE to false,
                    UnicodeProperties.Booleans.MATH to false,
                    UnicodeProperties.Booleans.NON_CHARACTER_CODE_POINT to false,
                    UnicodeProperties.Booleans.QUOTATION_MARK to false,
                    UnicodeProperties.Booleans.RADICAL to false,
                    UnicodeProperties.Booleans.SOFT_DOTTED to false,
                    UnicodeProperties.Booleans.TERMINAL_PUNCTUATION to false,
                    UnicodeProperties.Booleans.UNIFIED_IDEOGRAPH to false,
                    UnicodeProperties.Booleans.UPPERCASE to false,
                    UnicodeProperties.Booleans.WHITE_SPACE to false,
                    UnicodeProperties.Booleans.XID_CONTINUE to true,
                    UnicodeProperties.Booleans.XID_START to true,
                    UnicodeProperties.Booleans.CASE_SENSITIVE to false,
                    UnicodeProperties.Booleans.S_TERM to false,
                    UnicodeProperties.Booleans.VARIATION_SELECTOR to false,
                    UnicodeProperties.Booleans.NFD_INERT to false,
                    UnicodeProperties.Booleans.NFKD_INERT to false,
                    UnicodeProperties.Booleans.NFC_INERT to true,
                    UnicodeProperties.Booleans.NFKC_INERT to true,
                    UnicodeProperties.Booleans.SEGMENT_STARTER to true,
                    UnicodeProperties.Booleans.PATTERN_SYNTAX to false,
                    UnicodeProperties.Booleans.PATTERN_WHITE_SPACE to false,
                    UnicodeProperties.Booleans.POSIX_ALNUM to true,
                    UnicodeProperties.Booleans.POSIX_BLANK to false,
                    UnicodeProperties.Booleans.POSIX_GRAPH to true,
                    UnicodeProperties.Booleans.POSIX_PRINT to true,
                    UnicodeProperties.Booleans.POSIX_XDIGIT to false,
                    UnicodeProperties.Booleans.CASED to false,
                    UnicodeProperties.Booleans.CASE_IGNORABLE to false,
                    UnicodeProperties.Booleans.CHANGES_WHEN_LOWERCASED to false,
                    UnicodeProperties.Booleans.CHANGES_WHEN_UPPERCASED to false,
                    UnicodeProperties.Booleans.CHANGES_WHEN_TITLECASED to false,
                    UnicodeProperties.Booleans.CHANGES_WHEN_CASEFOLDED to false,
                    UnicodeProperties.Booleans.CHANGES_WHEN_CASEMAPPED to false,
                    UnicodeProperties.Booleans.CHANGES_WHEN_NFKC_CASEFOLDED to false,
                    UnicodeProperties.Booleans.EMOJI to false,
                    UnicodeProperties.Booleans.EMOJI_PRESENTATION to false,
                    UnicodeProperties.Booleans.EMOJI_MODIFIER to false,
                    UnicodeProperties.Booleans.EMOJI_MODIFIER_BASE to false,
                    UnicodeProperties.Booleans.EMOJI_COMPONENT to false,
                    UnicodeProperties.Booleans.REGIONAL_INDICATOR to false,
                    UnicodeProperties.Booleans.PREPENDED_CONCATENATION_MARK to false,
                    UnicodeProperties.Booleans.EXTENDED_PICTOGRAPHIC to false,
                    UnicodeProperties.Booleans.BASIC_EMOJI to false,
                    UnicodeProperties.Booleans.EMOJI_KEYCAP_SEQUENCE to false,
                    UnicodeProperties.Booleans.RGI_EMOJI_MODIFIER_SEQUENCE to false,
                    UnicodeProperties.Booleans.RGI_EMOJI_FLAG_SEQUENCE to false,
                    UnicodeProperties.Booleans.RGI_EMOJI_TAG_SEQUENCE to false,
                    UnicodeProperties.Booleans.RGI_EMOJI_ZWJ_SEQUENCE to false,
                    UnicodeProperties.Booleans.RGI_EMOJI to false,
                    UnicodeProperties.Booleans.IDS_UNARY_OPERATOR to false,
                    UnicodeProperties.Booleans.ID_COMPAT_MATH_START to false,
                    UnicodeProperties.Booleans.ID_COMPAT_MATH_CONTINUE to false,
                    UnicodeProperties.Booleans.MODIFIER_COMBINING_MARK to false,
                    // Ints
                    UnicodeProperties.Ints.BIDI_CLASS to UnicodeCharacterDirection.LEFT_TO_RIGHT,
                    UnicodeProperties.Ints.BLOCK to UnicodeBlock.HIRAGANA,
                    UnicodeProperties.Ints.CANONICAL_COMBINING_CLASS to UnicodeCanonicalCombiningClass.NOT_REORDERED,
                    UnicodeProperties.Ints.DECOMPOSITION_TYPE to UnicodeDecompositionType.CANONICAL,
                    UnicodeProperties.Ints.EAST_ASIAN_WIDTH to UnicodeEastAsianWidth.WIDE,
                    UnicodeProperties.Ints.GENERAL_CATEGORY to UnicodeCharacterCategory.OTHER_LETTER,
                    UnicodeProperties.Ints.JOINING_GROUP to UnicodeJoiningGroup.NO_JOINING_GROUP,
                    UnicodeProperties.Ints.JOINING_TYPE to UnicodeJoiningType.NON_JOINING,
                    UnicodeProperties.Ints.LINE_BREAK to UnicodeLineBreak.IDEOGRAPHIC,
                    UnicodeProperties.Ints.NUMERIC_TYPE to UnicodeNumericType.NONE,
                    UnicodeProperties.Ints.SCRIPT to UnicodeScript.HIRAGANA,
                    UnicodeProperties.Ints.HANGUL_SYLLABLE_TYPE to UnicodeHangulSyllableType.NOT_APPLICABLE,
                    UnicodeProperties.Ints.NFD_QUICK_CHECK to UnicodeQuickCheckResult.NO,
                    UnicodeProperties.Ints.NFKD_QUICK_CHECK to UnicodeQuickCheckResult.NO,
                    UnicodeProperties.Ints.NFC_QUICK_CHECK to UnicodeQuickCheckResult.YES,
                    UnicodeProperties.Ints.NFKC_QUICK_CHECK to UnicodeQuickCheckResult.YES,
                    UnicodeProperties.Ints.LEAD_CANONICAL_COMBINING_CLASS to UnicodeCanonicalCombiningClass.NOT_REORDERED,
                    UnicodeProperties.Ints.TRAIL_CANONICAL_COMBINING_CLASS to UnicodeCanonicalCombiningClass.KANA_VOICING,
                    UnicodeProperties.Ints.GRAPHEME_CLUSTER_BREAK to UnicodeGraphemeClusterBreak.OTHER,
                    UnicodeProperties.Ints.SENTENCE_BREAK to UnicodeSentenceBreak.OLETTER,
                    UnicodeProperties.Ints.WORD_BREAK to UnicodeWordBreak.OTHER,
                    UnicodeProperties.Ints.BIDI_PAIRED_BRACKET_TYPE to UnicodeBidiPairedBracketType.NONE,
                    UnicodeProperties.Ints.INDIC_POSITIONAL_CATEGORY to UnicodeIndicPositionalCategory.NA,
                    UnicodeProperties.Ints.INDIC_SYLLABIC_CATEGORY to UnicodeIndicSyllabicCategory.OTHER,
                    UnicodeProperties.Ints.VERTICAL_ORIENTATION to UnicodeVerticalOrientation.UPRIGHT,
                    UnicodeProperties.Ints.IDENTIFIER_STATUS to UnicodeIdentifierStatus.ALLOWED,
                    UnicodeProperties.Ints.INDIC_CONJUNCT_BREAK to UnicodeIndicConjunctBreak.NONE,
                    UnicodeProperties.Ints.PLANE to UnicodePlane.BASIC_MULTILINGUAL_PLANE,
                    // Masks
                    UnicodeProperties.Masks.GENERAL_CATEGORY_MASK to setOf(UnicodeCharacterCategory.OTHER_LETTER),
                    // Doubles
                    UnicodeProperties.Doubles.NUMERIC_VALUE to -1.23456789E8,
                    // Strings
                    UnicodeProperties.Strings.AGE to VersionInfo.UNICODE_1_1_0,
                    UnicodeProperties.Strings.BIDI_MIRRORING_GLYPH to "ば",
                    UnicodeProperties.Strings.CASE_FOLDING to "ば",
                    UnicodeProperties.Strings.LOWERCASE_MAPPING to "ば",
                    UnicodeProperties.Strings.NAME to "HIRAGANA LETTER BA",
                    UnicodeProperties.Strings.NAME_ALIAS to null,
                    UnicodeProperties.Strings.EXTENDED_NAME to null,
                    UnicodeProperties.Strings.SIMPLE_CASE_FOLDING to "ば",
                    UnicodeProperties.Strings.SIMPLE_LOWERCASE_MAPPING to "ば",
                    UnicodeProperties.Strings.SIMPLE_TITLECASE_MAPPING to "ば",
                    UnicodeProperties.Strings.SIMPLE_UPPERCASE_MAPPING to "ば",
                    UnicodeProperties.Strings.TITLECASE_MAPPING to "ば",
                    UnicodeProperties.Strings.UPPERCASE_MAPPING to "ば",
                    UnicodeProperties.Strings.BIDI_PAIRED_BRACKET to "ば",
                    UnicodeProperties.Strings.CANONICAL_DECOMPOSITION to "は\u3099",
                    UnicodeProperties.Strings.COMPATIBILITY_DECOMPOSITION to "は\u3099",
                    // Other
                    UnicodeProperties.Other.SCRIPT_EXTENSIONS to setOf(UnicodeScript.HIRAGANA),
                    UnicodeProperties.Other.IDENTIFIER_TYPE to setOf(UnicodeIdentifierType.RECOMMENDED),
                )
                        +
                        // Unihan
                        UnihanProperties.allCollections()
                            .flatMap { c -> c.all() }
                            .map { p -> p to null }
                            .toMap()
            )
        }
    }
})
