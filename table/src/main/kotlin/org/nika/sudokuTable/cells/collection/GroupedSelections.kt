package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.CellConflict

abstract class GroupedSelections: CellCollection {
    abstract fun groupedCells(): List<SelectionOfCells>

    override fun findConflicts(): List<CellConflict> =
        groupedCells().flatMap { it.findConflicts() }
}