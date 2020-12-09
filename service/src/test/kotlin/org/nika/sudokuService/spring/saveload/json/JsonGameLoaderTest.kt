package org.nika.sudokuService.spring.saveload.json

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuInteraction.state.SavedSudokuGameState
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class JsonGameLoaderTest {

    @Test
    fun supportsJsonFormat() {
        assertEquals(SerializationFormat.JSON, JsonGameLoader()
            .supportedFormat())
    }

    @Test
    fun loadsGivenSavedGame() {
        val savedJson = """{"difficulty":"NO_DIFFICULTY","playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":0,"coordinateX":1,"coordinateY":1,"cellIsOpen":true},{"value":2,"coordinateX":1,"coordinateY":2,"cellIsOpen":false}],"conflicts":[],"tableIsFull":false,"tableIsEmpty":false}}"""

        val loaded = JsonGameLoader()
            .load(SavedSudokuGameState(SerializationFormat.JSON, savedJson))

        assertEquals("00:00:30", loaded.playedTime)
        assertEquals(3, loaded.numberOfTurns)
        assertEquals(SudokuDifficulty.NO_DIFFICULTY, loaded.difficulty)
        assertFalse(loaded.gameIsWon)
        assertEquals(2, loaded.tableState.cells.size)
        assertEquals(0, loaded.tableState.conflicts.size)
        assertFalse(loaded.tableState.tableIsEmpty)
    }

}