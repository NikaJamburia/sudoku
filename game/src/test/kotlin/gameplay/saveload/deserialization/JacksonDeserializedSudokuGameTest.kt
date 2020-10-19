package gameplay.saveload.deserialization

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class JacksonDeserializedSudokuGameTest {
    @Test
    fun correctlyCreatesGameStateFromJson() {
        val json = """{"playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":1,"coordinates":{"x":1,"y":1},"cellIsOpen":true},{"value":2,"coordinates":{"x":1,"y":2},"cellIsOpen":false}],"conflicts":[],"tableIsEmpty":false,"tableIsFull":false}}"""
        val state = JacksonDeserializedSudokuGame(json).state()

        assertEquals("00:00:30", state.playedTime)
        assertEquals(3, state.numberOfTurns)
        assertFalse(state.gameIsWon)

    }
}