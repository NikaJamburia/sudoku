package org.nika.sudokuGame.gameplay.game.generation

import org.junit.jupiter.api.Test
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.cells.NO_VALUE
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.TableState

import kotlin.test.assertEquals

class SudokuGameFromStateTest {
    @Test
    fun correctlyGeneratesGameFromState() {
        val initialState = GameState(
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