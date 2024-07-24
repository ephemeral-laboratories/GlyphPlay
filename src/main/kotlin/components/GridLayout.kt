package garden.ephemeral.glyphplay.components

//import androidx.compose.ui.layout.RootMeasurePolicy.measure
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import kotlin.math.max


class GridLayoutScope2(columnCount: Int) {
    var yOffset = 0

    val rowContents = mutableListOf<@Composable () -> Unit>()

    val alignmentLines = (0..<columnCount).map { _ -> VerticalAlignmentLine(merger = { old, new -> max(old, new) }) }

    @Composable
    fun row(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
        BoxWithConstraints(modifier = modifier) {
            Layout(content = content) { measurables: List<Measurable>, constraints: Constraints ->
                val rowPlaceables = measurables.map { measurable -> measurable.measure(constraints) }
                val maxHeightInRow = rowPlaceables.maxOf(Placeable::height)
                TODO("fuck")
//                layout(
//                    width = constraints.maxWidth,
//                    height = maxHeightInRow,
//                    alignmentLines = alignmentLines.associate { alignmentLine: VerticalAlignmentLine ->
//                        Pair(alignmentLine, TODO("...some X offset..."))
//                    },
//                )
            }
            content()
        }
    }

    @Composable
    internal fun emitAllContent() {
        Layout(
            content = {
                // TODO: How to record the row indices here?
                rowContents.forEach { rowContent ->
                    rowContent()
                }
            },
        ) { measurables: List<Measurable>, constraints: Constraints ->

            val rowPlaceables = measurables.map { measurable -> measurable.measure(constraints) }
            val rowHeight = rowPlaceables.maxOf(Placeable::height)
            layout(width = constraints.maxWidth, height = rowHeight) {
//                rowPlaceables.forEachIndexed { columnIndex: Int, placeable: Placeable ->
//                    placeable.placeRelative(x = xOffset, y =)
//                }
                TODO("Shit")
            }
        }

//        yOffset += placeablesInColumns.map { column -> column[rowIndex] }.maxOf { cell -> cell.height } +
////                    verticalArrangement.spacing.roundToPx()


    }
}

class CustomRow(val content: @Composable () -> Unit) {

}

class GridLayoutScope {
    val rows = mutableListOf<CustomRow>()

    @Composable
    fun row(content: @Composable () -> Unit) {
        rows.add(CustomRow(content = content))
    }
}

@Composable
fun GridLayout(
    columnCount: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    content: @Composable GridLayoutScope.() -> Unit,
) {
    val scope = GridLayoutScope()
    scope.content()
    Layout(
        contents = scope.rows.map { r -> r.content },
        modifier = modifier
    ) { measurables: List<List<Measurable>>, constraints: Constraints ->
        val placeables = measurables.indices.map { _ -> mutableListOf<Placeable>() }
        val columnSpacing = horizontalArrangement.spacing.roundToPx()
        val rowSpacing = verticalArrangement.spacing.roundToPx()
        val columnOffsets = mutableListOf<Int>()
        val rowOffsets = mutableListOf<Int>()

        // Have to iterate in column order when measuring, because the remaining space depends on
        // the width of previous columns.
        var xOffset = 0
        for (columnIndex in 0..<columnCount) {
            val columnPlaceables = mutableListOf<Placeable>()
            val columnConstraints = Constraints(
                minWidth = 0,
                maxWidth = (constraints.maxWidth - xOffset).coerceAtLeast(constraints.minWidth),
                minHeight = 0,
                maxHeight = constraints.maxHeight
            )
            for (rowIndex in measurables.indices) {
                val rowMeasurables = measurables[rowIndex]
                rowMeasurables.getOrNull(columnIndex)?.let { measurable ->
                    val placeable = measurable.measure(columnConstraints)
                    placeables[rowIndex].add(placeable)
                    columnPlaceables.add(placeable)
                }
            }

            val columnWidth = columnPlaceables.maxOf(Placeable::width)
            columnOffsets.add(xOffset)
            xOffset += columnWidth + columnSpacing
        }

        var yOffset = 0
        placeables.forEach { row ->
            val rowHeight = row.maxOf(Placeable::height)
            rowOffsets.add(yOffset)
            yOffset += rowHeight + rowSpacing
        }

        layout(width = constraints.maxWidth, height = yOffset - rowSpacing) {
            placeables.forEachIndexed { rowIndex, rowPlaceables ->
                rowPlaceables.forEachIndexed { columnIndex, cellPlaceable ->
                    cellPlaceable.placeRelative(x = columnOffsets[columnIndex], y = rowOffsets[rowIndex])
                }
            }
        }
    }
}
