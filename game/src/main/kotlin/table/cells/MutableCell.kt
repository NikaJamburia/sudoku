package table.cells

import table.Coordinates

const val NO_VALUE = -1
data class MutableCell (
    private var value: Int,
    private var coordinates: Coordinates
) : Cell {
    override fun fillValue(newValue: Int) {
        value = newValue
    }

    override fun empty() {
        value = NO_VALUE
    }

    override fun isEmpty(): Boolean =
        value == NO_VALUE

    override fun findLocation(): Coordinates = coordinates
    override fun getValue(): Int = value

}