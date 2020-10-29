package org.nika.sudokuService.integration

import org.junit.jupiter.api.Test
import org.nika.sudokuGame.table.generation.MockGeneratedSudokuTable
import org.nika.sudokuService.SudokuServiceApplication
import org.nika.sudokuService.columnSize
import org.nika.sudokuService.countClosedCells
import org.nika.sudokuService.rowSize
import org.nika.sudokuService.service.SudokuGameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@SpringBootTest(classes = [SudokuServiceApplication::class])
@TestPropertySource("classpath:table-generation-random-test.properties")
class SudokuGameServiceRandomIntegrationTest(
    @Autowired private val gameService: SudokuGameService
) {
    @Test
    fun correctlyUsesPropertiesToStartNewGame() {
        val result = gameService.startNewGame()

        assertTrue(result.isSuccessful)
        assertNotEquals(MockGeneratedSudokuTable().generate().internalState(), result.content.tableState)
        assertEquals(20, rowSize(result.content.tableState))
        assertEquals(20, columnSize(result.content.tableState))
        assertEquals(25, countClosedCells(result.content.tableState))
    }
}