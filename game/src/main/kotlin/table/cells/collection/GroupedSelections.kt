package table.cells.collection

import table.CellConflict

abstract class GroupedSelections: CellCollection {
    abstract fun groupCells(): List<SelectionOfCells>

    override fun findConflicts(): List<CellConflict> =
        groupCells().flatMap { it.findConflicts() }
}