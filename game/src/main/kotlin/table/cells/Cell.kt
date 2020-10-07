package table.cells

import table.Coordinates
import table.HasInternalState
import table.interaction.result.CellState

interface Cell : HasInternalState<CellState> {
    fun fillValue(newValue: Int)
    fun empty()
    fun isEmpty(): Boolean
    fun findLocation(): Coordinates
    fun getValue(): Int
}