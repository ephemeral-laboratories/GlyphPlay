package garden.ephemeral.glyphplay.fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.createFontFamilyResolver
import garden.ephemeral.glyphplay.unicode.CodePoint
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ExtraFontsTest : FreeSpec({
    val resolver: FontFamily.Resolver = createFontFamilyResolver()

    "determineBestFontFamilyForCodePoint" - {
        "returns NotoSans for various characters" {
            assertSoftly {
                // Printable ASCII
                ('!'.code..'~'.code).forEach { value ->
                    determineBestFontFamilyForCodePoint(resolver, CodePoint(value)) shouldBe NotoSans
                }

                // All these are not actually in NotoSans, but render via fallback.
                // Emoji
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0x1F574)) shouldBe NotoSans
                // Svasti signs, at least this variant of them
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0x534D)) shouldBe NotoSans
                determineBestFontFamilyForCodePoint(resolver, CodePoint(0x5350)) shouldBe NotoSans
                // CJK radical
                determineBestFontFamilyForCodePoint(resolver, CodePoint('⼡'.code)) shouldBe NotoSans
                // Male and Female Sign
                determineBestFontFamilyForCodePoint(resolver, CodePoint('⚥'.code)) shouldBe NotoSans
                // Floppy Disk
                determineBestFontFamilyForCodePoint(resolver, CodePoint("🖫".codePointAt(0))) shouldBe NotoSans
            }
        }

        "returns LastResort for characters that can't be rendered by fallback" {
            // Svasti characters, the ones in the Tibetan block
            determineBestFontFamilyForCodePoint(resolver, CodePoint(0xFD5)) shouldBe LastResort
            determineBestFontFamilyForCodePoint(resolver, CodePoint(0xFD6)) shouldBe LastResort
            // Private use areas
            CodePoint(0xE000)..CodePoint(0xEFFF)
            listOf(
                (0xE000..0xEFFF),
                (0xF000..0xF8FF),
                (0xF0000..0xFFFFD),
                (0x100000..0x10FFFD),
            ).flatten().forEach { value ->
                determineBestFontFamilyForCodePoint(resolver, CodePoint(value)) shouldBe LastResort
            }
        }
    }
})
