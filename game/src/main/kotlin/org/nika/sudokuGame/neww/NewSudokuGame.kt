package org.nika.sudokuGame.neww


import org.nika.sudokuGame.GameStats
import org.nika.sudokuGame.SudokuGame
import org.nika.sudokuGame.time.GameTime
import org.nika.sudokuTable.generation.MockGeneratedSudokuTable
import org.nika.sudokuTable.generation.RandomBasedNewSudokuTable

class NewSudokuGame (
    private val parameters: SudokuTableGenerationParameters
) {
    fun start(): SudokuGame {
        val table = when(parameters.tableGenerationAlgorithm) {
            TableGenerationAlgorithm.MOCKED -> MockGeneratedSudokuTable().generate()
            TableGenerationAlgorithm.RANDOM -> RandomBasedNewSudokuTable(parameters.xCells, parameters.yCells, parameters.closedCells).generate()
        }
        return SudokuGame(GameStats(GameTime(0, 0, 0), 0), table)
    }
}