package org.nika.sudokuGame.table.cells.collection

import org.nika.sudokuGame.table.CellConflict
import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.cells.NO_VALUE

open class SelectionOfCells (
    val content: List<Cell>
) : CellCollection {
    override fun findConflicts(): List<CellConflict> =
        content
            .groupBy { it.getValue() }
            .filter { it.key != NO_VALUE }
            .values
            .filter { it.size > 1 }
            .map { CellConflict(it.toMutableList()) }
}