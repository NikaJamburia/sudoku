package org.nika.sudokuGame.table.cells.collection

import org.nika.sudokuGame.table.CellConflict

interface CellCollection {
    fun findConflicts(): List<CellConflict>
}