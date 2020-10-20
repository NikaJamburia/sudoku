package saveload.json

import gameplay.saveload.SavedSudokuGame
import gameplay.saveload.serialization.SerializationFormat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class JsonGameLoaderTest {

    @Test
    fun supportsJsonFormat() {
        assertEquals(SerializationFormat.JSON, JsonGameLoader().supportedFormat())
    }

    @Test
    fun loadsGivenSavedGame() {
        val savedJson = """{"playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":0,"coordinates":{"x":1,"y":1},"cellIsOpen":true},{"value":2,"coordinates":{"x":1,"y":2},"cellIsOpen":false}],"conflicts":[],"tableIsFull":false,"tableIsEmpty":false}}"""

        val loaded = JsonGameLoader().load(SavedSudokuGame(SerializationFormat.JSON, savedJson))

        assertEquals("00:00:30", loaded.playedTime)
        assertEquals(3, loaded.numberOfTurns)
        assertFalse(loaded.gameIsWon)
        assertEquals(2, loaded.tableState.cells.size)
        assertEquals(0, loaded.tableState.conflicts.size)
        assertFalse(loaded.tableState.tableIsEmpty)
    }

}