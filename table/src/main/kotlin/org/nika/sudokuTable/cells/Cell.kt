package org.nika.sudokuTable.cells

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuInteraction.state.HasInternalState


interface Cell : HasInternalState<CellState> {
    fun fillValue(newValue: Int)
    fun empty()
    fun isEmpty(): Boolean
    fun location(): Coordinates
    fun getValue(): Int
}