package table.cells.collection

import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.OpenCell
import org.junit.Assert.*


class SelectionOfCellsTest {
    @Test
    fun correctlyIdentifiesConflicts()   {
        val cells = listOf<OpenCell>(
            OpenCell(1, Coordinates(1, 1)),
            OpenCell(2, Coordinates(1, 2)),
            OpenCell(1, Coordinates(1, 3)),
            OpenCell(2, Coordinates(2, 1)),
            OpenCell(1, Coordinates(2, 2)),
            OpenCell(8, Coordinates(2, 3))
        )

        val conflicts = SelectionOfCells(cells).findConflicts()

        assertEquals(2, conflicts.size)
        assertTrue(conflicts[0].conflictedCells.all { it.getValue() == 1 })
        assertTrue(conflicts[1].conflictedCells.all { it.getValue() == 2 })
    }
}