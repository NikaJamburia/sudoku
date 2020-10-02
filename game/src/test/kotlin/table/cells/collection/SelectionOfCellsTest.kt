package table.cells.collection

import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.MutableCell
import org.junit.Assert.*


class SelectionOfCellsTest {
    @Test
    fun correctlyIdentifiesConflicts()   {
        val cells = listOf<MutableCell>(
            MutableCell(1, Coordinates(1, 1)),
            MutableCell(2, Coordinates(1, 2)),
            MutableCell(1, Coordinates(1, 3)),
            MutableCell(2, Coordinates(2, 1)),
            MutableCell(1, Coordinates(2, 2)),
            MutableCell(8, Coordinates(2, 3))
        )

        val conflicts = SelectionOfCells(cells).findConflicts()

        assertEquals(2, conflicts.size)
        assertTrue(conflicts[0].conflictedCells.all { it.getValue() == 1 })
        assertTrue(conflicts[1].conflictedCells.all { it.getValue() == 2 })
    }
}