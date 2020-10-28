package org.nika.sudokuGame.table

import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.state.ConflictState

class CellConflict (
    val conflictedCells: MutableList<Cell>
): HasInternalState<ConflictState> {

    override fun internalState(): ConflictState =
        ConflictState(conflictedCells.map { it.internalState() })

}