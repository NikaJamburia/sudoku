package org.nika.sudokuGame.gameplay.saveload.deserialization

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class JacksonDeserializedSudokuGameStateTest {
    @Test
    fun correctlyCreatesGameStateFromJson() {
        val json = """{"playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":1,"coordinateX":1,"coordinateY":1,"cellIsOpen":true},{"value":2,"coordinateX":1,"coordinateY":2,"cellIsOpen":false}],"conflicts":[],"tableIsEmpty":false,"tableIsFull":false}}"""
        val state = JacksonDeserializedSudokuGameState(json).state()

        assertEquals("00:00:30", state.playedTime)
        assertEquals(3, state.numberOfTurns)
        assertFalse(state.gameIsWon)

    }
}