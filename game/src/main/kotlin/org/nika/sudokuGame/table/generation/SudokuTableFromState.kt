package org.nika.sudokuGame.table.generation

import org.nika.sudokuGame.table.CellConflict
import org.nika.sudokuGame.table.SudokuTable
import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.cells.ClosedCell
import org.nika.sudokuGame.table.cells.OpenCell
import org.nika.sudokuGame.table.cells.collection.Boxes
import org.nika.sudokuGame.table.state.CellState
import org.nika.sudokuGame.table.state.TableState

class SudokuTableFromState (
    private val state: TableState
): GeneratedSudokuTable {
    override fun generate(): SudokuTable {
        val cells = state.cells.map { createCellFrom(it) }
        return SudokuTable(Boxes(cells))
    }

    private fun createConflicts(cells: List<Cell>): List<CellConflict> =
        state.conflicts
            .map { it.conflictedCells.map { cellState -> cellState.coordinates } }
            .map { it.map { cells.first {cell -> cell.location().equals(it) } } }
            .map { CellConflict(it.toMutableList()) }


    private fun createCellFrom(it: CellState): Cell {
        return if (it.cellIsOpen) {
            OpenCell(it.value, it.coordinates)
        } else {
            ClosedCell(it.value, it.coordinates)
        }
    }
}