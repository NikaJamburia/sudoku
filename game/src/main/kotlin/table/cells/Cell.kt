package table.cells

import table.Coordinates
import table.HasInternalState
import table.state.CellState

interface Cell : HasInternalState<CellState> {
    fun fillValue(newValue: Int)
    fun empty()
    fun isEmpty(): Boolean
    fun location(): Coordinates
    fun getValue(): Int
}