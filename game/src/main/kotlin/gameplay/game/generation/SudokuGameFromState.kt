package gameplay.game.generation

import gameplay.game.GameState
import gameplay.game.GameStats
import gameplay.game.time.GameTime
import gameplay.game.SudokuGame
import gameplay.game.time.GameTimeFromString
import table.generation.SudokuTableFromState

class SudokuGameFromState(
    private val state: GameState
): GeneratedSudokuGame {
    override fun generate(): SudokuGame =
        SudokuGame(
            GameStats(GameTimeFromString(state.playedTime),state.numberOfTurns),
            SudokuTableFromState(state.tableState).generate()
        )
}
