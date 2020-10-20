package saveload.json

import gameplay.game.GameState
import gameplay.saveload.serialization.SerializationFormat
import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.NO_VALUE
import table.state.CellState
import table.state.TableState
import kotlin.test.assertEquals

class JsonGameSaverTest {

    @Test
    fun supportsJsonFormat() {
        assertEquals(SerializationFormat.JSON, JsonGameSaver().supportedFormat())
    }

    @Test
    fun savesGameAsJson() {
        val expectedJson = """{"playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":0,"coordinates":{"x":1,"y":1},"cellIsOpen":true},{"value":2,"coordinates":{"x":1,"y":2},"cellIsOpen":false}],"conflicts":[],"tableIsFull":false,"tableIsEmpty":false}}"""

        val tableState = TableState(
            listOf(CellState(NO_VALUE, Coordinates(1, 1), true), CellState(2, Coordinates(1, 2), false)),
            listOf(), false, false)
        val gameToSave = GameState("00:00:30", 3, false, tableState)

        val savedGame = JsonGameSaver().save(gameToSave)

        assertEquals(SerializationFormat.JSON, savedGame.serializationFormat)
        assertEquals(expectedJson, savedGame.savedContent)
    }
}