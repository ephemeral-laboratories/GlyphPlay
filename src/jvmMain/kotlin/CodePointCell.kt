package garden.ephemeral.glyphplay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import garden.ephemeral.glyphplay.fonts.determineBestFontFamilyForCodePoint
import garden.ephemeral.glyphplay.unicode.CodePointDescription

@Composable
fun CodePointCell(
    codePoint: Int,
    size: Dp,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) = CodePointCell(
    description = CodePointDescription.ofCodePoint(codePoint),
    size = size,
    onClick = onClick,
    modifier = modifier
)

@Composable
fun CodePointCell(
    description: CodePointDescription,
    size: Dp,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface {
        val fontHeight = size * (2.0f / 3.0f)
        val fontSize = with(LocalDensity.current) { fontHeight.toSp() }
        Box(modifier = modifier) {
            OutlinedButton(
                shape = RectangleShape,
                onClick = onClick,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Text(
                    text = description.stringFormForUI,
                    fontFamily = determineBestFontFamilyForCodePoint(description.codePoint),
                    fontSize = fontSize,
                    textAlign = TextAlign.Center,
                    lineHeight = fontSize,
                    modifier = Modifier
                        // Yes, we need to force this here as well because some characters like musical notes try to
                        // force the cell to be taller.
                        .requiredSize(size)
                        .debugBorder(),
                )
            }
        }
    }
}
