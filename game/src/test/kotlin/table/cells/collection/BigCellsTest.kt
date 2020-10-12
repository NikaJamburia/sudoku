package table.cells.collection

import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.Cell
import table.cells.NO_VALUE
import table.cells.OpenCell
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BigCellsTest {
    @Test
    fun groupsSmallSquareTableCellsCorrectly() {
        val listOfGroupedCells = BigCells(cells6X6()).groupedCells()
        assertEquals(4, listOfGroupedCells.size)
        assertTrue(listOfGroupedCells.all { it.content.size == 9 })

    }

    @Test
    fun groupsSmallRectangleTableCellsCorrectly() {
        val listOfGroupedCells = BigCells(cells6X3()).groupedCells()
        assertEquals(2, listOfGroupedCells.size)
        assertTrue(listOfGroupedCells.all { it.content.size == 9 })
    }

    @Test
    fun correctlyIdentifiesConflicts() {
        val bigCells = BigCells(cells6X6())
        assertEquals(6, bigCells.findConflicts().size)
        fillCellIn(bigCells, 5, Coordinates(3, 4))
        fillCellIn(bigCells, 5, Coordinates(2, 5))
        assertEquals(7, bigCells.findConflicts().size)

    }

    private fun fillCellIn(bigCells: BigCells, value: Int, coordinates: Coordinates) {
        bigCells.groupedCells().flatMap { it.content }.first { it.findLocation().sameAs(coordinates) }.fillValue(value)
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

}