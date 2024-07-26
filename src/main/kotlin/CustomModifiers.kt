package garden.ephemeral.glyphplay

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
    }
}

const val isDebug = false

internal fun Modifier.debugBorder() = if (isDebug) border(1.dp, Color.Red) else this

internal fun Modifier.debugLineAtY(y: Dp) = if (isDebug) {
    drawWithContent {
        drawContent()

        val strokeWidth = 1.dp.toPx()
        val yPixels = y.toPx()
        drawLine(
            color = Color.Magenta,
            start = Offset(0.0f, yPixels),
            end = Offset(size.width, yPixels),
            strokeWidth = strokeWidth,
        )
    }
} else {
    this
}
