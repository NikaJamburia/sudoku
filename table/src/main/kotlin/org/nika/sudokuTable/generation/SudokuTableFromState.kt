package org.nika.sudokuTable.generation

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.TableState
import org.nika.sudokuTable.CellConflict
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.SudokuTable
import org.nika.sudokuTable.cells.Cell
import org.nika.sudokuTable.cells.ClosedCell
import org.nika.sudokuTable.cells.OpenCell
import org.nika.sudokuTable.cells.collection.Boxes

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