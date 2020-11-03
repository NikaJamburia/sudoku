package org.nika.sudokuWeb.http.response

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SerializationFormat.JSON
import org.nika.sudokuInteraction.result.GameSaved
import org.nika.sudokuInteraction.state.SavedSudokuGameState
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class GameSavingResponseEntityTest {
    @Test
    fun createsOkResponseFromSuccessfulResult() {
        val result = GameSaved(SavedSudokuGameState(JSON, "Serialized game"))
        val response = GameSavingResponseEntity(result).response()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body, result)
    }
}