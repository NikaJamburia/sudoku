package org.nika.sudokuTable.generation.backtracking

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.SudokuTable
import org.nika.sudokuTable.cells.ClosedCell
import org.nika.sudokuTable.cells.NO_VALUE
import org.nika.sudokuTable.cells.OpenCell
import org.nika.sudokuTable.cells.collection.Boxes
import org.nika.sudokuTable.generation.CleanSudokuTable
import org.nika.sudokuTable.generation.GeneratedSudokuTable

class BacktrackedSudokuTable (
    private val numberOfRemovals: Int
) : GeneratedSudokuTable {

    override fun generate(): SudokuTable {
        val randomlyFilledTable = SolvedSudokuTable(
            CleanSudokuTable().generate()
        ).generate()

        var cellsRemoved = 0
        while (cellsRemoved < numberOfRemovals) {
            val randomCell = randomlyFilledTable.randomFilledCell()
            randomlyFilledTable.emptyCell(randomCell.coordinates())

            val solutions = SolutionsForSudokuTable(randomlyFilledTable).findAll()
            if (solutions.size == 1) {
                cellsRemoved++
            } else {
                randomlyFilledTable.fillCell(randomCell.value, randomCell.coordinates())
            }
        }

        return randomlyFilledTable.withAllCellsClosed()

    }

    private fun SudokuTable.randomFilledCell(): CellState =
        this.internalState()
            .cells
            .filter { it.value != NO_VALUE }
            .random()

    private fun SudokuTable.withAllCellsClosed(): SudokuTable =
        SudokuTable(
            Boxes(
                this.internalState().cells.map {
                    if (it.value != NO_VALUE) {
                        ClosedCell(it.value, it.coordinates())
                    } else {
                        OpenCell(NO_VALUE, it.coordinates())
                    }
                }
            )
        )

    private fun CellState.coordinates(): Coordinates = Coordinates(this.coordinateX, this.coordinateY)

}