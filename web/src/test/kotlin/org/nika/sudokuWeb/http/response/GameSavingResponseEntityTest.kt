package org.nika.sudokuWeb.http.response

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SerializationFormat.JSON
import org.nika.sudokuInteraction.result.GameSaved
import org.nika.sudokuInteraction.result.SavingError
import org.nika.sudokuInteraction.state.SavedSudokuGameState
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class GameSavingResponseEntityTest {
    @Test
    fun createsOkResponseFromSuccessfulResult() {
        val result = GameSaved(SavedSudokuGameState(JSON, "Serialized game"))
        val response = GameSavingResponseEntity(result).respond()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body, result)
    }

    @Test
    fun createsBadRequestResponseFromUnsuccessfulResult() {
        val result = SavingError("save failed")
        val response = GameSavingResponseEntity(result).respond()

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertEquals(response.body, result)
    }
}