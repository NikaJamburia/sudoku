package table.generation

import table.Coordinates
import table.SudokuTable
import table.cells.Cell
import table.cells.ClosedCell
import table.cells.NO_VALUE
import table.cells.OpenCell
import table.cells.collection.Boxes

class MockGeneratedSudokuTable: GeneratedSudokuTable {

    private val closedCells = listOf<Cell>(
        ClosedCell(3, Coordinates(1, 1)),
        ClosedCell(5, Coordinates(1, 2)),
        ClosedCell(9, Coordinates(1, 5)),
        ClosedCell(1, Coordinates(1, 7)),
        ClosedCell(2, Coordinates(2, 2)),
        ClosedCell(8, Coordinates(2, 3)),
        ClosedCell(5, Coordinates(2, 6)),
        ClosedCell(3, Coordinates(2, 7)),
        ClosedCell(6, Coordinates(3, 1)),
        ClosedCell(7, Coordinates(3, 3)),
        ClosedCell(3, Coordinates(3, 4)),
        ClosedCell(5, Coordinates(3, 9)),
        ClosedCell(5, Coordinates(4, 1)),
        ClosedCell(8, Coordinates(4, 5)),
        ClosedCell(2, Coordinates(4, 9)),
        ClosedCell(1, Coordinates(5, 4)),
        ClosedCell(6, Coordinates(5, 5)),
        ClosedCell(9, Coordinates(5, 6)),
        ClosedCell(8, Coordinates(6, 1)),
        ClosedCell(3, Coordinates(6, 5)),
        ClosedCell(6, Coordinates(6, 9)),
        ClosedCell(4, Coordinates(7, 1)),
        ClosedCell(6, Coordinates(7, 6)),
        ClosedCell(2, Coordinates(7, 7)),
        ClosedCell(3, Coordinates(7, 9)),
        ClosedCell(3, Coordinates(8, 3)),
        ClosedCell(8, Coordinates(8, 4)),
        ClosedCell(5, Coordinates(8, 7)),
        ClosedCell(7, Coordinates(8, 8)),
        ClosedCell(1, Coordinates(9, 3)),
        ClosedCell(5, Coordinates(9, 5)),
        ClosedCell(4, Coordinates(9, 8))
    )

    override fun generate(): SudokuTable {
        val cells = (1..9).map { x ->
            (1..9).map { y ->
                closedCells.find { it.location() == Coordinates(x, y) }
                    ?: OpenCell(NO_VALUE, Coordinates(x, y))
            }
        }.flatten()

        return SudokuTable(Boxes(cells))
    }
}