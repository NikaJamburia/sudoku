package service

import gameplay.game.GameState
import gameplay.interaction.request.FillCellRequest
import gameplay.interaction.request.SudokuInteractionRequest
import gameplay.interaction.result.Error
import gameplay.interaction.result.GameWon
import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.NO_VALUE
import table.state.CellState
import table.state.TableState
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuGameServiceTest {

    @Test
    fun correctlyFillsCells() {
        val gameState = game4X4With1Empty("00:00:30", 1)
        val request = FillCellRequest(5, 1, 1, gameState, "00:00:45")

        val result = SudokuGameService().fillCell(request)

        assertTrue(result.isSuccessful)
        assertEquals("Successfully filled cell" , result.message)
        assertEquals("00:00:45" , result.content.playedTime)
        assertEquals(2 , result.content.numberOfTurns)
        assertEquals(5 , result.content.tableState.cells.first { it.coordinates == Coordinates(1, 1) }.value)
    }

    @Test
    fun tellsUsWhenGameIsWon() {
        val gameState = game4X4With1Empty("00:00:30", 1)

        val winningRequest = FillCellRequest(4, 2, 2, gameState, "00:00:50")
        val winningResult = SudokuGameService().fillCell(winningRequest)

        assertTrue(winningResult.isSuccessful)
        assertTrue(winningResult is GameWon)
        assertEquals("You won" , winningResult.message)
        assertEquals("00:00:50" , winningResult.content.playedTime)
        assertTrue(winningResult.content.tableState.tableIsFull)
    }

    @Test
    fun returnsErrorWhenDoingInvalidAction() {
        val gameState = game4X4With1Empty("00:00:30", 1)

        // try to fill closed cell
        val request1 = FillCellRequest(4, 1, 2, gameState, "00:00:31")
        val result1 = SudokuGameService().fillCell(request1)
        assertFalse(result1.isSuccessful)
        assertTrue(result1 is Error)
        assertEquals("Value of closed cell can not be changed", result1.message)

        // try to fill not existing cell
        val request2 = FillCellRequest(4, 1, 5, gameState, "00:00:32")
        val result2 = SudokuGameService().fillCell(request2)
        assertFalse(result2.isSuccessful)
        assertTrue(result2 is Error)
        assertEquals("Cell not found", result2.message)

        // try to fill invalid value
        val request3 = FillCellRequest(-1, 1, 1, gameState, "00:00:32")
        val result3 = SudokuGameService().fillCell(request3)
        assertFalse(result3.isSuccessful)
        assertTrue(result3 is Error)
        assertEquals("Value of cell must be between 0 and 9!", result3.message)
    }

    @Test
    fun correctlyRestartsTheGame() {
        val game = game4X4With1Empty("00:31:15", 21)
        val result = SudokuGameService().restart(SudokuInteractionRequest(game, "00:32:16"))

        assertTrue(result.isSuccessful)
        assertEquals("New game started", result.message)
        assertEquals("00:00:00", result.content.playedTime)
        assertEquals(0, result.content.numberOfTurns)
        assertTrue(result.content.tableState.tableIsEmpty)
    }

    private fun game4X4With1Empty(playedTime: String, turns: Int): GameState {
        val tableState = TableState(
            listOf(
                CellState(1, Coordinates(1, 1), true),
                CellState(2, Coordinates(1, 2), false),
                CellState(3, Coordinates(2, 1), true),
                CellState(NO_VALUE, Coordinates(2, 2), true)
            ), listOf(), false, false)

        return GameState(playedTime, turns, false, tableState)
    }
}