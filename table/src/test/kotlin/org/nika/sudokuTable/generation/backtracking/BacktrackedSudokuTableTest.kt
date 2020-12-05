package org.nika.sudokuTable.generation.backtracking

import org.junit.jupiter.api.Test
import org.nika.sudokuTable.cells.NO_VALUE
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BacktrackedSudokuTableTest {
    @Test
    fun createsTableWith1Solution() {
        val table = BacktrackedSudokuTable(10).generate()

        val filledCells  = table.internalState().cells.filter { it.value != NO_VALUE }
        assertEquals(81, table.internalState().cells.size)
        assertEquals(71, filledCells.size)
        assertEquals(1, SolutionsForSudokuTable(table).findAll().size)
        assertTrue(filledCells.none { it.cellIsOpen })
    }

    @Test
    fun createsTableWithCorrectNumberOfClosedCells() {
        listOf(10, 15, 20, 30).forEach {numberOfRemovals ->
            val table = BacktrackedSudokuTable(numberOfRemovals).generate()
            val closedCells = table.internalState().cells.filter { !it.cellIsOpen }

            assertEquals(81 - numberOfRemovals, closedCells.size)
            assertTrue(closedCells.all { it.value != NO_VALUE })
        }
    }
}