package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.CellConflict

interface CellCollection {
    fun findConflicts(): List<CellConflict>
}