package org.nika.sudokuGame.generation.parameters

enum class TableGenerationAlgorithm {
    MOCKED, RANDOM, BACKTRACKING, RANDOM_EMPTY
}

data class SudokuTableGenerationParameters (
    val tableGenerationAlgorithm: TableGenerationAlgorithm,
    val xCells: Int,
    val yCells: Int,
    val defaultClosedCells: Int
)