package org.nika.sudokuTable

import org.nika.sudokuInteraction.state.HasInternalState
import org.nika.sudokuInteraction.state.TableState
import org.nika.sudokuTable.cells.Cell
import org.nika.sudokuTable.cells.OpenCell
import org.nika.sudokuTable.cells.collection.Boxes
import org.nika.sudokuTable.cells.collection.Columns
import org.nika.sudokuTable.cells.collection.Rows
import java.lang.IllegalStateException

class SudokuTable(
    private val boxes: Boxes
): HasInternalState<TableState> {
    fun fillCell(value: Int, coordinates: Coordinates): TableState {
        findCellBy(coordinates).fillValue(value)
        return internalState()
    }

    fun emptyTable(): TableState {
        allCells().forEach {
            try {
                it.empty()
            } catch (e: IllegalStateException) { }
        }
        return internalState()
    }

    fun emptyCell(coordinates: Coordinates): TableState {
        findCellBy(coordinates).empty()
        return internalState()
    }

    private fun isFilled(): Boolean = !allCells().any { it.isEmpty() }
    private fun isEmpty(): Boolean = allCells().filterIsInstance<OpenCell>().all { it.isEmpty() }
    private fun allCells(): List<Cell> = boxes.asList().flatMap { it.content }

    private fun findConflicts(): List<CellConflict> {
        val rowConflicts = Rows(allCells()).findConflicts()
        val columnConflicts = Columns(allCells()).findConflicts()
        val bigCellConflicts = boxes.findConflicts()

        return listOf(rowConflicts, columnConflicts, bigCellConflicts).flatten()
    }

    private fun findCellBy(coordinates: Coordinates) =
        allCells()
            .find { it.location() == coordinates }
            ?: let { throw IllegalStateException("Cell not found") }


    override fun internalState(): TableState =
        TableState(
            allCells().map { it.internalState() },
            findConflicts().map { it.internalState() },
            isFilled(),
            isEmpty()
        )

}