package org.nika.sudokuService

import columnSize
import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuInteraction.request.StartNewGameRequest
import org.nika.sudokuService.spring.service.SudokuGameSpringService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import rowSize
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(classes = [SudokuServiceApplication::class])
@TestPropertySource("classpath:table-generation-mocked-test.properties")
class SudokuGameSpringServiceMockedIntegrationTest(
    @Autowired private val gameSpringService: SudokuGameSpringService
) {
    @Test
    fun correctlyUsesPropertiesToStartNewGame() {
        val result = gameSpringService.startNewGame(StartNewGameRequest(SudokuDifficulty.NO_DIFFICULTY))

        assertTrue(result.isSuccessful)
        assertEquals(9, rowSize(result.content.tableState))
        assertEquals(9, columnSize(result.content.tableState))
    }



}