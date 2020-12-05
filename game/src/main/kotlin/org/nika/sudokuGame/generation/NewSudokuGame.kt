package org.nika.sudokuGame.generation


import org.nika.sudokuGame.GameStats
import org.nika.sudokuGame.SudokuGame
import org.nika.sudokuGame.generation.parameters.SudokuDifficultyParameters
import org.nika.sudokuGame.generation.parameters.SudokuTableGenerationParameters
import org.nika.sudokuGame.generation.parameters.TableGenerationAlgorithm
import org.nika.sudokuGame.time.GameTime
import org.nika.sudokuTable.generation.CleanSudokuTable
import org.nika.sudokuTable.generation.MockGeneratedSudokuTable
import org.nika.sudokuTable.generation.RandomBasedSudokuTable
import org.nika.sudokuTable.generation.backtracking.BacktrackedSudokuTable

class NewSudokuGame (
    private val generationParameters: SudokuTableGenerationParameters,
    private val difficultyParameters: SudokuDifficultyParameters
    ): GeneratedSudokuGame {

    override fun generate(): SudokuGame {
        val table = when(generationParameters.tableGenerationAlgorithm) {
            TableGenerationAlgorithm.MOCKED -> MockGeneratedSudokuTable().generate()
            TableGenerationAlgorithm.RANDOM -> RandomBasedSudokuTable(generationParameters.xCells, generationParameters.yCells, generationParameters.defaultClosedCells).generate()
            TableGenerationAlgorithm.BACKTRACKING -> BacktrackedSudokuTable(81 - difficultyParameters.numberOfClosedCells).generate()
            TableGenerationAlgorithm.RANDOM_EMPTY -> CleanSudokuTable().generate()
        }
        return SudokuGame(
            GameStats(difficultyParameters.chosenDifficulty, GameTime(0, 0, 0), 0),
            table)
    }
}