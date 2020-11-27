package org.nika.sudokuTable.cells.collection

import org.junit.jupiter.api.Test
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.cells.Cell
import org.nika.sudokuTable.cells.NO_VALUE
import org.nika.sudokuTable.cells.OpenCell
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoxesTest {

    @Test
    fun farApartCellsAreGroupedInSeparateBoxes() {
        val cells = listOf(
            OpenCell(1, Coordinates(1, 1)),
            OpenCell(2, Coordinates(1, 4)),
            OpenCell(3, Coordinates(4, 1)),
            OpenCell(4, Coordinates(4, 4))
        )
        val listOfGroupedCells = Boxes(cells).asList()
        assertEquals(4, listOfGroupedCells.size)
        assertEquals(4, listOfGroupedCells.flatMap { it.content }.size)
    }

    @Test
    fun groupsTableWithOneBigCellCellCorrectly() {
        val listOfGroupedCells = Boxes(cells2X2()).asList()
        assertEquals(4, listOfGroupedCells.flatMap { it.content }.size)
        assertEquals(1, listOfGroupedCells.size)
    }

    @Test
    fun groupsSmallSquareTableCellsCorrectly() {
        val listOfGroupedCells = Boxes(cells6X6()).asList()
        assertEquals(4, listOfGroupedCells.size)
        assertEquals(36, listOfGroupedCells.flatMap { it.content }.size)
        assertTrue(listOfGroupedCells.all { it.content.size == 9 })
    }

    @Test
    fun groupsSmallRectangleTableCellsCorrectly() {
        val listOfGroupedCells = Boxes(cells6X3()).asList()
        assertEquals(2, listOfGroupedCells.size)
        assertEquals(18, listOfGroupedCells.flatMap { it.content }.size)
        assertTrue(listOfGroupedCells.all { it.content.size == 9 })
    }

    @Test
    fun correctlyIdentifiesConflicts() {
        val bigCells = Boxes(cells6X6())
        assertEquals(6, bigCells.findConflicts().size)
        fillCellIn(bigCells, 5, Coordinates(3, 4))
        fillCellIn(bigCells, 5, Coordinates(2, 5))
        assertEquals(7, bigCells.findConflicts().size)

    }

    private fun fillCellIn(boxes: Boxes, value: Int, coordinates: Coordinates) {
        boxes.asList().flatMap { it.content }.first { it.location().equals(coordinates) }.fillValue(value)
    }

    private fun cells6X6(): List<Cell> =
        listOf(
            OpenCell(3, Coordinates(1, 1)),
            OpenCell(4, Coordinates(2, 1)),
            OpenCell(2, Coordinates(3, 1)),
            OpenCell(NO_VALUE, Coordinates(1, 2)),
            OpenCell(4, Coordinates(2, 2)),
            OpenCell(NO_VALUE, Coordinates(3, 2)),
            OpenCell(2, Coordinates(1, 3)),
            OpenCell(NO_VALUE, Coordinates(2, 3)),
            OpenCell(3, Coordinates(3, 3)),
            OpenCell(NO_VALUE, Coordinates(1, 4)),
            OpenCell(NO_VALUE, Coordinates(2, 4)),
            OpenCell(NO_VALUE, Coordinates(3, 4)),
            OpenCell(5, Coordinates(1, 5)),
            OpenCell(NO_VALUE, Coordinates(2, 5)),
            OpenCell(NO_VALUE, Coordinates(3, 5)),
            OpenCell(NO_VALUE, Coordinates(1, 6)),
            OpenCell(NO_VALUE, Coordinates(2, 6)),
            OpenCell(4, Coordinates(3, 6)),
            OpenCell(NO_VALUE, Coordinates(4, 1)),
            OpenCell(8, Coordinates(5, 1)),
            OpenCell(8, Coordinates(6, 1)),
            OpenCell(NO_VALUE, Coordinates(4, 2)),
            OpenCell(8, Coordinates(5, 2)),
            OpenCell(8, Coordinates(6, 2)),
            OpenCell(NO_VALUE, Coordinates(4, 3)),
            OpenCell(NO_VALUE, Coordinates(5, 3)),
            OpenCell(1, Coordinates(6, 3)),
            OpenCell(NO_VALUE, Coordinates(4, 4)),
            OpenCell(5, Coordinates(5, 4)),
            OpenCell(NO_VALUE, Coordinates(6, 4)),
            OpenCell(3, Coordinates(4, 5)),
            OpenCell(6, Coordinates(5, 5)),
            OpenCell(5, Coordinates(6, 5)),
            OpenCell(NO_VALUE, Coordinates(4, 6)),
            OpenCell(8, Coordinates(5, 6)),
            OpenCell(6, Coordinates(6, 6))
        )

    private fun cells6X3(): List<Cell> =
        listOf(
            OpenCell(NO_VALUE, Coordinates(4, 1)),
            OpenCell(NO_VALUE, Coordinates(5, 1)),
            OpenCell(7, Coordinates(6, 1)),
            OpenCell(NO_VALUE, Coordinates(4, 2)),
            OpenCell(NO_VALUE, Coordinates(5, 2)),
            OpenCell(NO_VALUE, Coordinates(6, 2)),
            OpenCell(NO_VALUE, Coordinates(4, 3)),
            OpenCell(3, Coordinates(5, 3)),
            OpenCell(NO_VALUE, Coordinates(6, 3)),
            OpenCell(NO_VALUE, Coordinates(1, 1)),
            OpenCell(NO_VALUE, Coordinates(2, 1)),
            OpenCell(NO_VALUE, Coordinates(3, 1)),
            OpenCell(NO_VALUE, Coordinates(1, 2)),
            OpenCell(5, Coordinates(2, 2)),
            OpenCell(NO_VALUE, Coordinates(3, 2)),
            OpenCell(2, Coordinates(1, 3)),
            OpenCell(NO_VALUE, Coordinates(2, 3)),
            OpenCell(NO_VALUE, Coordinates(3, 3))
        )

    private fun cells2X2(): List<Cell> = listOf(
        OpenCell(NO_VALUE, Coordinates(1, 1)),
        OpenCell(NO_VALUE, Coordinates(1, 2)),
        OpenCell(NO_VALUE, Coordinates(2, 1)),
        OpenCell(NO_VALUE, Coordinates(2, 2))
    )

}