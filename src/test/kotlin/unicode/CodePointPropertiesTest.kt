package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter.IdentifierType
import com.ibm.icu.util.VersionInfo
import garden.ephemeral.glyphplay.search2.CodePointProperties
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.maps.shouldHaveSize

class CodePointPropertiesTest : FreeSpec({
    "CodePointProperties" - {
        val map = CodePointProperties.ofCodePoint("ば".codePointAt(0)).map

        fun value(value: Any, description: String = value.toString()) =
            UnicodePropertyValue(value = value, description = description)

        "should contain all expected properties" {
            map.shouldHaveSize(117)
            map.shouldContainExactly(
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
                    UnicodeProperties.Ints.BLOCK to value(UnicodeBlock.HIRAGANA, "Hiragana"),
                    UnicodeProperties.Ints.CANONICAL_COMBINING_CLASS to value(0, "Not Reordered"),
                    UnicodeProperties.Ints.DECOMPOSITION_TYPE to value(1, "Canonical"),
                    UnicodeProperties.Ints.EAST_ASIAN_WIDTH to value(UnicodeEastAsianWidth.WIDE, "Wide"),
                    UnicodeProperties.Ints.GENERAL_CATEGORY to value(
                        value = UnicodeCharacterCategory.OTHER_LETTER,
                        description = "Other Letter",
                    ),
                    UnicodeProperties.Ints.JOINING_GROUP to value(0, "No Joining Group"),
                    UnicodeProperties.Ints.JOINING_TYPE to value(0, "Non Joining"),
                    UnicodeProperties.Ints.LINE_BREAK to value(UnicodeLineBreak.IDEOGRAPHIC, "Ideographic"),
                    UnicodeProperties.Ints.NUMERIC_TYPE to value(UnicodeNumericType.NONE, "None"),
                    UnicodeProperties.Ints.SCRIPT to value(UnicodeScript.HIRAGANA, "Hiragana"),
                    UnicodeProperties.Ints.HANGUL_SYLLABLE_TYPE to value(0, "Not Applicable"),
                    UnicodeProperties.Ints.NFD_QUICK_CHECK to value(0, "No"),
                    UnicodeProperties.Ints.NFKD_QUICK_CHECK to value(0, "No"),
                    UnicodeProperties.Ints.NFC_QUICK_CHECK to value(1, "Yes"),
                    UnicodeProperties.Ints.NFKC_QUICK_CHECK to value(1, "Yes"),
                    UnicodeProperties.Ints.LEAD_CANONICAL_COMBINING_CLASS to value(0, "Not Reordered"),
                    UnicodeProperties.Ints.TRAIL_CANONICAL_COMBINING_CLASS to value(8, "Kana Voicing"),
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
                    UnicodeProperties.Ints.BIDI_PAIRED_BRACKET_TYPE to value(0, "None"),
                    UnicodeProperties.Ints.INDIC_POSITIONAL_CATEGORY to value(0, "Na"),
                    UnicodeProperties.Ints.INDIC_SYLLABIC_CATEGORY to value(0, "Other"),
                    UnicodeProperties.Ints.VERTICAL_ORIENTATION to value(3, "Upright"),
                    UnicodeProperties.Ints.IDENTIFIER_STATUS to value(1, "Allowed"),
                    // Masks
                    UnicodeProperties.Masks.GENERAL_CATEGORY_MASK to value(
                        value = setOf(UnicodeCharacterCategory.OTHER_LETTER),
                        description = "Other Letter"
                    ),
                    // Doubles
                    UnicodeProperties.Doubles.NUMERIC_VALUE to value(-1.23456789E8, "No Numeric Value"),
                    // Strings
                    UnicodeProperties.Strings.AGE to value(VersionInfo.UNICODE_1_1_0, "1.1"),
                    UnicodeProperties.Strings.BIDI_MIRRORING_GLYPH to value("ば"),
                    UnicodeProperties.Strings.CASE_FOLDING to value("ば"),
                    UnicodeProperties.Strings.LOWERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.NAME to value("HIRAGANA LETTER BA"),
                    UnicodeProperties.Strings.SIMPLE_CASE_FOLDING to value("ば"),
                    UnicodeProperties.Strings.SIMPLE_LOWERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.SIMPLE_TITLECASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.SIMPLE_UPPERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.TITLECASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.UPPERCASE_MAPPING to value("ば"),
                    UnicodeProperties.Strings.BIDI_PAIRED_BRACKET to value("ば"),
                    // Other
                    UnicodeProperties.Other.SCRIPT_EXTENSIONS to value(setOf(UnicodeScript.HIRAGANA), "Hiragana"),
                    UnicodeProperties.Other.IDENTIFIER_TYPE to value(setOf(IdentifierType.RECOMMENDED), "Recommended"),
                )
            )
        }
    }
})
