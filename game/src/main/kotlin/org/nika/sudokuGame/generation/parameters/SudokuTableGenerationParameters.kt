package org.nika.sudokuGame.generation.parameters

enum class TableGenerationAlgorithm {
    MOCKED, RANDOM, BACKTRACKING, EMPTY_TABLE
}

data class SudokuTableGenerationParameters (
    val tableGenerationAlgorithm: TableGenerationAlgorithm,
    val xCells: Int,
    val yCells: Int,
    val defaultClosedCells: Int
)