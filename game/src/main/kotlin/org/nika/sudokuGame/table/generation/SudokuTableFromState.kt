package org.nika.sudokuGame.table.generation

import org.nika.sudokuGame.table.CellConflict
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.SudokuTable
import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.cells.ClosedCell
import org.nika.sudokuGame.table.cells.OpenCell
import org.nika.sudokuGame.table.cells.collection.Boxes
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.TableState

class SudokuTableFromState (
    private val state: TableState
): GeneratedSudokuTable {
    override fun generate(): SudokuTable {
        val cells = state.cells.map { createCellFrom(it) }
        return SudokuTable(Boxes(cells))
    }

    private fun createConflicts(cells: List<Cell>): List<CellConflict> =
        state.conflicts
            .map { it.conflictedCells.map { cellState -> Coordinates(cellState.coordinateX, cellState.coordinateY) } }
            .map { it.map { cells.first {cell -> cell.location().equals(it) } } }
            .map { CellConflict(it.toMutableList()) }


    private fun createCellFrom(it: CellState): Cell {
        return if (it.cellIsOpen) {
            OpenCell(it.value, Coordinates(it.coordinateX, it.coordinateY))
        } else {
            ClosedCell(it.value, Coordinates(it.coordinateX, it.coordinateY))
        }
    }
}