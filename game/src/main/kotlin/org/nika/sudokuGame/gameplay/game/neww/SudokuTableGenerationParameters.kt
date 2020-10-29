package org.nika.sudokuGame.gameplay.game.neww

enum class TableGenerationAlgorithm {
    MOCKED, RANDOM
}

data class SudokuTableGenerationParameters (
    val tableGenerationAlgorithm: TableGenerationAlgorithm,
    val xCells: Int,
    val yCells: Int,
    val closedCells: Int
)