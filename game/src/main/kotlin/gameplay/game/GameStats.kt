package gameplay.game

import gameplay.game.time.PassedTime

data class GameStats (
    var playedTime: PassedTime,
    var numberOfTurns: Int
)