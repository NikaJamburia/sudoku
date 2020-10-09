package gameplay.game

import java.lang.IllegalArgumentException

class GameTime (
    val hours: Int,
    val minutes: Int,
    val seconds: Int
) {
    init {
        if (
            (seconds < 0 || seconds > 60)
            || (minutes < 0 || minutes > 60)
        ) {
            throw IllegalArgumentException("Wrong values")
        }
    }

    override fun toString(): String {
        val hourString: String = if (hours < 10) { "0$hours" } else { hours.toString() }
        val minuteString: String = if (minutes < 10) { "0$minutes" } else { minutes.toString() }
        val secondString: String = if (seconds < 10) { "0$seconds" } else { seconds.toString() }
        return "${hourString}:${minuteString}:${secondString}"
    }

    companion object {
        fun fromString(string: String): GameTime {
            val pattern = """\d{2}:\d{2}:\d{2}"""
            if (string.matches(pattern.toRegex())) {
                return GameTime(
                    string.substring(0, 2).toInt(),
                    string.substring(3, 5).toInt(),
                    string.substring(6, 8).toInt()
                )
            } else {
                throw IllegalArgumentException("Wrong pattern")
            }
        }
    }
}