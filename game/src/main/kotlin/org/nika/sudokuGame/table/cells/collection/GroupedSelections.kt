package org.nika.sudokuGame.table.cells.collection

import org.nika.sudokuGame.table.CellConflict

abstract class GroupedSelections: CellCollection {
    abstract fun groupedCells(): List<SelectionOfCells>

    override fun findConflicts(): List<CellConflict> =
        groupedCells().flatMap { it.findConflicts() }
}