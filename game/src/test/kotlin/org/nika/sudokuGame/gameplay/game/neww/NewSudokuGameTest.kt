package org.nika.sudokuGame.gameplay.game.neww

import org.junit.jupiter.api.Test
import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.cells.ClosedCell
import org.nika.sudokuGame.table.cells.OpenCell
import org.nika.sudokuGame.table.cells.collection.Columns
import org.nika.sudokuGame.table.cells.collection.Rows
import org.nika.sudokuGame.table.generation.MockGeneratedSudokuTable
import org.nika.sudokuGame.table.state.CellState
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NewSudokuGameTest {

    @Test
    fun startsNewGameWithMockedTable() {
        val params = SudokuTableGenerationParameters(TableGenerationAlgorithm.MOCKED, 0, 0, 0)
        val newGameState = NewSudokuGame(params).start().internalState()

        assertEquals(0, newGameState.numberOfTurns)
        assertEquals("00:00:00", newGameState.playedTime)
        assertFalse(newGameState.gameIsWon)
        assertTrue(newGameState.tableState.tableIsEmpty)
        assertEquals(MockGeneratedSudokuTable().generate().internalState(), newGameState.tableState)
    }

    @Test
    fun startsNewGameWithRandomTable() {
        val params = SudokuTableGenerationParameters(TableGenerationAlgorithm.RANDOM, 9, 9, 20)
        val newGameState = NewSudokuGame(params).start().internalState()

        assertTrue(newGameState.tableState.tableIsEmpty)
        assertEquals(20, newGameState.tableState.cells.count { !it.cellIsOpen })
        assertEquals(9, Columns(newGameState.tableState.cells.map { stateToCell(it) }).groupedCells().first().content.size)
        assertEquals(9, Rows(newGameState.tableState.cells.map { stateToCell(it) }).groupedCells().first().content.size)
    }

    private fun stateToCell(cellState: CellState): Cell =
        if (cellState.cellIsOpen) {
            OpenCell(cellState.value, cellState.coordinates)
        } else {
            ClosedCell(cellState.value, cellState.coordinates)
        }

}