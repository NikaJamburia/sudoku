package org.nika.sudokuGame.generation

enum class TableGenerationAlgorithm {
    MOCKED, RANDOM, BACKTRACKING, EMPTY
}

data class SudokuTableGenerationParameters (
    val tableGenerationAlgorithm: TableGenerationAlgorithm,
    val xCells: Int,
    val yCells: Int,
    val closedCells: Int
)