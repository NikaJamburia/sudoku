package org.nika.sudokuGame.table

import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.state.ConflictState

class CellConflict (
    val conflictedCells: MutableList<Cell>
): HasInternalState<ConflictState> {
    fun isResolved(): Boolean {
        return conflictedCells.isEmpty()
    }

    fun reEvaluate() {
        conflictedCells
            .groupBy { it.getValue() }
            .values
            .filter { it.size == 1 }
            .forEach { conflictedCells.removeAll(it) }
    }

    override fun internalState(): ConflictState =
        ConflictState(conflictedCells.map { it.internalState() })

}