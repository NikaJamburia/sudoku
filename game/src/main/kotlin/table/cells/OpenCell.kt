package table.cells

import table.Coordinates
import table.state.CellState
import java.lang.IllegalArgumentException

const val NO_VALUE = 0
data class OpenCell (
    private var value: Int,
    private var coordinates: Coordinates
) : Cell {

    override fun fillValue(newValue: Int) {
        if (newValue < 0) {
            throw IllegalArgumentException("Value of cell can not be negative number!")
        } else {
            value = newValue
        }
    }

    override fun empty() {
        value = NO_VALUE
    }

    override fun isEmpty(): Boolean = value == NO_VALUE
    override fun findLocation(): Coordinates = coordinates
    override fun getValue(): Int = value

    override fun internalState(): CellState =
        CellState(value, coordinates, true)
}