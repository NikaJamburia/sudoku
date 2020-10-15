package table.generation

import table.Coordinates
import table.SudokuTable
import table.cells.Cell
import table.cells.ClosedCell
import table.cells.NO_VALUE
import table.cells.OpenCell
import table.cells.collection.Boxes
import table.cells.collection.Columns
import table.cells.collection.GroupedSelections
import table.cells.collection.Rows
import util.random.RandomCollectionOfUniqueCoordinates
import util.random.RandomSequenceOfUniqueInts

class NewSudokuTable(
    private val cellsOnXAxis: Int,
    private val cellsOnYAxis: Int,
    private val numberOfClosedCells: Int
) : GeneratedSudokuTable {
    private val closedCells = createClosedCells()

    override fun generate(): SudokuTable {
        val cells = (1..cellsOnXAxis).map { x ->
            (1..cellsOnYAxis).map { y ->
                createCell(Coordinates(x, y))
            }
        }.flatten()

        return SudokuTable(Boxes(cells))
    }

    private fun createCell(coordinates: Coordinates): Cell =
        closedCells.find { it.location() == coordinates }
            ?: OpenCell(NO_VALUE, coordinates)

    private fun createClosedCells(): List<ClosedCell> {
        val randomOpenCells = RandomCollectionOfUniqueCoordinates(numberOfClosedCells, cellsOnXAxis, cellsOnYAxis)
            .coordinates
            .map { OpenCell(NO_VALUE, it) }

        randomOpenCells.forEach {
            if (it.isEmpty()) {
                val potentiallyConflictingCells = findPotentialConflicts(it, randomOpenCells)
                val alreadyFilled = potentiallyConflictingCells.filter { cell -> !cell.isEmpty() }
                val cellsToFill = potentiallyConflictingCells.filter { cell -> cell.isEmpty() }
                //TODO fix errors using alreadyFilled and cellsToFill
                val uniqueValues = RandomSequenceOfUniqueInts(potentiallyConflictingCells.size, 1, 9)
                cellsToFill
                    .forEach { cell -> cell.fillValue(uniqueValues.next()) }
            }
        }
        return randomOpenCells.map { ClosedCell(it.getValue(), it.location()) }
    }

    private fun findPotentialConflicts(forCell: Cell, inCellsList: List<OpenCell>): Set<Cell> =
        listOf(
            findCellsInSameGroupedSelection(forCell, Rows(inCellsList)),
            findCellsInSameGroupedSelection(forCell, Columns(inCellsList)),
            findCellsInSameGroupedSelection(forCell, Boxes(inCellsList))
        ).flatten().toSet()

    private fun findCellsInSameGroupedSelection(forCell: Cell, selection: GroupedSelections): List<Cell> =
        selection.groupedCells()
            .map { it.content }
            .first { it.any { c -> c.location() == forCell.location() } }


}