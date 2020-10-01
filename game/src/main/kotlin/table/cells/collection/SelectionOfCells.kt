package table.cells.collection

import table.CellConflict
import table.cells.Cell

open class SelectionOfCells (
    val content: List<Cell>
) : CellCollection {
    override fun findConflicts(): List<CellConflict> {
        return content
            .groupBy { it.getValue() }
            .values
            .filter { it.size > 1 }
            .map { CellConflict(it.toMutableList()) }
    }
}