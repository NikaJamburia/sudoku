package org.nika.sudokuTable.generation.backtracking

import org.junit.jupiter.api.Test
import org.nika.sudokuTable.generation.CleanSudokuTable
import org.nika.sudokuTable.generation.MockGeneratedSudokuTable
import org.nika.sudokuTable.generation.RandomBasedSudokuTable
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SolvedSudokuTableTest {
    @Test
    fun fillsOutEmptyTable() {
        val solvedSudokuTable = SolvedSudokuTable(
            MockGeneratedSudokuTable().generate()
        )
        val tableState = solvedSudokuTable.generate().internalState()

        assertEquals(81, tableState.cells.size)
        assertTrue(tableState.tableIsFull)
        assertTrue(tableState.conflicts.isEmpty())
    }

    @Test
    fun fillsOutEmptyTablesDifferently() {
        val tableState1 = SolvedSudokuTable(CleanSudokuTable().generate()).generate().internalState()
        val tableState2 = SolvedSudokuTable(CleanSudokuTable().generate()).generate().internalState()
        val tableState3 = SolvedSudokuTable(CleanSudokuTable().generate()).generate().internalState()
        val tableState4 = SolvedSudokuTable(CleanSudokuTable().generate()).generate().internalState()

        assertEquals(4, setOf(tableState1, tableState2, tableState3, tableState4).size)
    }


    @Test
    fun solvesGivenTable() {
        val tableState = SolvedSudokuTable(
            MockGeneratedSudokuTable().generate()
        ).generate().internalState()

        assertEquals(81, tableState.cells.size)
        assertTrue(tableState.tableIsFull)
        assertTrue(tableState.conflicts.isEmpty())
    }
}