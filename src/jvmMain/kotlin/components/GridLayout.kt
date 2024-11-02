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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CustomRow(
    val indent: Dp,
    val content: @Composable () -> Unit,
)

class GridLayoutScope(
    private val verticalArrangement: Arrangement.Vertical,
    private val sectionIndent: Dp,
    private val currentIndent: Dp,
) {
    val rows = mutableListOf<CustomRow>()

    fun section(headerContent: @Composable () -> Unit, nestedContent: GridLayoutScope.() -> Unit) {
        val nestedScope = GridLayoutScope(
            verticalArrangement = verticalArrangement,
            sectionIndent = sectionIndent,
            currentIndent = currentIndent + sectionIndent,
        )
        nestedScope.nestedContent()
        if (nestedScope.rows.isNotEmpty()) {
            row {
                headerContent()
            }
            rows.addAll(nestedScope.rows)
        }
    }

    fun row(content: @Composable () -> Unit) {
        rows.add(CustomRow(indent = currentIndent, content = content))
    }

    fun buildRowContents() = rows.toList()
}

@Composable
fun GridLayout(
    columnCount: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    sectionIndent: Dp = 16.dp,
    content: GridLayoutScope.() -> Unit,
) {
    val rows by derivedStateOf {
        val scope = GridLayoutScope(
            verticalArrangement = verticalArrangement,
            sectionIndent = sectionIndent,
            currentIndent = 0.dp
        )
        scope.content()
        scope.rows
    }
    val rowIndents by derivedStateOf { rows.map { r -> r.indent } }
    val rowContents by derivedStateOf { rows.map { r -> r.content } }

    Layout(
        contents = rowContents,
        modifier = modifier
    ) { measurables: List<List<Measurable>>, constraints: Constraints ->
        val placeables = measurables.indices.map { _ -> mutableListOf<Placeable>() }
        val columnSpacing = horizontalArrangement.spacing.roundToPx()
        val rowSpacing = verticalArrangement.spacing.roundToPx()
        val columnOffsets = mutableListOf<Int>()
        val rowOffsets = mutableListOf<Int>()

        fun cellIndent(rowIndex: Int, columnIndex: Int) =
            if (columnIndex == 0) rowIndents[rowIndex].roundToPx() else 0

        // Have to iterate in column order when measuring, because the remaining space depends on
        // the width of previous columns.
        var xOffset = 0
        for (columnIndex in 0..<columnCount) {
            val columnPlaceables = mutableListOf<Placeable>()
            for (rowIndex in measurables.indices) {
                val cellXOffset = xOffset + cellIndent(rowIndex, columnIndex)

                val rowMeasurables = measurables[rowIndex]
                rowMeasurables.getOrNull(columnIndex)?.let { measurable ->
                    val cellConstraints = Constraints(
                        minWidth = 0,
                        maxWidth = (constraints.maxWidth - cellXOffset).coerceAtLeast(constraints.minWidth),
                        minHeight = 0,
                        maxHeight = constraints.maxHeight
                    )

                    val placeable = measurable.measure(cellConstraints)
                    placeables[rowIndex].add(placeable)
                    columnPlaceables.add(placeable)
                }
            }

            columnOffsets.add(xOffset)

            val columnWidth = columnPlaceables
                .mapIndexed { rowIndex, placeable -> placeable.width + cellIndent(rowIndex, columnIndex) }
                .max()

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
                    cellPlaceable.placeRelative(
                        x = columnOffsets[columnIndex] + cellIndent(rowIndex, columnIndex),
                        y = rowOffsets[rowIndex]
                    )
                }
            }
        }
    }

}
