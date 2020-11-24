package org.nika.sudokuTable.util.random

import org.nika.sudokuTable.Coordinates
import java.lang.IllegalStateException

class RandomCollectionOfUniqueCoordinates (
    private val collectionSize: Int,
    private val cellsOnXAxis: Int,
    private val cellsOnYAxis: Int
) {
    val coordinates: Set<Coordinates> = generate()

    private fun generate(): Set<Coordinates> {
        if ((cellsOnXAxis * cellsOnYAxis) < collectionSize) {
            throw IllegalStateException("Total number of cells can't be less then required collection size")
        }

        val generatedCoordinates = mutableSetOf<Coordinates>()

        while (generatedCoordinates.size < collectionSize) {
            generatedCoordinates.add(Coordinates(randomX(), randomY()))
        }
        return generatedCoordinates
    }

    private fun randomX(): Int = (1..cellsOnXAxis).random()
    private fun randomY(): Int = (1..cellsOnYAxis).random()
}