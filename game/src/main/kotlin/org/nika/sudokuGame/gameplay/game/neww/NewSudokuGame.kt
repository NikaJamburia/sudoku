package org.nika.sudokuGame.gameplay.game.neww


import org.nika.sudokuGame.gameplay.game.GameStats
import org.nika.sudokuGame.gameplay.game.SudokuGame
import org.nika.sudokuGame.gameplay.game.time.GameTime
import org.nika.sudokuGame.table.generation.MockGeneratedSudokuTable
import org.nika.sudokuGame.table.generation.RandomBasedNewSudokuTable

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