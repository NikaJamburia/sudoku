package table

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CoordinatesTest {
    @Test
    fun correctlyComparesItselfToOtherCoordinate() {
        val coordinate = Coordinates(1, 2)
        val sameCoordinate = Coordinates(1, 2)
        val otherCoordinate = Coordinates(1, 3)

        assertTrue(coordinate.sameAs(sameCoordinate))
        assertFalse(coordinate.sameAs(otherCoordinate))
    }
}