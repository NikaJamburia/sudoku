package org.nika.sudokuGame.table.cells

import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.HasInternalState
import org.nika.sudokuInteraction.state.CellState

interface Cell : HasInternalState<CellState> {
    fun fillValue(newValue: Int)
    fun empty()
    fun isEmpty(): Boolean
    fun location(): Coordinates
    fun getValue(): Int
}