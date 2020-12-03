package org.nika.sudokuTable.generation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CleanSudokuTableTest {
    @Test
    fun generatesEmptySudokuTable() {
        val tableState = CleanSudokuTable().generate().internalState()

        assertEquals(81, tableState.cells.size)
        assertTrue(tableState.tableIsEmpty)
        assertFalse(tableState.tableIsFull)
        assertTrue(tableState.conflicts.isEmpty())
    }
}