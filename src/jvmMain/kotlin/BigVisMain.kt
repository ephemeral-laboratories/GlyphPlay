package garden.ephemeral.glyphplay

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import garden.ephemeral.glyphplay.theme.AppTheme
import garden.ephemeral.glyphplay.unicode.enums.UnicodePlane

@Composable
fun UnicodePlanesView() {
    val chunkStep = 0x100
    val chunksPerRow = 0x10
    val rowStep = 0x10
    val cellSize = 48.dp

    // Calculated width of one chunk table.
    // TODO: This is probably wrong. I need to figure out some esoterics about the amount of padding in buttons.
    val chunkCellWidth = (cellSize + 11.dp) * rowStep

    Box(modifier = Modifier.horizontalScroll(state = rememberScrollState())) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(chunksPerRow),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp).width(chunkCellWidth * chunksPerRow),
        ) {
            UnicodePlane.Companion.entries.forEach { plane ->
                // Plane header
                item(span = { GridItemSpan(chunksPerRow) }) {
                    Text(
                        text = "${plane.longName} (${plane.range})",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                // Plane chunks
                val chunkStarts = (plane.range step chunkStep).toList()
                items(items = chunkStarts) {chunkStart ->
                    // One chunk of `chunkStep` code points.
                    // Really, we'd like these to be lazy as well, but you can't have lazy inside lazy in Compose.
                    Column {
                        val chunkRange = chunkStart..<(chunkStart + chunkStep)
                        Text(text = chunkRange.toString(), style = MaterialTheme.typography.titleMedium)

                        Column {
                            (chunkRange step rowStep).forEach { rowStart ->
                                Row {
                                    (rowStart..<(rowStart + rowStep)).forEach { codePoint ->
                                        if (codePoint.isValid() && codePoint.isDefined()) {
                                            CodePointCell(
                                                codePoint = codePoint,
                                                size = cellSize,
                                            )
                                        } else {
                                            CodePointPlaceholderCell(size = cellSize)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

fun main() = application {
    AppTheme {
        Window(title = "Hi", onCloseRequest = ::exitApplication) {
            Surface {
                UnicodePlanesView()
            }
        }
    }
}
