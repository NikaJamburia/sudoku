package table.cells

import table.Coordinates

const val NO_VALUE = -1
data class Cell (
    private var value: Int,
    private var coordinates: Coordinates
) {
    fun fillValue(newValue: Int) {
        value = newValue
    }

    fun empty() {
        value = NO_VALUE
    }

    fun isEmpty(): Boolean =
        value == NO_VALUE

    fun findLocation(): Coordinates = coordinates
    fun getValue(): Int = value

}