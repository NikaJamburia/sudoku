package org.nika.sudokuGame

import org.nika.sudokuGame.time.GameTime
import org.junit.jupiter.api.Test
import org.nika.sudokuGame.GameStats
import org.nika.sudokuGame.SudokuGame
import org.nika.sudokuGame.findCell
import org.nika.sudokuGame.generate2X2Table
import org.nika.sudokuTable.SudokuTable
import org.nika.sudokuTable.cells.NO_VALUE
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuGameTest {
    @Test
    fun stateOfAGameShouldReactCorrectlyToFillingACell() {
        val game = newGame(generate2X2Table())
        assertTrue(game.internalState().tableState.tableIsEmpty)

        var gameState = game.fillCell(1, 1, 1, "00:00:30")
        assertFalse(gameState.tableState.tableIsEmpty)
        assertEquals("00:00:30", gameState.playedTime)
        assertEquals(1, gameState.numberOfTurns)
        assertFalse(gameState.gameIsWon)

        gameState = game.fillCell(2, 1, 2, "00:00:45")
        assertEquals("00:00:45", gameState.playedTime)
        assertEquals(2, gameState.numberOfTurns)

        gameState = game.fillCell(3, 2, 1, "00:01:05")
        assertEquals("00:01:05", gameState.playedTime)
        assertEquals(3, gameState.numberOfTurns)

        gameState = game.fillCell(4, 2, 2, "00:01:21")
        assertEquals("00:01:21", gameState.playedTime)
        assertEquals(4, gameState.numberOfTurns)
        assertTrue(gameState.gameIsWon)
    }

    @Test
    fun restartingAGameResetsState() {
        val game = newGame(generate2X2Table())
        assertTrue(game.internalState().tableState.tableIsEmpty)

        game.fillCell(1, 1, 1, "00:00:30")
        var gameState = game.fillCell(2, 1, 2, "00:00:45")
        assertEquals("00:00:45", gameState.playedTime)
        assertEquals(2, gameState.numberOfTurns)
        assertFalse(gameState.tableState.tableIsEmpty)

        gameState = game.restart()
        assertEquals("00:00:00", gameState.playedTime)
        assertEquals(0, gameState.numberOfTurns)
        assertTrue(gameState.tableState.tableIsEmpty)
    }

    @Test
    fun correctlyEmptiesACell() {
        val game = newGame(generate2X2Table())

        var gameState = game.fillCell(1, 1, 1, "00:00:01")
        assertEquals(1, gameState.tableState.findCell(1, 1).value)

        gameState = game.emptyCell(1, 1, "00:00:02")
        assertEquals("00:00:02", gameState.playedTime)
        assertEquals(2, gameState.numberOfTurns)
        assertEquals(NO_VALUE, gameState.tableState.findCell(1, 1).value)
    }

    private fun newGame(sudokuTable: SudokuTable): SudokuGame =
        SudokuGame(
            GameStats(GameTime(0, 0, 0), 0),
            sudokuTable
        )
}