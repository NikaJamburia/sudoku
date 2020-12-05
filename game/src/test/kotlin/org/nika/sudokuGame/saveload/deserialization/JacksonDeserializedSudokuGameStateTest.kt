package org.nika.sudokuGame.saveload.deserialization

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class JacksonDeserializedSudokuGameStateTest {
    @Test
    fun correctlyCreatesGameStateFromJson() {
        val json = """{"difficulty":"EASY","playedTime":"00:00:30","numberOfTurns":3,"gameIsWon":false,"tableState":{"cells":[{"value":1,"coordinateX":1,"coordinateY":1,"cellIsOpen":true},{"value":2,"coordinateX":1,"coordinateY":2,"cellIsOpen":false}],"conflicts":[{"conflictedCells":[{"value":3,"coordinateX":4,"coordinateY":3,"cellIsOpen":true},{"value":3,"coordinateX":5,"coordinateY":3,"cellIsOpen":true},{"value":3,"coordinateX":8,"coordinateY":3,"cellIsOpen":false}]},{"conflictedCells":[{"value":5,"coordinateX":8,"coordinateY":5,"cellIsOpen":true},{"value":5,"coordinateX":9,"coordinateY":5,"cellIsOpen":false}]}],"tableIsEmpty":false,"tableIsFull":false}}"""
        val state = JacksonDeserializedSudokuGameState(json).gameState()

        assertEquals(SudokuDifficulty.EASY, state.difficulty)
        assertEquals("00:00:30", state.playedTime)
        assertEquals(3, state.numberOfTurns)
        assertEquals(2, state.tableState.conflicts.size)
        assertFalse(state.gameIsWon)

    }
}