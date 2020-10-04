package table

import org.junit.jupiter.api.Test
import table.cells.Cell
import table.cells.OpenCell
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CellConflictTest {
    @Test
    fun canCorrectlyReEvaluateItself() {
        val conflictedCells = mutableListOf<Cell>(
            OpenCell(1, Coordinates(1, 1)),
            OpenCell(1, Coordinates(1, 2)),
            OpenCell(1, Coordinates(1, 3)),
            OpenCell(1, Coordinates(1, 4)),
            OpenCell(1, Coordinates(1, 5))
        )

        val conflict = CellConflict(conflictedCells)

        conflictedCells[2].fillValue(2)

        conflict.reEvaluate()
        assertEquals(4, conflict.conflictedCells.size)

        var newValue = 3;
        conflictedCells.forEach {
            it.fillValue(newValue)
            newValue++
        }

        conflict.reEvaluate()
        assertTrue(conflict.isResolved())

    }
}