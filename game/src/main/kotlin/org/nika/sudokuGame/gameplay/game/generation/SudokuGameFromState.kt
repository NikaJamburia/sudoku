package org.nika.sudokuGame.gameplay.game.generation

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.game.GameStats
import org.nika.sudokuGame.gameplay.game.SudokuGame
import org.nika.sudokuGame.gameplay.game.time.GameTimeFromString
import org.nika.sudokuGame.table.generation.SudokuTableFromState

class SudokuGameFromState(
    private val state: GameState
): GeneratedSudokuGame {
    override fun generate(): SudokuGame =
        SudokuGame(
            GameStats(GameTimeFromString(state.playedTime),state.numberOfTurns),
            SudokuTableFromState(state.tableState).generate()
        )
}
