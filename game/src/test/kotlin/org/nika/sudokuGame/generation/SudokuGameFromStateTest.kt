package org.nika.sudokuGame.generation

import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.TableState
import org.nika.sudokuTable.cells.NO_VALUE

import kotlin.test.assertEquals

class SudokuGameFromStateTest {
    @Test
    fun correctlyGeneratesGameFromState() {
        val initialState = GameState(
            SudokuDifficulty.EASY,
            "00:01:40",
            5,
            false,
            TableState(listOf(CellState(NO_VALUE,1, 1, true)), listOf(), false, false)
        )

        val generatedState = SudokuGameFromState(initialState).generate().internalState()

        assertEquals(initialState.gameIsWon, generatedState.gameIsWon)
        assertEquals(initialState.playedTime, generatedState.playedTime)
        assertEquals(initialState.numberOfTurns, generatedState.numberOfTurns)
        assertEquals(initialState.tableState.cells.size, generatedState.tableState.cells.size)
    }
}