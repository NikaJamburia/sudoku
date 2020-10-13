package table.generation

import table.CellConflict
import table.SudokuTable
import table.cells.Cell
import table.cells.ClosedCell
import table.cells.OpenCell
import table.cells.collection.BigCells
import table.state.CellState
import table.state.TableState

class SudokuTableFromState (
    private val state: TableState
): GeneratedSudokuTable {
    override fun table(): SudokuTable {
        val cells = state.cells.map { createCellFrom(it) }
        val conflicts = createConflicts(cells)
        return SudokuTable(BigCells(cells), conflicts)
    }

    private fun createConflicts(cells: List<Cell>): List<CellConflict> =
        state.conflicts
            .map { it.conflictedCells.map { cellState -> cellState.coordinates } }
            .map { it.map { cells.first {cell -> cell.findLocation().sameAs(it) } } }
            .map { CellConflict(it.toMutableList()) }


    private fun createCellFrom(it: CellState): Cell {
        return if (it.cellIsOpen) {
            OpenCell(it.value, it.coordinates)
        } else {
            ClosedCell(it.value, it.coordinates)
        }
    }
}