package org.nika.sudokuGame.generation


import org.nika.sudokuGame.GameStats
import org.nika.sudokuGame.SudokuGame
import org.nika.sudokuGame.time.GameTime
import org.nika.sudokuTable.generation.MockGeneratedSudokuTable
import org.nika.sudokuTable.generation.RandomBasedSudokuTable
import org.nika.sudokuTable.generation.backtracking.SudokuTableFromBacktracking

class NewSudokuGame (
    private val parameters: SudokuTableGenerationParameters
): GeneratedSudokuGame {

    override fun generate(): SudokuGame {
        val table = when(parameters.tableGenerationAlgorithm) {
            TableGenerationAlgorithm.MOCKED -> MockGeneratedSudokuTable().generate()
            TableGenerationAlgorithm.RANDOM -> RandomBasedSudokuTable(parameters.xCells, parameters.yCells, parameters.closedCells).generate()
            TableGenerationAlgorithm.BACKTRACKING -> SudokuTableFromBacktracking().generate()
        }
        return SudokuGame(GameStats(GameTime(0, 0, 0), 0), table)
    }
}