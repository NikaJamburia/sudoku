package org.nika.sudokuGame.time

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class GameTimeFromStringTest {
    @Test
    fun throwsExceptionWhenReceivesWrongFormat() {
        assertThrows(IllegalArgumentException::class.java) { GameTimeFromString("0:01:02") }
        assertThrows(IllegalArgumentException::class.java) { GameTimeFromString("01:0:02") }
        assertThrows(IllegalArgumentException::class.java) { GameTimeFromString("01:01:0") }
    }

    @Test
    fun generatesCorrectGameTimeWithGivenString() {
        assertEquals("01:05:02", GameTimeFromString("01:05:02").asString())
        assertEquals("00:20:02", GameTimeFromString("00:20:02").asString())
        assertEquals("00:00:00", GameTimeFromString("00:00:00").asString())
    }
}