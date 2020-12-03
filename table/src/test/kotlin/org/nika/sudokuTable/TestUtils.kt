package org.nika.sudokuTable

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.TableState
import org.nika.sudokuTable.cells.ClosedCell
import org.nika.sudokuTable.cells.NO_VALUE
import org.nika.sudokuTable.cells.OpenCell
import org.nika.sudokuTable.cells.collection.Boxes

fun generate2X2Table(): SudokuTable {
    val cells = listOf(
        OpenCell(NO_VALUE, Coordinates(1, 1)),
        OpenCell(NO_VALUE, Coordinates(1, 2)),
        OpenCell(NO_VALUE, Coordinates(2, 1)),
        OpenCell(NO_VALUE, Coordinates(2, 2))
    )
    return SudokuTable(
        Boxes(cells)
    )
}

fun generate6X3Table(): SudokuTable {
    val cells = listOf(
        OpenCell(NO_VALUE, Coordinates(1, 1)),
        OpenCell(NO_VALUE, Coordinates(2, 1)),
        OpenCell(NO_VALUE, Coordinates(3, 1)),
        OpenCell(NO_VALUE, Coordinates(1, 2)),
        ClosedCell(5, Coordinates(2, 2)),
        OpenCell(NO_VALUE, Coordinates(3, 2)),
        ClosedCell(2, Coordinates(1, 3)),
        OpenCell(NO_VALUE, Coordinates(2, 3)),
        OpenCell(NO_VALUE, Coordinates(3, 3)),
        OpenCell(NO_VALUE, Coordinates(4, 1)),
        OpenCell(NO_VALUE, Coordinates(5, 1)),
        ClosedCell(7, Coordinates(6, 1)),
        OpenCell(NO_VALUE, Coordinates(4, 2)),
        OpenCell(NO_VALUE, Coordinates(5, 2)),
        OpenCell(NO_VALUE, Coordinates(6, 2)),
        OpenCell(NO_VALUE, Coordinates(4, 3)),
        ClosedCell(3, Coordinates(5, 3)),
        OpenCell(NO_VALUE, Coordinates(6, 3))
    )


    return SudokuTable(Boxes(cells))
}

fun generate3X6Table(): SudokuTable {
    val cells = listOf(
        OpenCell(NO_VALUE, Coordinates(1, 1)),
        OpenCell(NO_VALUE, Coordinates(2, 1)),
        OpenCell(NO_VALUE, Coordinates(3, 1)),
        OpenCell(NO_VALUE, Coordinates(1, 2)),
        ClosedCell(5, Coordinates(2, 2)),
        OpenCell(NO_VALUE, Coordinates(3, 2)),
        ClosedCell(2, Coordinates(1, 3)),
        OpenCell(NO_VALUE, Coordinates(2, 3)),
        OpenCell(NO_VALUE, Coordinates(3, 3)),
        OpenCell(NO_VALUE, Coordinates(1, 4)),
        OpenCell(NO_VALUE, Coordinates(2, 4)),
        ClosedCell(7, Coordinates(3, 4)),
        ClosedCell(3, Coordinates(1, 5)),
        OpenCell(NO_VALUE, Coordinates(2, 5)),
        OpenCell(NO_VALUE, Coordinates(3, 5)),
        OpenCell(NO_VALUE, Coordinates(1, 6)),
        OpenCell(NO_VALUE, Coordinates(2, 6)),
        OpenCell(NO_VALUE, Coordinates(3, 6))
    )

    return SudokuTable(Boxes(cells))
}

fun generate6X6Table(): SudokuTable {
    val cells = listOf(
        OpenCell(NO_VALUE, Coordinates(1, 1)),
        OpenCell(NO_VALUE, Coordinates(2, 1)),
        OpenCell(NO_VALUE, Coordinates(3, 1)),
        OpenCell(NO_VALUE, Coordinates(1, 2)),
        OpenCell(NO_VALUE, Coordinates(2, 2)),
        ClosedCell(6, Coordinates(3, 2)),
        OpenCell(NO_VALUE, Coordinates(1, 3)),
        ClosedCell(5, Coordinates(2, 3)),
        OpenCell(NO_VALUE, Coordinates(3, 3)),
        OpenCell(NO_VALUE, Coordinates(1, 4)),
        OpenCell(NO_VALUE, Coordinates(2, 4)),
        OpenCell(NO_VALUE, Coordinates(3, 4)),
        ClosedCell(5, Coordinates(1, 5)),
        OpenCell(NO_VALUE, Coordinates(2, 5)),
        OpenCell(NO_VALUE, Coordinates(3, 5)),
        OpenCell(NO_VALUE, Coordinates(1, 6)),
        OpenCell(NO_VALUE, Coordinates(2, 6)),
        ClosedCell(4, Coordinates(3, 6)),
        OpenCell(NO_VALUE, Coordinates(4, 1)),
        ClosedCell(9, Coordinates(5, 1)),
        OpenCell(NO_VALUE, Coordinates(6, 1)),
        OpenCell(NO_VALUE, Coordinates(4, 2)),
        OpenCell(NO_VALUE, Coordinates(5, 2)),
        OpenCell(NO_VALUE, Coordinates(6, 2)),
        OpenCell(NO_VALUE, Coordinates(4, 3)),
        OpenCell(NO_VALUE, Coordinates(5, 3)),
        ClosedCell(1, Coordinates(6, 3)),
        OpenCell(NO_VALUE, Coordinates(4, 4)),
        OpenCell(NO_VALUE, Coordinates(5, 4)),
        OpenCell(NO_VALUE, Coordinates(6, 4)),
        ClosedCell(3, Coordinates(4, 5)),
        OpenCell(NO_VALUE, Coordinates(5, 5)),
        OpenCell(NO_VALUE, Coordinates(6, 5)),
        OpenCell(NO_VALUE, Coordinates(4, 6)),
        ClosedCell(8, Coordinates(5, 6)),
        OpenCell(NO_VALUE, Coordinates(6, 6))
    )

    return SudokuTable(Boxes(cells))
}

