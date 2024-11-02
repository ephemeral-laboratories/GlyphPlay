package garden.ephemeral.glyphplay.fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.createFontFamilyResolver
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ExtraFontsTest : FreeSpec({
    val resolver: FontFamily.Resolver = createFontFamilyResolver()

    "determineBestFontFamilyForCodePoint" - {
        "returns NotoSans if the code point is in it" {
            determineBestFontFamilyForCodePoint(resolver, 'a'.code).shouldBe(NotoSans)
        }

        "returns NotoSans for emoji despite those not being in NotoSans" {
            determineBestFontFamilyForCodePoint(resolver, 0x1F574).shouldBe(NotoSans)
        }

        "returns NotoSansSymbols if the code point is in it but none before it" {
            determineBestFontFamilyForCodePoint(resolver, '⚥'.code).shouldBe(NotoSansSymbols)
        }

        "returns NotoSansSymbols2 if the code point is in it but none before it" {
            determineBestFontFamilyForCodePoint(resolver, "🖫".codePointAt(0)).shouldBe(NotoSansSymbols2)
        }

        "returns LastResort for code points in the private use area" {
            determineBestFontFamilyForCodePoint(resolver, 0xE000).shouldBe(LastResort)
        }
    }
})
