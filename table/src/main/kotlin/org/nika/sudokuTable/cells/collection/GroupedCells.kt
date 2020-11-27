package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.CellConflict

abstract class GroupedCells: CellCollection {
    abstract fun asList(): List<SelectionOfCells>

    override fun findConflicts(): List<CellConflict> =
        asList().flatMap { it.findConflicts() }
}