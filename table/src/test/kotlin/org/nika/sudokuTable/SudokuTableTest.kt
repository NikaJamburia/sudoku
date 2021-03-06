package org.nika.sudokuTable

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.state.TableState
import org.nika.sudokuTable.cells.NO_VALUE
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuTableTest {

    @Test
    fun behavesCorrectlyOnFillingACell() {
        val table6X3 = generate6X3Table()

        // fill a cell with value that wont conflict with anything
        var tableState = table6X3.fillCell(6, Coordinates(1, 1))
        assertEquals(0, tableState.conflicts.size)

        // fill a cell with value that will cause conflict in big cell
        tableState = table6X3.fillCell(6, Coordinates(3, 3))
        assertEquals(1, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(3, 3), tableState))

        // change same cells value to another conflicting one
        tableState = table6X3.fillCell(3, Coordinates(3, 3))
        assertEquals(1, tableState.conflicts.size)

        // (3, 3) should no longer conflict with (1, 1) and is should with (5, 3)
        assertFalse(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(3, 3), tableState))
        assertTrue(twoCellsAreConflicting(Coordinates(3, 3), Coordinates(5, 3), tableState))

        // change same cells value so it wont conflict with anything
        tableState = table6X3.fillCell(4, Coordinates(3, 3))
        assertEquals(0, tableState.conflicts.size)
    }

    @Test
    fun correctlyIdentifiesAndResolvesConflictsInRows() {
        val table = generate6X3Table()

        // fill cell (1, 1) with value that exists on the same row (6, 1)
        var tableState = table.fillCell(7, Coordinates(1, 1))
        assertEquals(1, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(6, 1), tableState))

        // fill cell (4, 2) with value that exists on the same row (2, 2)
        tableState = table.fillCell(5, Coordinates(4, 2))
        assertEquals(2, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(6, 1), tableState))
        assertTrue(twoCellsAreConflicting(Coordinates(2, 2), Coordinates(4, 2), tableState))

        // fill cells (3, 3) and (6, 3) with values that exists on the same row in (6, 3) and (5, 3)
        table.fillCell(3, Coordinates(3, 3))
        tableState = table.fillCell(2, Coordinates(6, 3))
        assertEquals(4, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(6, 1), tableState))
        assertTrue(twoCellsAreConflicting(Coordinates(2, 2), Coordinates(4, 2), tableState))
        assertTrue(twoCellsAreConflicting(Coordinates(1, 3), Coordinates(6, 3), tableState))
        assertTrue(twoCellsAreConflicting(Coordinates(3, 3), Coordinates(5, 3), tableState))

        // fill all the previous cells in a way to eliminate conflicts
        table.fillCell(8, Coordinates(1, 1))
        table.fillCell(6, Coordinates(4, 2))
        table.fillCell(4, Coordinates(6, 3))
        tableState = table.fillCell(6, Coordinates(3, 3))
        assertEquals(0, tableState.conflicts.size)

        // fill 2 cells on the same row with same values
        table.fillCell(9, Coordinates(2, 1))
        tableState = table.fillCell(9, Coordinates(4, 1))
        assertEquals(1, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(2, 1), Coordinates(4, 1), tableState))

    }

    @Test
    fun correctlyIdentifiesAndResolvesConflictsInColumns() {
        val table = generate3X6Table()

        var tableState = table.fillCell(2, Coordinates(1, 6))
        assertEquals(1, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 3), Coordinates(1, 6), tableState))

        tableState = table.fillCell(3, Coordinates(1, 1))
        assertEquals(2, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(1, 5), tableState))

        tableState = table.fillCell(5, Coordinates(2, 4))
        assertEquals(3, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(2, 4), Coordinates(2, 2), tableState))

        tableState = table.fillCell(7, Coordinates(3, 1))
        assertEquals(4, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(3, 1), Coordinates(3, 4), tableState))

        table.fillCell(6, Coordinates(1, 6))
        table.fillCell(4, Coordinates(1, 1))
        table.fillCell(9, Coordinates(2, 4))
        tableState = table.fillCell(9, Coordinates(3, 1))
        assertEquals(0, tableState.conflicts.size)

        table.fillCell(8, Coordinates(3, 3))
        table.fillCell(8, Coordinates(3, 5))
        table.fillCell(1, Coordinates(3, 6))
        tableState = table.fillCell(1, Coordinates(3, 2))
        assertEquals(2, tableState.conflicts.size)
    }

    @Test
    fun correctlyIdentifiesAndResolvesConflictsInsideBigCells() {
        val table = generate6X3Table()

        var tableState = table.fillCell(5, Coordinates(1, 1))
        assertEquals(1, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(2, 2), tableState))

        tableState = table.fillCell(2, Coordinates(3, 1))
        assertEquals(2, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(3, 1), Coordinates(1, 3), tableState))

        tableState = table.fillCell(7, Coordinates(5, 2))
        assertEquals(3, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(5, 2), Coordinates(6, 1), tableState))
        assertEquals(2, findConflictByValueOfCells(7, tableState).conflictedCells.size)

        tableState = table.fillCell(7, Coordinates(4, 3))
        assertEquals(3, tableState.conflicts.size)
        assertEquals(3, findConflictByValueOfCells(7, tableState).conflictedCells.size)
        assertTrue(twoCellsAreConflicting(Coordinates(4, 3), Coordinates(6, 1), tableState))
        assertTrue(twoCellsAreConflicting(Coordinates(4, 3), Coordinates(5, 2), tableState))

        tableState = table.fillCell(3, Coordinates(4, 2))
        assertEquals(4, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(4, 2), Coordinates(5, 3), tableState))

        table.fillCell(3, Coordinates(1, 1))
        table.fillCell(8, Coordinates(3, 1))
        table.fillCell(4, Coordinates(5, 2))
        table.fillCell(5, Coordinates(4, 3))
        tableState = table.fillCell(9, Coordinates(4, 2))
        assertEquals(0, tableState.conflicts.size)
    }

    @Test
    fun correctlyResolvesConflictsInAllCellSelections() {
        val table = generate6X6Table()
        var tableState: TableState

        tableState = table.fillCell(5, Coordinates(1, 1))
        assertEquals(2, tableState.conflicts.size)
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(2, 3), tableState))
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(1, 5), tableState))

        tableState = table.fillCell(5, Coordinates(6, 1))
        assertTrue(twoCellsAreConflicting(Coordinates(1, 1), Coordinates(6, 1), tableState))
        assertEquals(3, tableState.conflicts.size)

        table.fillCell(6, Coordinates(5, 2))
        tableState = table.fillCell(6, Coordinates(5, 3))
        assertEquals(6, tableState.conflicts.size)
        assertEquals(2, findConflictByValueOfCells(6, tableState).conflictedCells.size)

        tableState = table.fillCell(6, Coordinates(4, 2))
        assertEquals(6, tableState.conflicts.size)
        assertEquals(3, findConflictByValueOfCells(6, tableState).conflictedCells.size)
    }

    @Test
    fun shouldBeCorrectlyEmptied() {
        val table = generate6X6Table()

        table.fillCell(5, Coordinates(1, 1))
        table.fillCell(5, Coordinates(6, 1))
        table.fillCell(6, Coordinates(5, 2))
        table.fillCell(6, Coordinates(5, 3))
        val tableState: TableState = table.fillCell(6, Coordinates(4, 2))

        assertFalse(tableState.tableIsEmpty)
        assertFalse(tableState.conflicts.isEmpty())

        table.emptyTable()
        assertTrue(table.internalState().tableIsEmpty)
        assertTrue(table.internalState().conflicts.isEmpty())
    }

    @Test
    fun correctlyEmptiesACell() {
        val table = generate6X6Table()
        table.fillCell(5, Coordinates(1, 1))
        var state = table.fillCell(6, Coordinates(2, 1))

        assertEquals(5, state.findCell(1, 1).value)
        assertEquals(6, state.findCell(2, 1).value)

        state = table.emptyCell(Coordinates(1, 1))
        assertEquals(NO_VALUE, state.findCell(1, 1).value)
        assertEquals(6, state.findCell(2, 1).value)

        state = table.emptyCell(Coordinates(2, 1))
        assertEquals(NO_VALUE, state.findCell(2, 1).value)

    }

    @Test
    fun throwsExceptionWhenTryingToEmptyClosedOrNonExistantCell() {
        val table = generate6X6Table()

        assertThrows(IllegalStateException::class.java) { table.emptyCell(Coordinates(7, 7)) }
        assertThrows(IllegalStateException::class.java) { table.emptyCell(Coordinates(3, 2)) }
    }

    private fun findConflictByValueOfCells(value: Int, tableState: TableState) =
        tableState.conflicts.first { it.conflictedCells.all { cell -> cell.value == value } }

}