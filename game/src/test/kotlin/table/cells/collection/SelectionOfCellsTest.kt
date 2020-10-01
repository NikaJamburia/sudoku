package table.cells.collection

import org.junit.Assert
import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.Cell
import org.junit.Assert.*


class SelectionOfCellsTest {
    @Test
    fun correctlyIdentifiesConflicts()   {
        val cells = listOf<Cell>(
            Cell(1, Coordinates(1, 1)),
            Cell(2, Coordinates(1, 2)),
            Cell(1, Coordinates(1, 3)),
            Cell(2, Coordinates(2, 1)),
            Cell(1, Coordinates(2, 2)),
            Cell(8, Coordinates(2, 3))
        )

        val conflicts = SelectionOfCells(cells).findConflicts()

        assertEquals(2, conflicts.size)
        assertTrue(conflicts[0].conflictedCells.all { it.getValue() == 1 })
        assertTrue(conflicts[1].conflictedCells.all { it.getValue() == 2 })
    }
}