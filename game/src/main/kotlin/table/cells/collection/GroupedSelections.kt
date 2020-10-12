package table.cells.collection

import table.CellConflict

abstract class GroupedSelections: CellCollection {
    abstract fun groupedCells(): List<SelectionOfCells>

    override fun findConflicts(): List<CellConflict> =
        groupedCells().flatMap { it.findConflicts() }
}