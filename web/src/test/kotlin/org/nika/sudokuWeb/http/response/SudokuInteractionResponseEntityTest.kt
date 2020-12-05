package org.nika.sudokuWeb.http.response

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuInteraction.result.GameStarted
import org.nika.sudokuInteraction.result.NoGameError
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.TableState
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class SudokuInteractionResponseEntityTest {
    @Test
    fun createsOkResponseFromSuccessfulResult() {
        val result = GameStarted(GameState(SudokuDifficulty.HARD, "00:00:00", 1, false, TableState(listOf(), listOf(), false, false)))
        val response = SudokuInteractionResponseEntity(result).respond()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body, result)
    }

    @Test
    fun createsBadRequestResponseFromUnsuccessfulResult() {
        val result = NoGameError("error")
        val response = SudokuInteractionResponseEntity(result).respond()

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertEquals(response.body, result)
    }
}