package table.cells.collection

import table.CellConflict
import table.cells.Cell
import table.cells.NO_VALUE

open class SelectionOfCells (
    val content: List<Cell>
) : CellCollection {
    override fun findConflicts(): List<CellConflict> {
        return content
            .groupBy { it.getValue() }
            .filter { it.key != NO_VALUE }
            .values
            .filter { it.size > 1 }
            .map { CellConflict(it.toMutableList()) }
    }
}