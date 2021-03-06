package org.nika.sudokuTable.generation

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.ConflictState
import org.nika.sudokuInteraction.state.TableState
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.coordinates
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuTableFromStateTest {
    @Test
    fun correctlyGeneratesTableCellsFromGivenState() {
        val state = TableState(cells2X2(), listOf(), true, false)

        val generatedTable = SudokuTableFromState(state).generate()
        assertThrows(IllegalStateException::class.java) { generatedTable.fillCell(5, Coordinates(1, 2)) }

        val stateOfGeneratedTable = generatedTable.internalState()
        assertFalse(stateOfGeneratedTable.tableIsEmpty)
        assertTrue(stateOfGeneratedTable.tableIsFull)

        assertEquals(4, stateOfGeneratedTable.cells.size)
        assertFalse(stateOfGeneratedTable.cells.first { it.coordinates() == Coordinates(1, 2) }.cellIsOpen)
        assertEquals(3, stateOfGeneratedTable.cells.filter { it.cellIsOpen }.size)
    }

    @Test
    fun correctlyGeneratesCellConflictsFromGivenState() {
        val state = TableState(cells2X2(), conflicts2X2(), true, false)
        val generatedTable = SudokuTableFromState(state).generate()
        var stateOfGeneratedTable = generatedTable.internalState()

        assertEquals(1, stateOfGeneratedTable.conflicts.size)

        stateOfGeneratedTable = generatedTable.fillCell(5, Coordinates(1, 1))

        assertEquals(0, stateOfGeneratedTable.conflicts.size)

        stateOfGeneratedTable = generatedTable.fillCell(2, Coordinates(1, 1))
        assertEquals(2, stateOfGeneratedTable.conflicts.size)

        val newTable = SudokuTableFromState(stateOfGeneratedTable).generate()
        assertEquals(2, newTable.internalState().conflicts.size)
    }

    private fun cells2X2(): List<CellState> =
        listOf(
            CellState(1, 1, 1, true),
            CellState(2, 1, 2, false),
            CellState(3, 2, 1, true),
            CellState(1, 2, 2, true)
        )

    private fun conflicts2X2(): List<ConflictState> =
        listOf(
            ConflictState(
                listOf(
                    CellState(1, 2, 2, true),
                    CellState(1, 1, 1, true)
                )
            )
        )
}