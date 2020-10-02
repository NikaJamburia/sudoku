package table

import org.junit.jupiter.api.Test
import table.cells.MutableCell
import table.cells.NO_VALUE
import table.cells.collection.ImmutableCell
import table.cells.collection.SelectionOfCells
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuTableTest /*: BaseSudokuTableTest()*/ {
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

    val table6X3 = SudokuTable(listOf(bigCell1, bigCell2), mutableListOf())

    @Test
    fun behavesCorrectlyOnFillingACell() {

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

    fun twoCellsAreConflicting(coordinate1: Coordinates, coordinate2: Coordinates, table: SudokuTable): Boolean =
        table.conflicts.any {
            it.conflictedCells.any { cell -> cell.findLocation().sameAs(coordinate1) }
                    && it.conflictedCells.any { cell -> cell.findLocation().sameAs(coordinate2) }
        }

}