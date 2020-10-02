package table.cells

import table.Coordinates

interface Cell {
    fun fillValue(newValue: Int)
    fun empty()
    fun isEmpty(): Boolean
    fun findLocation(): Coordinates
    fun getValue(): Int
}