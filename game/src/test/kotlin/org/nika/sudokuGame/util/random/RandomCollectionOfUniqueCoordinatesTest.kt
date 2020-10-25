package org.nika.sudokuGame.util.random

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RandomCollectionOfUniqueCoordinatesTest {
    @Test
    fun throwsExceptionIfTotalNumberOfCellsIsLesserThenCollectionSize() {
        assertThrows(IllegalStateException::class.java) { RandomCollectionOfUniqueCoordinates(10, 2, 2) }
    }

    @Test
    fun generatedCoordinatesAreUnique() {
        val coordinates = RandomCollectionOfUniqueCoordinates(10, 6, 6).coordinates
        assertEquals(10, coordinates.size)

        coordinates.maxBy { it.x }!!.x >
        coordinates.maxBy { it.y }!!.y
    }

    @Test
    fun generatedCoordinatesHaveValidValues() {
        val coordinates = RandomCollectionOfUniqueCoordinates(1000, 100, 10).coordinates
        assertEquals(1000, coordinates.size)

        assertTrue(coordinates.maxBy { it.x }!!.x <= 100)
        assertTrue(coordinates.maxBy { it.y }!!.y <= 10)
    }
}