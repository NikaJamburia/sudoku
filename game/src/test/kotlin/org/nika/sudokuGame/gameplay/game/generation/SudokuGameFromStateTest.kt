package org.nika.sudokuGame.gameplay.game.generation

import org.nika.sudokuGame.gameplay.game.GameState
import org.junit.jupiter.api.Test
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.cells.NO_VALUE
import org.nika.sudokuGame.table.state.CellState
import org.nika.sudokuGame.table.state.TableState
import kotlin.test.assertEquals

class SudokuGameFromStateTest {
    @Test
    fun correctlyGeneratesGameFromState() {
        val initialState = GameState(
            "00:01:40",
            5,
            false,
            TableState(listOf(CellState(NO_VALUE, Coordinates(1, 1), true)), listOf(), false, false)
        )

        val generatedState = SudokuGameFromState(initialState).generate().internalState()

        assertEquals(initialState.gameIsWon, generatedState.gameIsWon)
        assertEquals(initialState.playedTime, generatedState.playedTime)
        assertEquals(initialState.numberOfTurns, generatedState.numberOfTurns)
        assertEquals(initialState.tableState.cells.size, generatedState.tableState.cells.size)
    }
}