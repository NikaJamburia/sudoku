package org.nika.sudokuTable.cells

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuTable.Coordinates

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ClosedCell (
    private val value: Int,
    private val coordinates: Coordinates
): Cell {

    override fun fillValue(newValue: Int) {
        throw IllegalStateException("Value of closed cell can not be changed")
    }

    override fun empty() {
        throw IllegalStateException("Value of closed cell can not be changed")
    }

    override fun isEmpty(): Boolean = false
    override fun location(): Coordinates = coordinates
    override fun getValue(): Int = value

    init {
        if (value == NO_VALUE) {
            throw IllegalArgumentException("Closed cell can not be initialized without a value")
        }
    }

    override fun internalState(): CellState =
        CellState(value, coordinates.x, coordinates.y, false)
}