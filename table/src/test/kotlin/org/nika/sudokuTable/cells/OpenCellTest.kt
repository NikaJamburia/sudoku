package org.nika.sudokuTable.cells

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.nika.sudokuTable.Coordinates
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OpenCellTest {
    @Test
    fun valueGetsCorrectlyFilledAndEmptied() {
        val cell = OpenCell(NO_VALUE, Coordinates(1, 2))

        cell.fillValue(1)
        assertEquals(1, cell.getValue())
        assertFalse(cell.isEmpty())

        cell.empty()
        assertEquals(NO_VALUE, cell.getValue())
        assertTrue(cell.isEmpty())

        assertThrows(IllegalArgumentException::class.java) { cell.fillValue(-1)}
        assertThrows(IllegalArgumentException::class.java) { cell.fillValue(10)}
    }

}