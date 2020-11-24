package org.nika.sudokuService

import columnSize
import org.junit.jupiter.api.Test
import org.nika.sudokuService.service.SudokuGameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import rowSize
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(classes = [SudokuServiceApplication::class])
@TestPropertySource("classpath:table-generation-mocked-test.properties")
class SudokuGameServiceMockedIntegrationTest(
    @Autowired private val gameService: SudokuGameService
) {
    @Test
    fun correctlyUsesPropertiesToStartNewGame() {
        val result = gameService.startNewGame()

        assertTrue(result.isSuccessful)
        assertEquals(9, rowSize(result.content.tableState))
        assertEquals(9, columnSize(result.content.tableState))
    }



}