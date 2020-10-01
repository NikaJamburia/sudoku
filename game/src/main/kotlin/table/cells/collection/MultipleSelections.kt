package table.cells.collection

import table.CellConflict

abstract class MultipleSelections: CellCollection {
    abstract fun selectCells(): List<SelectionOfCells>

    override fun findConflicts(): List<CellConflict> =
        selectCells().flatMap { it.findConflicts() }
}