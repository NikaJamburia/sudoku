package org.nika.sudokuTable.cells

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.nika.sudokuTable.Coordinates
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ClosedCellTest {
    @Test
    fun changingValueIsNotPossible() {
        val immutableCell = ClosedCell(1, Coordinates(1, 1))
        assertThrows(IllegalStateException::class.java) { immutableCell.fillValue(1) }
        assertThrows(IllegalStateException::class.java) { immutableCell.empty() }
    }

    @Test
    fun canNotBeInitializedWithoutValue() {
        assertThrows(IllegalArgumentException::class.java) { ClosedCell(NO_VALUE, Coordinates(1, 1)) }
    }
}