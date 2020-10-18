package gameplay.game.time

import org.junit.Assert.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class GameTimeTest {
    @Test
    fun throwsExceptionWhenHasWrongValuesForTime() {
        assertThrows(IllegalArgumentException::class.java) { GameTime(0, 10, -1) }
        assertThrows(IllegalArgumentException::class.java) { GameTime(0, 10, 60) }
        assertThrows(IllegalArgumentException::class.java) { GameTime(0, -1, 59) }
        assertThrows(IllegalArgumentException::class.java) { GameTime(0, 60, 59) }
        assertThrows(IllegalArgumentException::class.java) { GameTime(-1, 59, 59) }
    }

    @Test
    fun correctlyRepresentsItselfAsString() {
        assertEquals("01:05:02", GameTime(1, 5 ,2).asString())
        assertEquals("00:20:02", GameTime(0, 20 ,2).asString())
        assertEquals("00:00:00", GameTime(0, 0 ,0).asString())
    }
}