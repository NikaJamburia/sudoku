package gameplay.saveload.serialization

import gameplay.game.GameState
import org.junit.jupiter.api.Test
import table.Coordinates
import table.state.CellState
import table.state.TableState
import kotlin.test.assertEquals

class JacksonSerializedSudokuGameTest {
    private val expectedString = """{"gameId":"1","playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":1,"coordinates":{"x":1,"y":1},"cellIsOpen":true},{"value":2,"coordinates":{"x":1,"y":2},"cellIsOpen":false}],"conflicts":[],"tableIsFull":false,"tableIsEmpty":false}}"""

    @Test
    fun turnsGivenGameStateToValidJsonString() {
        val tableState = TableState(
            listOf(CellState(1, Coordinates(1, 1), true), CellState(2, Coordinates(1, 2), false)),
            listOf(), false, false)
        val gameState = GameState("1", "00:00:30", 3, false, tableState)

        assertEquals(expectedString, JacksonSerializedSudokuGame(gameState).asString())

    }
}