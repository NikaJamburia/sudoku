package org.nika.sudokuGame.generation

enum class TableGenerationAlgorithm {
    MOCKED, RANDOM, BACKTRACKING
}

data class SudokuTableGenerationParameters (
    val tableGenerationAlgorithm: TableGenerationAlgorithm,
    val xCells: Int,
    val yCells: Int,
    val closedCells: Int
)