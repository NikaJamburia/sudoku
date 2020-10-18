package gameplay.game

import gameplay.game.time.GameTime
import gameplay.saveload.save.GameSaver
import gameplay.saveload.serialization.SerializedSudokuGame
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import table.Coordinates
import table.SudokuTable
import table.cells.NO_VALUE
import table.cells.OpenCell
import table.cells.collection.Boxes
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuGameTest {
    @Test
    fun stateOfAGameShouldReactCorrectlyToFillingACell() {
        val game = newGame(generate2X2Table())
        assertTrue(game.internalState().tableState.tableIsEmpty)

        var gameState = game.fillCell(1, Coordinates(1, 1), "00:00:30")
        assertFalse(gameState.tableState.tableIsEmpty)
        assertEquals("00:00:30", gameState.playedTime)
        assertEquals(1, gameState.numberOfTurns)
        assertFalse(gameState.gameIsWon)

        gameState = game.fillCell(2, Coordinates(1, 2), "00:00:45")
        assertEquals("00:00:45", gameState.playedTime)
        assertEquals(2, gameState.numberOfTurns)

        gameState = game.fillCell(3, Coordinates(2, 1), "00:01:05")
        assertEquals("00:01:05", gameState.playedTime)
        assertEquals(3, gameState.numberOfTurns)

        gameState = game.fillCell(4, Coordinates(2, 2), "00:01:21")
        assertEquals("00:01:21", gameState.playedTime)
        assertEquals(4, gameState.numberOfTurns)
        assertTrue(gameState.gameIsWon)
    }

    @Test
    fun restartingAGameResetsState() {
        val game = newGame(generate2X2Table())
        assertTrue(game.internalState().tableState.tableIsEmpty)

        game.fillCell(1, Coordinates(1, 1), "00:00:30")
        var gameState = game.fillCell(2, Coordinates(1, 2), "00:00:45")
        assertEquals("00:00:45", gameState.playedTime)
        assertEquals(2, gameState.numberOfTurns)
        assertFalse(gameState.tableState.tableIsEmpty)

        gameState = game.restart()
        assertEquals("00:00:00", gameState.playedTime)
        assertEquals(0, gameState.numberOfTurns)
        assertTrue(gameState.tableState.tableIsEmpty)
    }

    @Test
    fun savingWorksCorrectly() {
        val game = newGame(generate2X2Table())
        game.fillCell(1, Coordinates(1, 1), "00:00:30")

        val gameSaverMock = mockk<GameSaver>() {
            every { save(game.internalState()) } returns mockk<SerializedSudokuGame>() {
                every { asString() } returns "serialized game"
            }
        }

        val savedGame = game.save(gameSaverMock)
        assertEquals("serialized game", savedGame.asString())
    }

    fun generate2X2Table(): SudokuTable {
        val cells= listOf(
            OpenCell(NO_VALUE, Coordinates(1, 1)),
            OpenCell(NO_VALUE, Coordinates(1, 2)),
            OpenCell(NO_VALUE, Coordinates(2, 1)),
            OpenCell(NO_VALUE, Coordinates(2, 2))
        )
        return SudokuTable(
            Boxes(cells)
        )
    }

    fun newGame(sudokuTable: SudokuTable): SudokuGame =
        SudokuGame(
            "1",
            GameStats(GameTime(0, 0, 0), 0),
            sudokuTable)
}