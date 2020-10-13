package table.cells.collection

import table.cells.Cell
import kotlin.math.max

class BigCells(
    private val cells: List<Cell>
) : GroupedSelections() {
    override fun groupedCells(): List<SelectionOfCells> { // TODO: Requires heavy testing
        val maxX = max(3, cells.map { it.findLocation().x }.max()!!)
        val maxY = max(3, cells.map { it.findLocation().y }.max()!!)

        val bigCellXUpperBorders = (1..maxX).filter { it.canBeDividedBy(3) }
        val bigCellYUpperBorders = (1..maxY).filter { it.canBeDividedBy(3) }

        return bigCellXUpperBorders.map { x ->
            bigCellYUpperBorders.map { y ->
                SelectionOfCells(
                    cells.filter {
                        cellFitsIntoBorders(it, y, x)
                    }
                )
            }
        }.flatten()

    }

    private fun cellFitsIntoBorders(it: Cell, y: Int, x: Int) =
        (it.findLocation().y <= y && it.findLocation().y > y - 3) && (it.findLocation().x <= x && it.findLocation().x > x - 3)

    private fun Int.canBeDividedBy(other: Int): Boolean = this % other == 0

}

