package table.cells.collection

import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.MutableCell
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RowsAndColumnsTest {
    private val source = listOf<MutableCell>(
        MutableCell(1, Coordinates(1, 1)),
        MutableCell(2, Coordinates(2, 1)),
        MutableCell(1, Coordinates(3, 1)),
        MutableCell(1, Coordinates(1, 2)),
        MutableCell(5, Coordinates(2, 2)),
        MutableCell(7, Coordinates(3, 2)),
        MutableCell(9, Coordinates(1, 3)),
        MutableCell(8, Coordinates(2, 3)),
        MutableCell(8, Coordinates(3, 3))
    )

    @Test
    fun shouldCorrectlyDivideCellsIntoRows() {
        val rows = Rows(source);
        val listOfRows = rows.groupCells()

        assertEquals(3, listOfRows.size)
        assertTrue(listOfRows[0].content.all { it.findLocation().y == 1 })
        assertTrue(listOfRows[1].content.all { it.findLocation().y == 2 })
        assertTrue(listOfRows[2].content.all { it.findLocation().y == 3 })

        assertEquals(2, rows.findConflicts().size)
    }

    @Test
    fun shouldCorrectlyDivideCellsIntoColumns() {
        val columns = Columns(source);
        val listOfCols = columns.groupCells()

        assertEquals(3, listOfCols.size)
        assertTrue(listOfCols[0].content.all { it.findLocation().x == 1 })
        assertTrue(listOfCols[1].content.all { it.findLocation().x == 2 })
        assertTrue(listOfCols[2].content.all { it.findLocation().x == 3 })

        assertEquals(1, columns.findConflicts().size)
    }
}