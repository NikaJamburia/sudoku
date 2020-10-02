package table.cells

import table.Coordinates
import table.cells.Cell
import table.cells.NO_VALUE
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ImmutableCell (
    private var value: Int,
    private var coordinates: Coordinates
): Cell {
    override fun fillValue(newValue: Int) {
        throw IllegalStateException("Value of immutable cell can not be changed")
    }

    override fun empty() {
        throw IllegalStateException("Value of immutable cell can not be changed")
    }

    override fun isEmpty(): Boolean = false
    override fun findLocation(): Coordinates = coordinates
    override fun getValue(): Int = value

    init {
        if (value == NO_VALUE) {
            throw IllegalArgumentException("Immutable cell can not bi initialized without a value")
        }
    }

}