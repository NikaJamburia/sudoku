package org.nika.sudokuGame.saveload.serialization

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.TableState

import kotlin.test.assertEquals

class JacksonSerializedSudokuGameStateTest {
    private val expectedString = """{"playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":1,"coordinateX":1,"coordinateY":1,"cellIsOpen":true},{"value":2,"coordinateX":1,"coordinateY":2,"cellIsOpen":false}],"conflicts":[],"tableIsFull":false,"tableIsEmpty":false}}"""

    @Test
    fun turnsGivenGameStateToValidJsonString() {
        val tableState = TableState(
            listOf(CellState(1, 1, 1, true), CellState(2, 1, 2, false)),
            listOf(), false, false)
        val gameState = GameState("00:00:30", 3, false, tableState)

        assertEquals(expectedString, JacksonSerializedSudokuGameState(gameState).asString())

    }
}