package garden.ephemeral.glyphplay.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CustomCell(val content: @Composable () -> Unit)

data class CustomRow(val indentLevel: Int, val cells: List<CustomCell>)

class RowScope() {
    val cells = mutableListOf<CustomCell>()

    fun cell(content: @Composable () -> Unit) {
        cells.add(CustomCell(content))
    }
}

class GridLayoutScope(
    private val verticalArrangement: Arrangement.Vertical,
    private val currentIndentLevel: Int,
) {
    val rows = mutableListOf<CustomRow>()

    fun section(headerContent: @Composable () -> Unit, nestedContent: GridLayoutScope.() -> Unit) {
        val nestedScope = GridLayoutScope(
            verticalArrangement = verticalArrangement,
            currentIndentLevel = currentIndentLevel + 1,
        )
        nestedScope.nestedContent()
        if (nestedScope.rows.isNotEmpty()) {
            row {
                cell {
                    headerContent()
                }
            }
            rows.addAll(nestedScope.rows)
        }
    }

    fun row(content: RowScope.() -> Unit) {
        val rowScope = RowScope()
        rowScope.content()
        rows.add(CustomRow(indentLevel = currentIndentLevel, cells = rowScope.cells))
    }
}

@Composable
fun GridLayout(
    columnCount: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    sectionIndent: Dp = 16.dp,
    sectionIndentText: String = "  ",
    content: GridLayoutScope.() -> Unit,
) {
    // State tracking is similar to `LazyGrid`
    val latestContent = rememberUpdatedState(content)
    val rows by remember {
        derivedStateOf {
            val scope = GridLayoutScope(verticalArrangement = verticalArrangement, currentIndentLevel = 0)
            latestContent.value(scope)
            scope.rows
        }
    }

    Layout(
        contents = rows.map { row ->
            @Composable {
                val cells = row.cells
                cells.forEachIndexed { index, cell ->
                    Row {
                        if (index == 0) {
                            InvisibleText(text = sectionIndentText.repeat(row.indentLevel))
                        } else {
                            InvisibleText(text = "\t")
                        }
                        cell.content()
                        if (index == cells.lastIndex) {
                            InvisibleText(text = "\n")
                        }
                    }
                }
            }
        },
        modifier = modifier
    ) { measurables: List<List<Measurable>>, constraints: Constraints ->
        println("measurables = ${measurables.map { it.toList() }}")

        val placeables = measurables.indices.map { _ -> mutableListOf<Placeable>() }
        val columnSpacing = horizontalArrangement.spacing.roundToPx()
        val rowSpacing = verticalArrangement.spacing.roundToPx()
        val columnOffsets = mutableListOf<Int>()
        val rowOffsets = mutableListOf<Int>()

        fun cellIndent(rowIndex: Int, columnIndex: Int) = if (columnIndex == 0) {
            (sectionIndent * rows[rowIndex].indentLevel).roundToPx()
        } else {
            0
        }

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
