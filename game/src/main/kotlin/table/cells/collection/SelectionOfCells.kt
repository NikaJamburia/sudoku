package table.cells.collection

import table.CellConflict
import table.cells.Cell

class SelectionOfCells (
    val cells: List<Cell>
) : CellCollection {
    override fun findConflicts(): List<CellConflict> {
        return cells
            .groupBy { it.getValue() }
            .values
            .filter { it.size > 1 }
            .map { CellConflict(it.toMutableList()) }
    }
}