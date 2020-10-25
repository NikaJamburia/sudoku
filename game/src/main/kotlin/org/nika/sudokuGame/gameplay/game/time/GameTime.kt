package org.nika.sudokuGame.gameplay.game.time

import java.lang.IllegalArgumentException

class GameTime (
    private val hours: Int,
    private val minutes: Int,
    private val seconds: Int
): PassedTime {
    init {
        if (
            (seconds < 0 || seconds > 59)
            || (minutes < 0 || minutes > 59)
            || hours < 0
        ) {
            throw IllegalArgumentException("Wrong values")
        }
    }

    override fun asString(): String {
        val hourString: String = if (hours < 10) { "0$hours" } else { hours.toString() }
        val minuteString: String = if (minutes < 10) { "0$minutes" } else { minutes.toString() }
        val secondString: String = if (seconds < 10) { "0$seconds" } else { seconds.toString() }
        return "${hourString}:${minuteString}:${secondString}"
    }

}