fun sudokuTableWithTwoSolutions(): SudokuTable {
    val cells = listOf(
        OpenCell(2, Coordinates(1, 1)),
        OpenCell(4, Coordinates(1, 2)),
        OpenCell(8, Coordinates(1, 3)),
        OpenCell(9, Coordinates(2, 1)),
        OpenCell(3, Coordinates(2, 2)),
        OpenCell(7, Coordinates(2, 3)),
        OpenCell(5, Coordinates(3, 1)),
        OpenCell(1, Coordinates(3, 2)),
        OpenCell(6, Coordinates(3, 3)),
        OpenCell(3, Coordinates(1, 4)),
        OpenCell(6, Coordinates(1, 5)),
        OpenCell(5, Coordinates(1, 6)),
        OpenCell(8, Coordinates(2, 4)),
        OpenCell(1, Coordinates(2, 5)),
        OpenCell(4, Coordinates(2, 6)),
        OpenCell(7, Coordinates(3, 4)),
        OpenCell(2, Coordinates(3, 5)),
        OpenCell(9, Coordinates(3, 6)),
        OpenCell(7, Coordinates(1, 7)),
        OpenCell(9, Coordinates(1, 8)),
        OpenCell(1, Coordinates(1, 9)),
        OpenCell(6, Coordinates(2, 7)),
        OpenCell(2, Coordinates(2, 8)),
        OpenCell(5, Coordinates(2, 9)),
        OpenCell(3, Coordinates(3, 7)),
        OpenCell(8, Coordinates(3, 8)),
        OpenCell(4, Coordinates(3, 9)),
        OpenCell(7, Coordinates(4, 1)),
        OpenCell(8, Coordinates(4, 2)),
        OpenCell(1, Coordinates(4, 3)),
        OpenCell(4, Coordinates(5, 1)),
        OpenCell(6, Coordinates(5, 2)),
        OpenCell(9, Coordinates(5, 3)),
        OpenCell(3, Coordinates(6, 1)),
        OpenCell(5, Coordinates(6, 2)),
        OpenCell(2, Coordinates(6, 3)),
        OpenCell(4, Coordinates(4, 4)),
        OpenCell(3, Coordinates(4, 5)),
        OpenCell(2, Coordinates(4, 6)),
        OpenCell(5, Coordinates(5, 4)),
        OpenCell(8, Coordinates(5, 5)),
        OpenCell(1, Coordinates(5, 6)),
        OpenCell(9, Coordinates(6, 4)),
        OpenCell(7, Coordinates(6, 5)),
        OpenCell(6, Coordinates(6, 6)),
        OpenCell(5, Coordinates(4, 7)),
        OpenCell(6, Coordinates(4, 8)),
        OpenCell(9, Coordinates(4, 9)),
        OpenCell(2, Coordinates(5, 7)),
        OpenCell(7, Coordinates(5, 8)),
        OpenCell(3, Coordinates(5, 9)),
        OpenCell(4, Coordinates(6, 7)),
        OpenCell(1, Coordinates(6, 8)),
        OpenCell(8, Coordinates(6, 9)),
        OpenCell(8, Coordinates(7, 1)),
        OpenCell(9, Coordinates(7, 2)),
        OpenCell(5, Coordinates(7, 3)),
        OpenCell(6, Coordinates(8, 1)),
        OpenCell(0, Coordinates(8, 2)),
        OpenCell(4, Coordinates(8, 3)),
        OpenCell(1, Coordinates(9, 1)),
        OpenCell(0, Coordinates(9, 2)),
        OpenCell(3, Coordinates(9, 3)),
        OpenCell(2, Coordinates(7, 4)),
        OpenCell(4, Coordinates(7, 5)),
        OpenCell(7, Coordinates(7, 6)),
        OpenCell(1, Coordinates(8, 4)),
        OpenCell(9, Coordinates(8, 5)),
        OpenCell(3, Coordinates(8, 6)),
        OpenCell(6, Coordinates(9, 4)),
        OpenCell(5, Coordinates(9, 5)),
        OpenCell(8, Coordinates(9, 6)),
        OpenCell(1, Coordinates(7, 7)),
        OpenCell(3, Coordinates(7, 8)),
        OpenCell(6, Coordinates(7, 9)),
        OpenCell(8, Coordinates(8, 7)),
        OpenCell(5, Coordinates(8, 8)),
        OpenCell(0, Coordinates(8, 9)),
        OpenCell(9, Coordinates(9, 7)),
        OpenCell(4, Coordinates(9, 8)),
        OpenCell(0, Coordinates(9, 9))
    )

    return SudokuTable((Boxes(cells)))
}

fun twoCellsAreConflicting(coordinate1: Coordinates, coordinate2: Coordinates, table: TableState): Boolean =
    table.conflicts.any {
        it.conflictedCells.any { cell -> cell.coordinates() == coordinate1 }
                && it.conflictedCells.any { cell -> cell.coordinates() == coordinate2 }
    }

fun CellState.coordinates(): Coordinates = Coordinates(this.coordinateX, this.coordinateY)

fun TableState.findCell(x: Int, y: Int): CellState = this.cells.first { it.coordinateX == x && it.coordinateY == y }