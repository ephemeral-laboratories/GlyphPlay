package garden.ephemeral.glyphplay.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.platform.FontLoadResult
import garden.ephemeral.glyphplay.unicode.CodePoint
import org.jetbrains.skia.Typeface
import org.jetbrains.skia.shaper.Shaper

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

val LastResort = FontFamily(
    Font(resource = "/fonts/LastResort/LastResort-Regular.ttf")
)

private val AllFontFamilies = sequenceOf(NotoSans, LastResort)

private fun typefaceSupportsCodePoint(typeface: Typeface, codePoint: CodePoint): Boolean {
    val font = org.jetbrains.skia.Font(typeface = typeface)
    val textLine = Shaper.make().shapeLine(text = codePoint.toString(), font = font)
    return textLine.glyphs.isNotEmpty() && textLine.glyphs.none { it == 0.toShort() }
}

private fun familySupportsCodePoint(fontFamilyResolver: FontFamily.Resolver, fontFamily: FontFamily, codePoint: CodePoint): Boolean {
    return when (val result = fontFamilyResolver.resolve(fontFamily).value) {
        is FontLoadResult -> {
            val skiaTypeface = checkNotNull(result.typeface)
            typefaceSupportsCodePoint(skiaTypeface, codePoint)
        }
        else -> TODO("Support for font family resolver result: $result")
    }
}

fun determineBestFontFamilyForCodePoint(fontFamilyResolver: FontFamily.Resolver, codePoint: CodePoint) =
    AllFontFamilies.first { fontFamily -> familySupportsCodePoint(fontFamilyResolver, fontFamily, codePoint) }

@Composable
fun determineBestFontFamilyForCodePoint(codePoint: CodePoint): FontFamily {
    return determineBestFontFamilyForCodePoint(
        fontFamilyResolver = LocalFontFamilyResolver.current,
        codePoint = codePoint,
    )
}
