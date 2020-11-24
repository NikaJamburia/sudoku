package org.nika.sudokuTable.cells

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuTable.Coordinates
import java.lang.IllegalArgumentException

const val NO_VALUE = 0
data class OpenCell (
    private var value: Int,
    private var coordinates: Coordinates
) : Cell {

    override fun fillValue(newValue: Int) {
        if (newValue < 0 || newValue > 9) {
            throw IllegalArgumentException("Value of cell must be between 0 and 9!")
        } else {
            value = newValue
        }
    }

    override fun empty() {
        value = NO_VALUE
    }

    override fun isEmpty(): Boolean = value == NO_VALUE
    override fun location(): Coordinates = coordinates
    override fun getValue(): Int = value

    override fun internalState(): CellState =
        CellState(value, coordinates.x, coordinates.y, true)
}