package org.nika.sudokuGame.gameplay.game.generation

import org.nika.sudokuGame.gameplay.game.GameStats
import org.nika.sudokuGame.gameplay.game.SudokuGame
import org.nika.sudokuGame.gameplay.game.time.GameTimeFromString
import org.nika.sudokuGame.table.generation.SudokuTableFromState
import org.nika.sudokuInteraction.state.GameState

class SudokuGameFromState(
    private val state: GameState
): GeneratedSudokuGame {
    override fun generate(): SudokuGame =
        SudokuGame(
            GameStats(GameTimeFromString(state.playedTime),state.numberOfTurns),
            SudokuTableFromState(state.tableState).generate()
        )
}
