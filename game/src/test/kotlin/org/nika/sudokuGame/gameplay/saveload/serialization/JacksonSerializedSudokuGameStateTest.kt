package org.nika.sudokuGame.gameplay.saveload.serialization

import org.nika.sudokuGame.gameplay.game.GameState
import org.junit.jupiter.api.Test
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.state.CellState
import org.nika.sudokuGame.table.state.TableState
import kotlin.test.assertEquals

class JacksonSerializedSudokuGameStateTest {
    private val expectedString = """{"playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":1,"coordinates":{"x":1,"y":1},"cellIsOpen":true},{"value":2,"coordinates":{"x":1,"y":2},"cellIsOpen":false}],"conflicts":[],"tableIsFull":false,"tableIsEmpty":false}}"""

    @Test
    fun turnsGivenGameStateToValidJsonString() {
        val tableState = TableState(
            listOf(CellState(1, Coordinates(1, 1), true), CellState(2, Coordinates(1, 2), false)),
            listOf(), false, false)
        val gameState = GameState("00:00:30", 3, false, tableState)

        assertEquals(expectedString, JacksonSerializedSudokuGameState(gameState).asString())

    }
}