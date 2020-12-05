package org.nika.sudokuInteraction.state

import org.nika.sudokuInteraction.enums.SudokuDifficulty

data class GameState (
    val difficulty: SudokuDifficulty,
    val playedTime: String,
    val numberOfTurns: Int,
    val gameIsWon: Boolean,
    val tableState: TableState
)