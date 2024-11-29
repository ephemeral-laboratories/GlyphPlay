package garden.ephemeral.glyphplay.fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.createFontFamilyResolver
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.toCodePoint
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ExtraFontsTest : FreeSpec({
    val resolver: FontFamily.Resolver = createFontFamilyResolver()

    "determineBestFontFamilyForCodePoint" - {
        "returns NotoSans for various characters" {
            assertSoftly {
                // Printable ASCII
                ('!'.toCodePoint()..'~'.toCodePoint()).forEach { codePoint ->
                    determineBestFontFamilyForCodePoint(resolver, codePoint) shouldBe NotoSans
                }

                // All these are not actually in NotoSans, but render via fallback.
                // Emoji
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0x1F574)) shouldBe NotoSans
                // Svasti signs, at least this variant of them
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0x534D)) shouldBe NotoSans
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0x5350)) shouldBe NotoSans
                // CJK radical
                determineBestFontFamilyForCodePoint(resolver, '⼡'.toCodePoint()) shouldBe NotoSans
                // Male and Female Sign
                determineBestFontFamilyForCodePoint(resolver, '⚥'.toCodePoint()) shouldBe NotoSans
                // Floppy Disk
                determineBestFontFamilyForCodePoint(resolver, "🖫".firstToCodePoint()) shouldBe NotoSans
            }
        }

        "returns LastResort for characters that can't be rendered by fallback" {
            assertSoftly {
                // Svasti characters, the ones in the Tibetan block
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0xFD5)) shouldBe LastResort
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0xFD6)) shouldBe LastResort
                // Private use areas
                listOf(
                    (CodePoint(0xE000)..CodePoint(0xEFFF)),
                    (CodePoint(0xF000)..CodePoint(0xF8FF)),
                    (CodePoint(0xF0000)..CodePoint(0xFFFFD)),
                    (CodePoint(0x100000)..CodePoint(0x10FFFD)),
                ).flatten().forEach { codePoint ->
                    determineBestFontFamilyForCodePoint(resolver, codePoint) shouldBe LastResort
                }
            }
        }
    }
})
