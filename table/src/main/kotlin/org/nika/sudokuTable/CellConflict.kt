package org.nika.sudokuTable

import org.nika.sudokuInteraction.state.ConflictState
import org.nika.sudokuInteraction.state.HasInternalState
import org.nika.sudokuTable.cells.Cell

class CellConflict (
    val conflictedCells: MutableList<Cell>
): HasInternalState<ConflictState> {

    override fun internalState(): ConflictState =
        ConflictState(conflictedCells.map { it.internalState() })

}