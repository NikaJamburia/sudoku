package org.nika.sudokuGame

import org.nika.sudokuGame.time.PassedTime

data class GameStats (
    var playedTime: PassedTime,
    var numberOfTurns: Int
) {
    fun update(newTime: PassedTime): GameStats =
        GameStats(newTime, numberOfTurns + 1)

}