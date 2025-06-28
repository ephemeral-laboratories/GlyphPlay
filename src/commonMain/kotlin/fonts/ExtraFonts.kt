package garden.ephemeral.glyphplay.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.platform.FontLoadResult
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphs.glyphplay.generated.resources.Res
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.skia.FontMgrWithFallback
import org.jetbrains.skia.Typeface
import org.jetbrains.skia.paragraph.FontCollection
import org.jetbrains.skia.paragraph.TypefaceFontProviderWithFallback
import java.io.File

data class FontInfo(
    val path: String,
    val weight: FontWeight = FontWeight.Normal,
    val style: FontStyle = FontStyle.Normal,
)

@OptIn(ExperimentalResourceApi::class)
private suspend fun loadFont(info: FontInfo): Font {
    val identity = File(info.path).nameWithoutExtension
    return Font(
        identity = identity,
        data = Res.readBytes("files/fonts/${info.path}"),
        weight = info.weight,
        style = info.style,
    )
}

// I tried using the Res.font helpers, but they were less convenient than expected,
// as the method was @Composable and a great deal of our usage of these fonts was from
// non-Composable functions, including all the test code.
// If we're forced to do this in a blocking fashion, we might as well load all the individual files
// in parallel.
fun loadFontFamily(vararg fontInfos: FontInfo) = FontFamily(
    runBlocking {
        fontInfos.map { fontInfo ->
            async { loadFont(fontInfo) }
        }.awaitAll()
    }
)

val NotoSans = loadFontFamily(
    FontInfo("NotoSans/NotoSans-Thin.ttf", FontWeight.Thin, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-Thin.ttf", FontWeight.Thin, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-ThinItalic.ttf", FontWeight.Thin, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-ExtraLight.ttf", FontWeight.ExtraLight, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-ExtraLightItalic.ttf", FontWeight.ExtraLight, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-Light.ttf", FontWeight.Light, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-LightItalic.ttf", FontWeight.Light, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-Regular.ttf", FontWeight.Normal, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-Italic.ttf", FontWeight.Normal, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-Medium.ttf", FontWeight.Medium, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-MediumItalic.ttf", FontWeight.Medium, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-SemiBold.ttf", FontWeight.SemiBold, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-SemiBoldItalic.ttf", FontWeight.SemiBold, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-Bold.ttf", FontWeight.Bold, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-BoldItalic.ttf", FontWeight.Bold, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-ExtraBold.ttf", FontWeight.ExtraBold, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-ExtraBoldItalic.ttf", FontWeight.ExtraBold, FontStyle.Italic),
    FontInfo("NotoSans/NotoSans-Black.ttf", FontWeight.Black, FontStyle.Normal),
    FontInfo("NotoSans/NotoSans-BlackItalic.ttf", FontWeight.Black, FontStyle.Italic),
)

val LastResort = loadFontFamily(
    FontInfo("LastResort/LastResort-Regular.ttf")
)

private val AllFontFamilies = sequenceOf(NotoSans, LastResort)

private val fontCollection = FontCollection()
    .setDefaultFontManager(FontMgrWithFallback(TypefaceFontProviderWithFallback()))

private fun typefaceContainsCodePoint(typeface: Typeface, codePoint: CodePoint) =
    typeface.getUTF32Glyph(codePoint.value) != 0.toShort()

private fun fallbackSupportsCodePoint(typeface: Typeface, codePoint: CodePoint) =
    fontCollection.defaultFallback(unicode = codePoint.value, style = typeface.fontStyle, locale = null) != null

private fun familySupportsCodePoint(
    fontFamilyResolver: FontFamily.Resolver, fontFamily: FontFamily, codePoint: CodePoint
): Boolean {
    return when (val result = fontFamilyResolver.resolve(fontFamily).value) {
        is FontLoadResult -> {
            val skiaTypeface = checkNotNull(result.typeface)
            typefaceContainsCodePoint(skiaTypeface, codePoint) || fallbackSupportsCodePoint(skiaTypeface, codePoint)
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
