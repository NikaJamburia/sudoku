package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.CellConflict
import org.nika.sudokuTable.cells.Cell
import org.nika.sudokuTable.cells.NO_VALUE

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