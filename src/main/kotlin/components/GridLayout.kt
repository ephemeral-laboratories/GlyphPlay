package garden.ephemeral.glyphplay.components

//import androidx.compose.ui.layout.RootMeasurePolicy.measure
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

class CustomRow(val content: @Composable () -> Unit) {

}

class GridLayoutScope {
    val rows = mutableListOf<CustomRow>()

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
    content: GridLayoutScope.() -> Unit,
) {
    val rowContents by derivedStateOf {
        val scope = GridLayoutScope()
        scope.rows.map { r -> r.content }
        scope.content()
        scope.rows.map { r -> r.content }
    }

    Layout(
        contents = rowContents,
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
