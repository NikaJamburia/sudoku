package org.nika.sudokuGame.generation

import org.nika.sudokuGame.GameStats
import org.nika.sudokuGame.SudokuGame
import org.nika.sudokuGame.time.GameTimeFromString
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuTable.generation.SudokuTableFromState

class SudokuGameFromState(
    private val state: GameState
): GeneratedSudokuGame {
    override fun generate(): SudokuGame =
        SudokuGame(
            GameStats(state.difficulty, GameTimeFromString(state.playedTime), state.numberOfTurns),
            SudokuTableFromState(state.tableState).generate()
        )
}
