package table.cells

import org.junit.jupiter.api.Test
import table.Coordinates
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CellTest {
    @Test
    fun valueGetsCorrectlyFilledAndEmptied() {
        val cell = Cell(NO_VALUE, Coordinates(1, 2))

        cell.fillValue(1)
        assertEquals(1, cell.getValue())
        assertFalse(cell.isEmpty())

        cell.empty()
        assertEquals(NO_VALUE, cell.getValue())
        assertTrue(cell.isEmpty())
    }

}