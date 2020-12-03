package org.nika.sudokuTable.generation.backtracking

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.SudokuTable
import org.nika.sudokuTable.cells.NO_VALUE
import org.nika.sudokuTable.generation.GeneratedSudokuTable
import org.nika.sudokuTable.generation.SudokuTableCopy

class SolvedSudokuTable (
    sourceTable: SudokuTable
) : GeneratedSudokuTable {
    private val allowedValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    private val tableToSolve = SudokuTableCopy(sourceTable).generate()

    override fun generate(): SudokuTable {
        solve()
        return tableToSolve
    }
    
    private fun solve(): Boolean {
        tableToSolve.internalState().cells.forEach { cell ->
            if (cell.isEmpty()) {
                allowedValues.shuffled().forEach { value ->
                    tableToSolve.fillCell(value, cell.coordinates())
                    if (!tableToSolve.containsConflicts() && solve()) {
                        return true
                    }
                    tableToSolve.emptyCell(cell.coordinates())
                }
                return false
            }
        }
        return true
    }

    private fun SudokuTable.containsConflicts(): Boolean = this.internalState().conflicts.isNotEmpty()
    private fun CellState.coordinates(): Coordinates = Coordinates(this.coordinateX, this.coordinateY)
    private fun CellState.isEmpty(): Boolean = this.value == NO_VALUE
}