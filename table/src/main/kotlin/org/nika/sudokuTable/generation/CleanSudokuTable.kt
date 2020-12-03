package org.nika.sudokuTable.generation

import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.SudokuTable
import org.nika.sudokuTable.cells.NO_VALUE
import org.nika.sudokuTable.cells.OpenCell
import org.nika.sudokuTable.cells.collection.Boxes

class CleanSudokuTable : GeneratedSudokuTable {
    override fun generate(): SudokuTable {
        val emptyCells = (1..9).map { x ->
            (1..9).map { y ->
                OpenCell(NO_VALUE, Coordinates(x, y))
            }
        }.flatten()

        return SudokuTable(Boxes(emptyCells))
    }
}