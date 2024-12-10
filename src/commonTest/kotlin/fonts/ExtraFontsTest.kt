package garden.ephemeral.glyphplay.fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.createFontFamilyResolver
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.toCodePoint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.junit.platform.commons.annotation.Testable

@Testable
class ExtraFontsTest : FreeSpec({
    val resolver: FontFamily.Resolver = createFontFamilyResolver()

    "determineBestFontFamilyForCodePoint" - {
        fun single(cp: CodePoint) = cp..cp

        val rangeExpectations = listOf(
            // U+0000..U+007F    Basic Latin
            row(('!'.toCodePoint()..'~'.toCodePoint()), NotoSans),

            /* TODO: Check more blocks
            U+0080..U+00FF    Latin-1 Supplement
            U+0100..U+017F    Latin Extended-A
            U+0180..U+024F    Latin Extended-B
            U+0250..U+02AF    IPA Extensions
            U+02B0..U+02FF    Spacing Modifier Letters
            U+0300..U+036F    Combining Diacritical Marks
            U+0370..U+03FF    Greek and Coptic
            U+0400..U+04FF    Cyrillic
            U+0500..U+052F    Cyrillic Supplement
            U+0530..U+058F    Armenian
            U+0590..U+05FF    Hebrew
            U+0600..U+06FF    Arabic
            U+0700..U+074F    Syriac
            U+0750..U+077F    Arabic Supplement
            U+0780..U+07BF    Thaana
            U+07C0..U+07FF    NKo
            U+0800..U+083F    Samaritan
            U+0840..U+085F    Mandaic
            U+0860..U+086F    Syriac Supplement
            U+0870..U+089F    Arabic Extended-B
            U+08A0..U+08FF    Arabic Extended-A
            U+0900..U+097F    Devanagari
            U+0980..U+09FF    Bengali
            U+0A00..U+0A7F    Gurmukhi
            U+0A80..U+0AFF    Gujarati
            U+0B00..U+0B7F    Oriya
            U+0B80..U+0BFF    Tamil
            U+0C00..U+0C7F    Telugu
            U+0C80..U+0CFF    Kannada
            U+0D00..U+0D7F    Malayalam
            U+0D80..U+0DFF    Sinhala
            U+0E00..U+0E7F    Thai
            U+0E80..U+0EFF    Lao
            */

            // U+0F00..U+0FFF    Tibetan
            row((CodePoint(0xFD5)..CodePoint(0xFD6)), LastResort),

            /* TODO: Check more blocks
            U+1000..U+109F 	Myanmar
            U+10A0..U+10FF 	Georgian
            U+1100..U+11FF 	Hangul Jamo
            U+1200..U+137F 	Ethiopic
            U+1380..U+139F 	Ethiopic Supplement
            U+13A0..U+13FF 	Cherokee
            U+1400..U+167F 	Unified Canadian Aboriginal Syllabics
            U+1680..U+169F 	Ogham
            U+16A0..U+16FF 	Runic
            U+1700..U+171F 	Tagalog
            U+1720..U+173F 	Hanunoo
            U+1740..U+175F 	Buhid
            U+1760..U+177F 	Tagbanwa
            U+1780..U+17FF 	Khmer
            U+1800..U+18AF 	Mongolian
            U+18B0..U+18FF 	Unified Canadian Aboriginal Syllabics Extended
            U+1900..U+194F 	Limbu
            U+1950..U+197F 	Tai Le
            U+1980..U+19DF 	New Tai Lue
            U+19E0..U+19FF 	Khmer Symbols
            U+1A00..U+1A1F 	Buginese
            U+1A20..U+1AAF 	Tai Tham
            U+1AB0..U+1AFF 	Combining Diacritical Marks Extended
            U+1B00..U+1B7F 	Balinese
            U+1B80..U+1BBF 	Sundanese
            U+1BC0..U+1BFF 	Batak
            U+1C00..U+1C4F 	Lepcha
            U+1C50..U+1C7F 	Ol Chiki
            U+1C80..U+1C8F 	Cyrillic Extended-C
            U+1C90..U+1CBF 	Georgian Extended
            U+1CC0..U+1CCF 	Sundanese Supplement
            U+1CD0..U+1CFF 	Vedic Extensions
            U+1D00..U+1D7F 	Phonetic Extensions
            U+1D80..U+1DBF 	Phonetic Extensions Supplement
            U+1DC0..U+1DFF 	Combining Diacritical Marks Supplement
            U+1E00..U+1EFF 	Latin Extended Additional
            U+1F00..U+1FFF 	Greek Extended
            U+2000..U+206F 	General Punctuation
            U+2070..U+209F 	Superscripts and Subscripts
            U+20A0..U+20CF 	Currency Symbols
            U+20D0..U+20FF 	Combining Diacritical Marks for Symbols
            U+2100..U+214F 	Letterlike Symbols
            U+2150..U+218F 	Number Forms
            U+2190..U+21FF 	Arrows
            U+2200..U+22FF 	Mathematical Operators
            U+2300..U+23FF 	Miscellaneous Technical
            U+2400..U+243F 	Control Pictures
            U+2440..U+245F 	Optical Character Recognition
            U+2460..U+24FF 	Enclosed Alphanumerics
            U+2500..U+257F 	Box Drawing
            U+2580..U+259F 	Block Elements
            U+25A0..U+25FF 	Geometric Shapes
             */

            // U+2600..U+26FF    Miscellaneous Symbols
            row(CodePoint(0x2600)..CodePoint(0x26FF), NotoSans),

            /* TODO: Check more blocks
            U+2700..U+27BF 	Dingbats
            U+27C0..U+27EF 	Miscellaneous Mathematical Symbols-A
            U+27F0..U+27FF 	Supplemental Arrows-A
            U+2800..U+28FF 	Braille Patterns
            U+2900..U+297F 	Supplemental Arrows-B
            U+2980..U+29FF 	Miscellaneous Mathematical Symbols-B
            U+2A00..U+2AFF 	Supplemental Mathematical Operators
             */

            // U+2B00..U+2BFF    Miscellaneous Symbols and Arrows
            row(CodePoint(0x2B00)..CodePoint(0x2B73), NotoSans),
            row(CodePoint(0x2B74)..CodePoint(0x2B75), LastResort),
            row(CodePoint(0x2B76)..CodePoint(0x2B95), NotoSans),
            row(CodePoint(0x2B96)..CodePoint(0x2B97), LastResort),
            row(CodePoint(0x2B98)..CodePoint(0x2BB9), NotoSans),
            row(CodePoint(0x2BBA)..CodePoint(0x2BBC), LastResort),
            row(CodePoint(0x2BBD)..CodePoint(0x2BC8), NotoSans),
            row(single(CodePoint(0x2BC9)), LastResort),
            row(CodePoint(0x2BCA)..CodePoint(0x2BD1), NotoSans),
            row(CodePoint(0x2BD2)..CodePoint(0x2BEB), LastResort),
            row(CodePoint(0x2BEC)..CodePoint(0x2BEF), NotoSans),
            row(CodePoint(0x2BF0)..CodePoint(0x2BFF), LastResort),

            // TODO: Check and catalogue more blocks - at what point do we move this to a data file?

            // CJK radical
            row(single('⼡'.toCodePoint()), NotoSans),

            // Svasti signs, at least this variant of them
            row(single(CodePoint(0x534D)), NotoSans),
            row(single(CodePoint(0x5350)), NotoSans),

            // U+E000..U+F8FF  Private Use Area
            row((CodePoint(0xE000)..CodePoint(0xF8FF)), LastResort),

            // U+1F300..U+1F5FF    Miscellaneous Symbols and Pictographs
            row(CodePoint(0x1F300)..CodePoint(0x1F54E), NotoSans),
            row(single(CodePoint(0x1F54F)), LastResort),
            row(CodePoint(0x1F550)..CodePoint(0x1F5FF), NotoSans),

            // U+1F900..U+1F9FF    Supplemental Symbols and Pictographs
            row(CodePoint(0x1F90C)..CodePoint(0x1F93A), NotoSans),
            row(CodePoint(0x1F93C)..CodePoint(0x1F945), NotoSans),
            row(CodePoint(0x1F947)..CodePoint(0x1F9FF), NotoSans),

            // U+F0000..U+FFFFD    Supplementary Private Use Area-A
            row((CodePoint(0xF0000)..CodePoint(0xFFFFD)), LastResort),

            // U+100000..U+10FFFD    Supplementary Private Use Area-B
            row((CodePoint(0x100000)..CodePoint(0x10FFFD)), LastResort),
        )

        rangeExpectations.forEach { (range, expected) ->
            "Range $range" {
                range.forEach { codePoint ->
                    determineBestFontFamilyForCodePoint(resolver, codePoint) shouldBe expected
                }
            }
        }
    }
})
