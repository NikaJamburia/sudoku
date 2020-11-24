package org.nika.sudokuTable

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class CoordinatesTest {
    @Test
    fun correctlyComparesItselfToOtherCoordinate() {
        val coordinate = Coordinates(1, 2)
        val sameCoordinate = Coordinates(1, 2)
        val otherCoordinate = Coordinates(1, 3)

        assertEquals(coordinate, sameCoordinate)
        assertNotEquals(coordinate, otherCoordinate)
    }

    @Test
    fun sameCoordinatesCantBeAddedToSet() {
        val set = mutableSetOf(Coordinates(1, 1))

        assertFalse(set.add(Coordinates(1, 1)) )
        assertTrue(set.add(Coordinates(1, 2)))
        assertEquals(2, set.size)
        assertFalse(set.add(Coordinates(1, 2)))
    }
}