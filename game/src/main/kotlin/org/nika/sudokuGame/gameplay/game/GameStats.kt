package org.nika.sudokuGame.gameplay.game

import org.nika.sudokuGame.gameplay.game.time.PassedTime

data class GameStats (
    var playedTime: PassedTime,
    var numberOfTurns: Int
)