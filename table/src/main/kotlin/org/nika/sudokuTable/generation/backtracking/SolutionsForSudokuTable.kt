package org.nika.sudokuTable.generation.backtracking

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.TableState
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.SudokuTable
import org.nika.sudokuTable.cells.NO_VALUE
import org.nika.sudokuTable.generation.SudokuTableCopy

class SolutionsForSudokuTable (
    sudokuTable : SudokuTable
) {
    private val tableCopy = SudokuTableCopy(sudokuTable).generate()
    private var solutions: MutableList<TableState> = mutableListOf()
    private val allowedValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    fun findAll(): List<TableState> {
        countSolutions()
        return solutions
    }

    private fun countSolutions(): Boolean {
        tableCopy.internalState().cells.forEach { cell ->
            if (cell.isEmpty()) {
                allowedValues.shuffled().forEach { value ->
                    tableCopy.fillCell(value, cell.coordinates())
                    if (!tableCopy.containsConflicts() && tableCopy.internalState().tableIsFull) {
                        solutions.add(tableCopy.internalState())
                    } else if (!tableCopy.containsConflicts() && countSolutions()) {
                        return true
                    }
                    tableCopy.emptyCell(cell.coordinates())
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