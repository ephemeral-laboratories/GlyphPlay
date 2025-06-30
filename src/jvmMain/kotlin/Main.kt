package garden.ephemeral.glyphplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import garden.ephemeral.glyphplay.theme.AppTheme
import garden.ephemeral.glyphplay.theme.Branding
import garden.ephemeral.glyphplay.unicode.CodePoint

fun main() = application {
    Window(
        state = rememberWindowState(size = DpSize(1200.dp, 800.dp)),
        title = Branding.APPLICATION_NAME,
        onCloseRequest = ::exitApplication
    ) {
        AppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    // App state
                    val (currentCodePoint, setCurrentCodePoint) = remember { mutableStateOf(CodePoint(0x1F574)) }

                    Box(Modifier.fillMaxHeight()) {
                        SearchView(onCodePointClicked = setCurrentCodePoint)
                    }
                    Box(Modifier.fillMaxSize()) {
                        CodePointDescriptionView(
                            codePoint = currentCodePoint,
                            onCodePointLinkClicked = setCurrentCodePoint
                        )
                    }
                }
            }
        }
    }
}
