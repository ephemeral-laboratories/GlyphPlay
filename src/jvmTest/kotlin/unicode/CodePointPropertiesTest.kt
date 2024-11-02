package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.search2.CodePointProperties
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
import garden.ephemeral.glyphplay.unicode.enums.UnicodeVerticalOrientation
import garden.ephemeral.glyphplay.unicode.enums.UnicodeWordBreak
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.maps.shouldHaveSize

class CodePointPropertiesTest : FreeSpec({
    "CodePointProperties" - {
        val properties = CodePointProperties.ofCodePoint("ば".codePointAt(0)).storage

        fun value(value: Any, description: String = value.toString()) =
            UnicodePropertyValue(value = value, description = description)

        "should contain all expected properties" {
            properties.shouldHaveSize(122)
            properties.shouldContainExactly(
                mapOf(
                    // Booleans
                    UnicodeProperties.Booleans.NON_CHARACTER_CODE_POINT to value(false, "No"),
                    UnicodeProperties.Booleans.ALPHABETIC to value(true, "Yes"),
                    UnicodeProperties.Booleans.ASCII_HEX_DIGIT to value(false, "No"),
                    UnicodeProperties.Booleans.BIDI_CONTROL to value(false, "No"),
                    UnicodeProperties.Booleans.BIDI_MIRRORED to value(false, "No"),
                    UnicodeProperties.Booleans.DASH to value(false, "No"),
                    UnicodeProperties.Booleans.DEFAULT_IGNORABLE_CODE_POINT to value(false, "No"),
                    UnicodeProperties.Booleans.DEPRECATED to value(false, "No"),
                    UnicodeProperties.Booleans.DIACRITIC to value(false, "No"),
                    UnicodeProperties.Booleans.EXTENDER to value(false, "No"),
                    UnicodeProperties.Booleans.FULL_COMPOSITION_EXCLUSION to value(false, "No"),
                    UnicodeProperties.Booleans.GRAPHEME_BASE to value(true, "Yes"),
                    UnicodeProperties.Booleans.GRAPHEME_EXTEND to value(false, "No"),
                    UnicodeProperties.Booleans.GRAPHEME_LINK to value(false, "No"),
                    UnicodeProperties.Booleans.HEX_DIGIT to value(false, "No"),
                    UnicodeProperties.Booleans.HYPHEN to value(false, "No"),
                    UnicodeProperties.Booleans.ID_CONTINUE to value(true, "Yes"),
                    UnicodeProperties.Booleans.ID_START to value(true, "Yes"),
                    UnicodeProperties.Booleans.IDEOGRAPHIC to value(false, "No"),
                    UnicodeProperties.Booleans.IDS_BINARY_OPERATOR to value(false, "No"),
                    UnicodeProperties.Booleans.IDS_TRINARY_OPERATOR to value(false, "No"),
                    UnicodeProperties.Booleans.JOIN_CONTROL to value(false, "No"),
                    UnicodeProperties.Booleans.LOGICAL_ORDER_EXCEPTION to value(false, "No"),
                    UnicodeProperties.Booleans.LOWERCASE to value(false, "No"),
                    UnicodeProperties.Booleans.MATH to value(false, "No"),
                    UnicodeProperties.Booleans.NON_CHARACTER_CODE_POINT to value(false, "No"),
                    UnicodeProperties.Booleans.QUOTATION_MARK to value(false, "No"),
                    UnicodeProperties.Booleans.RADICAL to value(false, "No"),
                    UnicodeProperties.Booleans.SOFT_DOTTED to value(false, "No"),
                    UnicodeProperties.Booleans.TERMINAL_PUNCTUATION to value(false, "No"),
                    UnicodeProperties.Booleans.UNIFIED_IDEOGRAPH to value(false, "No"),
                    UnicodeProperties.Booleans.UPPERCASE to value(false, "No"),
                    UnicodeProperties.Booleans.WHITE_SPACE to value(false, "No"),
                    UnicodeProperties.Booleans.XID_CONTINUE to value(true, "Yes"),
                    UnicodeProperties.Booleans.XID_START to value(true, "Yes"),
                    UnicodeProperties.Booleans.CASE_SENSITIVE to value(false, "No"),
                    UnicodeProperties.Booleans.S_TERM to value(false, "No"),
                    UnicodeProperties.Booleans.VARIATION_SELECTOR to value(false, "No"),
                    UnicodeProperties.Booleans.NFD_INERT to value(false, "No"),
                    UnicodeProperties.Booleans.NFKD_INERT to value(false, "No"),
                    UnicodeProperties.Booleans.NFC_INERT to value(true, "Yes"),
                    UnicodeProperties.Booleans.NFKC_INERT to value(true, "Yes"),
                    UnicodeProperties.Booleans.SEGMENT_STARTER to value(true, "Yes"),
                    UnicodeProperties.Booleans.PATTERN_SYNTAX to value(false, "No"),
                    UnicodeProperties.Booleans.PATTERN_WHITE_SPACE to value(false, "No"),
                    UnicodeProperties.Booleans.POSIX_ALPHANUM to value(true, "Yes"),
                    UnicodeProperties.Booleans.POSIX_BLANK to value(false, "No"),
                    UnicodeProperties.Booleans.POSIX_GRAPH to value(true, "Yes"),
                    UnicodeProperties.Booleans.POSIX_PRINT to value(true, "Yes"),
                    UnicodeProperties.Booleans.POSIX_XDIGIT to value(false, "No"),
                    UnicodeProperties.Booleans.CASED to value(false, "No"),
                    UnicodeProperties.Booleans.CASE_IGNORABLE to value(false, "No"),
                    UnicodeProperties.Booleans.CHANGES_WHEN_LOWERCASED to value(false, "No"),
                    UnicodeProperties.Booleans.CHANGES_WHEN_UPPERCASED to value(false, "No"),
                    UnicodeProperties.Booleans.CHANGES_WHEN_TITLECASED to value(false, "No"),
                    UnicodeProperties.Booleans.CHANGES_WHEN_CASEFOLDED to value(false, "No"),
                    UnicodeProperties.Booleans.CHANGES_WHEN_CASEMAPPED to value(false, "No"),
                    UnicodeProperties.Booleans.CHANGES_WHEN_NFKC_CASEFOLDED to value(false, "No"),
                    UnicodeProperties.Booleans.EMOJI to value(false, "No"),
                    UnicodeProperties.Booleans.EMOJI_PRESENTATION to value(false, "No"),
                    UnicodeProperties.Booleans.EMOJI_MODIFIER to value(false, "No"),
                    UnicodeProperties.Booleans.EMOJI_MODIFIER_BASE to value(false, "No"),
                    UnicodeProperties.Booleans.EMOJI_COMPONENT to value(false, "No"),
                    UnicodeProperties.Booleans.REGIONAL_INDICATOR to value(false, "No"),
                    UnicodeProperties.Booleans.PREPENDED_CONCATENATION_MARK to value(false, "No"),
                    UnicodeProperties.Booleans.EXTENDED_PICTOGRAPHIC to value(false, "No"),
                    UnicodeProperties.Booleans.BASIC_EMOJI to value(false, "No"),
                    UnicodeProperties.Booleans.EMOJI_KEYCAP_SEQUENCE to value(false, "No"),
                    UnicodeProperties.Booleans.RGI_EMOJI_MODIFIER_SEQUENCE to value(false, "No"),
                    UnicodeProperties.Booleans.RGI_EMOJI_FLAG_SEQUENCE to value(false, "No"),
                    UnicodeProperties.Booleans.RGI_EMOJI_TAG_SEQUENCE to value(false, "No"),
                    UnicodeProperties.Booleans.RGI_EMOJI_ZWJ_SEQUENCE to value(false, "No"),
                    UnicodeProperties.Booleans.RGI_EMOJI to value(false, "No"),
                    UnicodeProperties.Booleans.IDS_UNARY_OPERATOR to value(false, "No"),
                    UnicodeProperties.Booleans.ID_COMPAT_MATH_START to value(false, "No"),
                    UnicodeProperties.Booleans.ID_COMPAT_MATH_CONTINUE to value(false, "No"),
                    // Ints
                    UnicodeProperties.Ints.BIDI_CLASS to value(
                        value = UnicodeCharacterDirection.LEFT_TO_RIGHT,
                        description = "Left To Right"
                    ),
                    UnicodeProperties.Ints.BLOCK to value(
                        value = UnicodeBlock.HIRAGANA,
                        description = "Hiragana",
                    ),
                    UnicodeProperties.Ints.CANONICAL_COMBINING_CLASS to value(
                        value = UnicodeCanonicalCombiningClass.NOT_REORDERED,
                        description = "Not Reordered",
                    ),
                    UnicodeProperties.Ints.DECOMPOSITION_TYPE to value(
                        value = UnicodeDecompositionType.CANONICAL,
                        description = "Canonical",
                    ),
                    UnicodeProperties.Ints.EAST_ASIAN_WIDTH to value(UnicodeEastAsianWidth.WIDE, "Wide"),
                    UnicodeProperties.Ints.GENERAL_CATEGORY to value(
                        value = UnicodeCharacterCategory.OTHER_LETTER,
                        description = "Other Letter",
                    ),
                    UnicodeProperties.Ints.JOINING_GROUP to value(
                        value = UnicodeJoiningGroup.NO_JOINING_GROUP,
                        description = "No Joining Group",
                    ),
                    UnicodeProperties.Ints.JOINING_TYPE to value(
                        value = UnicodeJoiningType.NON_JOINING,
                        description = "Non Joining",
                    ),
                    UnicodeProperties.Ints.LINE_BREAK to value(UnicodeLineBreak.IDEOGRAPHIC, "Ideographic"),
                    UnicodeProperties.Ints.NUMERIC_TYPE to value(UnicodeNumericType.NONE, "None"),
                    UnicodeProperties.Ints.SCRIPT to value(UnicodeScript.HIRAGANA, "Hiragana"),
                    UnicodeProperties.Ints.HANGUL_SYLLABLE_TYPE to value(
                        value = UnicodeHangulSyllableType.NOT_APPLICABLE,
                        description = "Not Applicable",
                    ),
                    UnicodeProperties.Ints.NFD_QUICK_CHECK to value(
                        value = UnicodeQuickCheckResult.NO,
                        description = "No"
                    ),
                    UnicodeProperties.Ints.NFKD_QUICK_CHECK to value(
                        value = UnicodeQuickCheckResult.NO,
                        description = "No"
                    ),
                    UnicodeProperties.Ints.NFC_QUICK_CHECK to value(
                        value = UnicodeQuickCheckResult.YES,
                        description = "Yes"
                    ),
                    UnicodeProperties.Ints.NFKC_QUICK_CHECK to value(
                        value = UnicodeQuickCheckResult.YES,
                        description = "Yes",
                    ),
                    UnicodeProperties.Ints.LEAD_CANONICAL_COMBINING_CLASS to value(
                        value = UnicodeCanonicalCombiningClass.NOT_REORDERED,
                        description = "Not Reordered",
                    ),
                    UnicodeProperties.Ints.TRAIL_CANONICAL_COMBINING_CLASS to value(
                        value = UnicodeCanonicalCombiningClass.KANA_VOICING,
                        description = "Kana Voicing",
                    ),
                    UnicodeProperties.Ints.GRAPHEME_CLUSTER_BREAK to value(
                        value = UnicodeGraphemeClusterBreak.OTHER,
                        description = "Other"
                    ),
                    UnicodeProperties.Ints.SENTENCE_BREAK to value(
                        value = UnicodeSentenceBreak.OLETTER,
                        description = "Oletter"
                    ),
                    UnicodeProperties.Ints.WORD_BREAK to value(
                        value = UnicodeWordBreak.OTHER,
                        description = "Other"
                    ),
                    UnicodeProperties.Ints.BIDI_PAIRED_BRACKET_TYPE to value(
                        value = UnicodeBidiPairedBracketType.NONE,
                        description = "None",
                    ),
                    UnicodeProperties.Ints.INDIC_POSITIONAL_CATEGORY to value(
                        value = UnicodeIndicPositionalCategory.NA,
                        description = "Na",
                    ),
                    UnicodeProperties.Ints.INDIC_SYLLABIC_CATEGORY to value(
                        value = UnicodeIndicSyllabicCategory.OTHER,
                        description = "Other",
                    ),
                    UnicodeProperties.Ints.VERTICAL_ORIENTATION to value(
                        value = UnicodeVerticalOrientation.UPRIGHT,
                        description = "Upright",
                    ),
                    UnicodeProperties.Ints.IDENTIFIER_STATUS to value(
                        value = UnicodeIdentifierStatus.ALLOWED,
                        description = "Allowed",
                    ),
                    UnicodeProperties.Ints.PLANE to value(
                        value = UnicodePlane.BASIC_MULTILINGUAL_PLANE,
                        description = "Basic Multilingual Plane",
                    ),
                    // Masks
                    UnicodeProperties.Masks.GENERAL_CATEGORY_MASK to value(
                        value = setOf(UnicodeCharacterCategory.OTHER_LETTER),
                        description = "Other Letter",
                    ),
                    // Doubles
                    UnicodeProperties.Doubles.NUMERIC_VALUE to value(
                        value = -1.23456789E8,
                        description = "No Numeric Value",
                    ),
                    // Strings
                    UnicodeProperties.Strings.AGE to value(
                        value = VersionInfo.UNICODE_1_1_0,
                        description = "1.1.0 (June 1993)",
                    ),
                    UnicodeProperties.Strings.BIDI_MIRRORING_GLYPH to value("ば"),
                    UnicodeProperties.Strings.CASE_FOLDING to value("ば"),
                    UnicodeProperties.Strings.LOWERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.NAME to value(
                        value = "HIRAGANA LETTER BA",
                        description = "Hiragana Letter Ba",
                    ),
                    UnicodeProperties.Strings.NAME_ALIAS to value(""),
                    UnicodeProperties.Strings.EXTENDED_NAME to value(
                        value = "HIRAGANA LETTER BA",
                        description = "Hiragana Letter Ba",
                    ),
                    UnicodeProperties.Strings.SIMPLE_CASE_FOLDING to value("ば"),
                    UnicodeProperties.Strings.SIMPLE_LOWERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.SIMPLE_TITLECASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.SIMPLE_UPPERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.TITLECASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.UPPERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.BIDI_PAIRED_BRACKET to value("ば"),
                    UnicodeProperties.Strings.CANONICAL_DECOMPOSITION to value("は\u3099"),
                    UnicodeProperties.Strings.COMPATIBILITY_DECOMPOSITION to value("は\u3099"),
                    // Other
                    UnicodeProperties.Other.SCRIPT_EXTENSIONS to value(
                        value = setOf(UnicodeScript.HIRAGANA),
                        description = "Hiragana",
                    ),
                    UnicodeProperties.Other.IDENTIFIER_TYPE to value(
                        value = setOf(UnicodeIdentifierType.RECOMMENDED),
                        description = "Recommended",
                    ),
                )
            )
        }
    }
})
