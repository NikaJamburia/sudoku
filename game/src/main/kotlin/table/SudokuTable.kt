package table

import table.cells.Cell
import table.cells.OpenCell
import table.cells.collection.Boxes
import table.cells.collection.Columns
import table.cells.collection.Rows
import table.state.TableState
import java.lang.IllegalStateException

class SudokuTable(
    private val boxes: Boxes
): HasInternalState<TableState> {
    fun fillCell(value: Int, coordinates: Coordinates): TableState {
        allCells()
            .find { it.location() == coordinates }
            ?.let {
                it.fillValue(value)
                return internalState()
            }
            ?: let { throw IllegalStateException("Cell not found") }
    }

    fun empty(): TableState {
        allCells().forEach {
            try {
                it.empty()
            } catch (e: IllegalStateException) { }
        }
        return internalState()
    }

    private fun isFilled(): Boolean = !allCells().any { it.isEmpty() }
    private fun isEmpty(): Boolean = allCells().filterIsInstance<OpenCell>().all { it.isEmpty() }
    private fun allCells(): List<Cell> = boxes.groupedCells().flatMap { it.content }

    private fun findConflicts(): List<CellConflict> {
        val rowConflicts = Rows(allCells()).findConflicts()
        val columnConflicts = Columns(allCells()).findConflicts()
        val bigCellConflicts = boxes.findConflicts()

        return listOf(rowConflicts, columnConflicts, bigCellConflicts).flatten()
    }

    override fun internalState(): TableState =
        TableState(
            allCells().map { it.internalState() },
            findConflicts().map { it.internalState() },
            isFilled(),
            isEmpty()
        )

}