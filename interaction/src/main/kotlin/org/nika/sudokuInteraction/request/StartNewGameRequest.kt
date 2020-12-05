package org.nika.sudokuInteraction.request

import org.nika.sudokuInteraction.enums.SudokuDifficulty

data class StartNewGameRequest (
    val difficulty: SudokuDifficulty
)