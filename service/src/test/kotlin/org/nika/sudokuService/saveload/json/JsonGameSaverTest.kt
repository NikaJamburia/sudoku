package org.nika.sudokuService.saveload.json

import org.junit.jupiter.api.Test

import org.nika.sudokuInteraction.enums.SerializationFormat.JSON
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.TableState
import kotlin.test.assertEquals

class JsonGameSaverTest {

    @Test
    fun supportsJsonFormat() {
        assertEquals(JSON, JsonGameSaver().supportedFormat())
    }

    @Test
    fun savesGameAsJson() {
        val expectedJson = """{"playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":0,"coordinateX":1,"coordinateY":1,"cellIsOpen":true},{"value":2,"coordinateX":1,"coordinateY":2,"cellIsOpen":false}],"conflicts":[],"tableIsFull":false,"tableIsEmpty":false}}"""

        val tableState = TableState(
            listOf(CellState(0, 1, 1, true), CellState(2, 1, 2, false)),
            listOf(), false, false)
        val gameToSave = GameState("00:00:30", 3, false, tableState)

        val savedGame = JsonGameSaver().save(gameToSave)

        assertEquals(JSON, savedGame.serializationFormat)
        assertEquals(expectedJson, savedGame.content)
    }
}