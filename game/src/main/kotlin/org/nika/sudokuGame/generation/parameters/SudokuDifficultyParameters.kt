package org.nika.sudokuGame.generation.parameters

import org.nika.sudokuInteraction.enums.SudokuDifficulty

data class SudokuDifficultyParameters (
    val chosenDifficulty: SudokuDifficulty,
    val numberOfClosedCells: Int
)