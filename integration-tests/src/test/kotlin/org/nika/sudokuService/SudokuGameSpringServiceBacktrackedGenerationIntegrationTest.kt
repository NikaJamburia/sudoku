package org.nika.sudokuService

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuInteraction.enums.SudokuDifficulty.*
import org.nika.sudokuInteraction.request.StartNewGameRequest
import org.nika.sudokuInteraction.result.SudokuInteractionResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(classes = [SudokuServiceApplication::class])
@TestPropertySource("classpath:table-generation-backtracked-test.properties")
class SudokuGameSpringServiceBacktrackedGenerationIntegrationTest(
    @Autowired private val gameService: SudokuGameService,
    @Autowired private val tableDifficultyConfiguration: Map<SudokuDifficulty, Int>
) {
    @Test
    fun correctlyUsesPropertiesToStartEasyGame() {
        val result = gameService.startNewGame(StartNewGameRequest(EASY))

        assertTrue(result.isSuccessful)
        assertEquals(81, result.content.tableState.cells.size)
        assertEquals(tableDifficultyConfiguration[EASY], result.numberOfClosedCells())
    }

    @Test
    fun correctlyUsesPropertiesToStartMediumGame() {
        val result = gameService.startNewGame(StartNewGameRequest(MEDIUM))

        assertTrue(result.isSuccessful)
        assertEquals(81, result.content.tableState.cells.size)
        assertEquals(tableDifficultyConfiguration[MEDIUM], result.numberOfClosedCells())
    }

    @Test
    fun correctlyUsesPropertiesToStarthardGame() {
        val result = gameService.startNewGame(StartNewGameRequest(HARD))

        assertTrue(result.isSuccessful)
        assertEquals(81, result.content.tableState.cells.size)
        assertEquals(tableDifficultyConfiguration[HARD], result.numberOfClosedCells())
    }

    private fun SudokuInteractionResult.numberOfClosedCells() =
        this.content.tableState.cells.count { !it.cellIsOpen }
}