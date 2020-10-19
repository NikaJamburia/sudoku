package gameplay.game.generation

import gameplay.game.GameState
import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.NO_VALUE
import table.state.CellState
import table.state.TableState
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