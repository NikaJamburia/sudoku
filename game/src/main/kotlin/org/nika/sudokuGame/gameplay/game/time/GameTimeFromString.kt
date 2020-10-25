package org.nika.sudokuGame.gameplay.game.time

import java.lang.IllegalArgumentException

class GameTimeFromString(
    private val string: String
) : PassedTime {
    private val pattern = """\d{2}:\d{2}:\d{2}"""
    private val gameTime = parseString()

    private fun parseString(): GameTime {
        if (string.matches(pattern.toRegex())) {
            return GameTime(
                string.substring(0, 2).toInt(),
                string.substring(3, 5).toInt(),
                string.substring(6, 8).toInt())
        } else {
            throw IllegalArgumentException("Wrong pattern")
        }
    }

    override fun asString(): String = gameTime.asString()

}
