package org.nika.sudokuGame

import org.nika.sudokuGame.time.PassedTime
import org.nika.sudokuInteraction.enums.SudokuDifficulty

data class GameStats (
    var difficulty: SudokuDifficulty,
    var playedTime: PassedTime,
    var numberOfTurns: Int
) {
    fun update(newTime: PassedTime): GameStats =
        GameStats(difficulty, newTime, numberOfTurns + 1)
}