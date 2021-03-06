package org.nika.sudokuService.spring.service

import org.junit.jupiter.api.Test
import org.nika.sudokuGame.generation.parameters.SudokuDifficultyParameters
import org.nika.sudokuGame.generation.parameters.SudokuTableGenerationParameters
import org.nika.sudokuGame.generation.parameters.TableGenerationAlgorithm
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuInteraction.request.EmptyCellRequest
import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.request.StartNewGameRequest
import org.nika.sudokuInteraction.request.SudokuInteractionRequest
import org.nika.sudokuInteraction.result.CellEmptied
import org.nika.sudokuInteraction.result.GameStarted
import org.nika.sudokuInteraction.result.GameWon
import org.nika.sudokuService.game4X4With1Empty
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.nika.sudokuInteraction.result.Error
import org.nika.sudokuService.findCell

class SudokuGameSpringServiceTest {

    @Test
    fun correctlyFillsCells() {
        val gameState = game4X4With1Empty("00:00:30", 1)
        val request = FillCellRequest(5, 1, 1, gameState, "00:00:45")

        val result = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty).fillCell(request)

        assertTrue(result.isSuccessful)
        assertEquals("Successfully filled cell" , result.message)
        assertEquals("00:00:45" , result.content.playedTime)
        assertEquals(2 , result.content.numberOfTurns)
        assertEquals(5 , result.content.tableState.findCell(1, 1).value)
    }

    @Test
    fun tellsUsWhenGameIsWon() {
        val gameState = game4X4With1Empty("00:00:30", 1)

        val winningRequest = FillCellRequest(4, 2, 2, gameState, "00:00:50")
        val winningResult = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty).fillCell(winningRequest)

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
        val result1 = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty).fillCell(request1)
        assertFalse(result1.isSuccessful)
        assertTrue(result1 is Error)
        assertEquals("Value of closed cell can not be changed", result1.message)

        // try to fill not existing cell
        val request2 = FillCellRequest(4, 1, 5, gameState, "00:00:32")
        val result2 = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty).fillCell(request2)
        assertFalse(result2.isSuccessful)
        assertTrue(result2 is Error)
        assertEquals("Cell not found", result2.message)

        // try to fill invalid value
        val request3 = FillCellRequest(-1, 1, 1, gameState, "00:00:32")
        val result3 = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty).fillCell(request3)
        assertFalse(result3.isSuccessful)
        assertTrue(result3 is Error)
        assertEquals("Value of cell must be between 0 and 9!", result3.message)
    }

    @Test
    fun correctlyRestartsTheGame() {
        val game = game4X4With1Empty("00:31:15", 21)
        val result = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty).restart(SudokuInteractionRequest(game, "00:32:16"))

        assertTrue(result.isSuccessful)
        assertEquals("New game started", result.message)
        assertEquals("00:00:00", result.content.playedTime)
        assertEquals(0, result.content.numberOfTurns)
        assertTrue(result.content.tableState.tableIsEmpty)
    }

    @Test
    fun startsNewGame() {
        val service = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty)
        val result = service.startNewGame(StartNewGameRequest(SudokuDifficulty.NO_DIFFICULTY))

        assertTrue(result is GameStarted)
        assertEquals("00:00:00", result.content.playedTime)
        assertEquals(0, result.content.numberOfTurns)
    }

    @Test
    fun correctlyStartsBackTrackedGameWithDifficulty() {
        val generationParams = SudokuTableGenerationParameters(
            TableGenerationAlgorithm.BACKTRACKING, 9, 9, 50
        )

        val difficultyParams = mapOf(Pair(SudokuDifficulty.MEDIUM, 60))


        val service = SudokuGameSpringService(generationParams, difficultyParams)
        val result = service.startNewGame(StartNewGameRequest(SudokuDifficulty.MEDIUM))

        assertTrue(result is GameStarted)
        assertEquals("00:00:00", result.content.playedTime)
        assertEquals(0, result.content.numberOfTurns)
        assertEquals(SudokuDifficulty.MEDIUM, result.content.difficulty)
        assertEquals(60, result.content.tableState.cells.count { !it.cellIsOpen })
    }

    @Test
    fun emptiesCell() {
        val service = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty)
        val gameState = game4X4With1Empty("00:00:30", 0)

        val fillCellRequest = FillCellRequest(5, 1, 1, gameState, "00:00:15")
        val filCellResult = service.fillCell(fillCellRequest)

        assertEquals(5, filCellResult.content.tableState.findCell(1, 1).value)

        val emptyCellRequest = EmptyCellRequest(1, 1, filCellResult.content, "00:00:45")
        val emptyCellResult = service.emptyCell(emptyCellRequest)

        assertTrue(emptyCellResult is CellEmptied)
        assertEquals("Cell emptied", emptyCellResult.message)
        assertEquals("00:00:45", emptyCellResult.content.playedTime)
        assertEquals(2, emptyCellResult.content.numberOfTurns)
        assertEquals(0, emptyCellResult.content.tableState.findCell(1, 1).value)
    }

    @Test
    fun correctlyHandlesErrorsOnEmptyCell() {
        val service = SudokuGameSpringService(defaultTableGeneration, defaultDifficulty)
        val gameState = game4X4With1Empty("00:00:30", 0)

        val badCoordinatesRequest = EmptyCellRequest(5, 5, gameState, "00:00:45")
        val badCoordinatesResult = service.emptyCell(badCoordinatesRequest)

        assertTrue(badCoordinatesResult is Error)
        assertEquals("Cell not found", badCoordinatesResult.message)

        val closedCellRequest = EmptyCellRequest(1, 2, gameState, "00:00:45")
        val closedCellResult = service.emptyCell(closedCellRequest)

        assertTrue(closedCellResult is Error)
        assertEquals("Value of closed cell can not be changed", closedCellResult.message)
    }

    private val defaultTableGeneration =
        SudokuTableGenerationParameters(
            TableGenerationAlgorithm.MOCKED,
            0,
            0,
            0
        )

    private val defaultDifficulty = mapOf<SudokuDifficulty, Int>()

}