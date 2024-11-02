package garden.ephemeral.glyphplay.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.platform.FontLoadResult
import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import org.jetbrains.skia.Typeface

val LastResort = FontFamily(
    Font(resource = "/fonts/LastResort/LastResort-Regular.ttf")
)

val NotoSans = FontFamily(
    Font(resource = "/fonts/NotoSans/NotoSans-Thin.ttf", weight = FontWeight.Thin, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-ThinItalic.ttf", weight = FontWeight.Thin, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-ExtraLight.ttf", weight = FontWeight.ExtraLight, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-ExtraLightItalic.ttf", weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-Light.ttf", weight = FontWeight.Light, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-LightItalic.ttf", weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-Regular.ttf", weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-Italic.ttf", weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-Medium.ttf", weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-MediumItalic.ttf", weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-SemiBold.ttf", weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-SemiBoldItalic.ttf", weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-Bold.ttf", weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-BoldItalic.ttf", weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-ExtraBold.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-ExtraBoldItalic.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resource = "/fonts/NotoSans/NotoSans-Black.ttf", weight = FontWeight.Black, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSans/NotoSans-BlackItalic.ttf", weight = FontWeight.Black, style = FontStyle.Italic),
)

val NotoSansSymbols = FontFamily(
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-Thin.ttf", weight = FontWeight.Thin, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-ExtraLight.ttf", weight = FontWeight.ExtraLight, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-Light.ttf", weight = FontWeight.Light, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-Regular.ttf", weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-Medium.ttf", weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-SemiBold.ttf", weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-Bold.ttf", weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-ExtraBold.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font(resource = "/fonts/NotoSansSymbols/NotoSansSymbols-Black.ttf", weight = FontWeight.Black, style = FontStyle.Normal),
)

val NotoSansSymbols2 = FontFamily(
    Font(resource = "/fonts/NotoSansSymbols2/NotoSansSymbols2-Regular.ttf"),
)

private val AllFontFamilies = sequenceOf(NotoSans, NotoSansSymbols, NotoSansSymbols2, LastResort)

private fun typefaceSupportsCodePoint(typeface: Typeface, codePoint: Int): Boolean {
    return typeface.getUTF32Glyph(codePoint) != 0.toShort()
}

private fun familySupportsCodePoint(fontFamilyResolver: FontFamily.Resolver, fontFamily: FontFamily, codePoint: Int): Boolean {
    return when (val result = fontFamilyResolver.resolve(fontFamily).value) {
        is FontLoadResult -> {
            val skiaTypeface = checkNotNull(result.typeface)
            typefaceSupportsCodePoint(skiaTypeface, codePoint)
        }
        else -> TODO("Support for font family resolver result: $result")
    }
}

fun determineBestFontFamilyForCodePoint(fontFamilyResolver: FontFamily.Resolver, codePoint: Int): FontFamily {
    // Workaround - emoji render totally fine in NotoSans, but the font itself reports that it doesn't contain
    // the glyph when you ask for it.
    if (UnicodeProperties.Booleans.EMOJI.valueForCodePoint(codePoint).value) {
        return AllFontFamilies.first()
    }

    return AllFontFamilies.first { fontFamily -> familySupportsCodePoint(fontFamilyResolver, fontFamily, codePoint) }
}

@Composable
fun determineBestFontFamilyForCodePoint(codePoint: Int): FontFamily {
    return determineBestFontFamilyForCodePoint(
        fontFamilyResolver = LocalFontFamilyResolver.current,
        codePoint = codePoint,
    )
}
