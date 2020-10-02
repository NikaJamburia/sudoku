package table

import org.junit.jupiter.api.Test
import table.cells.MutableCell
import table.cells.NO_VALUE
import table.cells.ImmutableCell
import table.cells.collection.SelectionOfCells
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuTableTest /*: BaseSudokuTableTest()*/ {

    @Test
    fun behavesCorrectlyOnFillingACell() {
        val table6X3 = initialize6X3Table();

        // fill a cell with value that wont conflict with anything
        table6X3.fillCell(6, Coordinates(1, 1))
        assertEquals(0, table6X3.conflicts.size)

        // fill a cell with value that will cause conflict in big cell
        table6X3.fillCell(6, Coordinates(3, 3))
        assertEquals(1, table6X3.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(3, 3), table6X3))

        // change same cells value to another conflicting one
        table6X3.fillCell(3, Coordinates(3, 3))
        assertEquals(1, table6X3.conflicts.size)

        // (3, 3) should no longer conflict with (1, 1) and is should with (5, 3)
        assertFalse(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(3, 3), table6X3))
        assertTrue(twoCellsAreConflicting(Coordinates(3, 3), Coordinates(5, 3), table6X3))

        // change same cells value so it wont conflict with anything
        table6X3.fillCell(4, Coordinates(3, 3))
        assertEquals(0, table6X3.conflicts.size)
    }

    @Test
    fun correctlyIdentifiesAndResolvesConflictsInRows() {
        val table = initialize6X3Table()

        // fill cell (1, 1) with value that exists on the same row (6, 1)
        table.fillCell(7, Coordinates(1, 1))
        assertEquals(1, table.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(6, 1), table))

        // fill cell (4, 2) with value that exists on the same row (2, 2)
        table.fillCell(5, Coordinates(4, 2))
        assertEquals(2, table.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(6, 1), table))
        assertTrue(twoCellsAreConflicting(Coordinates(2, 2), Coordinates(4, 2), table))

        // fill cells (3, 3) and (6, 3) with values that exists on the same row in (6, 3) and (5, 3)
        table.fillCell(3, Coordinates(3, 3))
        table.fillCell(2, Coordinates(6, 3))
        assertEquals(4, table.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(6, 1), table))
        assertTrue(twoCellsAreConflicting(Coordinates(2, 2), Coordinates(4, 2), table))
        assertTrue(twoCellsAreConflicting(Coordinates(1, 3), Coordinates(6, 3), table))
        assertTrue(twoCellsAreConflicting(Coordinates(3, 3), Coordinates(5, 3), table))

        // fill all the previous cells in a way to eliminate conflicts
        table.fillCell(8, Coordinates(1, 1))
        table.fillCell(6, Coordinates(4, 2))
        table.fillCell(4, Coordinates(6, 3))
        table.fillCell(6, Coordinates(3, 3))
        assertEquals(0, table.conflicts.size)

        // fill 2 cells on the same row with same values
        table.fillCell(9, Coordinates(2, 1))
        table.fillCell(9, Coordinates(4, 1))
        assertEquals(1, table.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(2, 1), Coordinates(4, 1), table))

    }

    @Test
    fun correctlyIdentifiesAndResolvesConflictsInColumns() {

    }

    fun twoCellsAreConflicting(coordinate1: Coordinates, coordinate2: Coordinates, table: SudokuTable): Boolean =
        table.conflicts.any {
            it.conflictedCells.any { cell -> cell.findLocation().sameAs(coordinate1) }
                    && it.conflictedCells.any { cell -> cell.findLocation().sameAs(coordinate2) }
        }

    fun initialize6X3Table(): SudokuTable {
        val bigCell1 = SelectionOfCells(listOf(
            MutableCell(NO_VALUE, Coordinates(1, 1)),
            MutableCell(NO_VALUE, Coordinates(2, 1)),
            MutableCell(NO_VALUE, Coordinates(3, 1)),
            MutableCell(NO_VALUE, Coordinates(1, 2)),
            ImmutableCell(5, Coordinates(2, 2)),
            MutableCell(NO_VALUE, Coordinates(3, 2)),
            ImmutableCell(2, Coordinates(1, 3)),
            MutableCell(NO_VALUE, Coordinates(2, 3)),
            MutableCell(NO_VALUE, Coordinates(3, 3))
        ))

        val bigCell2 = SelectionOfCells(listOf(
            MutableCell(NO_VALUE, Coordinates(4, 1)),
            MutableCell(NO_VALUE, Coordinates(5, 1)),
            ImmutableCell(7, Coordinates(6, 1)),
            MutableCell(NO_VALUE, Coordinates(4, 2)),
            MutableCell(NO_VALUE, Coordinates(5, 2)),
            MutableCell(NO_VALUE, Coordinates(6, 2)),
            MutableCell(NO_VALUE, Coordinates(4, 3)),
            ImmutableCell(3, Coordinates(5, 3)),
            MutableCell(NO_VALUE, Coordinates(6, 3))
        ))

        return SudokuTable(listOf(bigCell1, bigCell2), mutableListOf())
    }

}