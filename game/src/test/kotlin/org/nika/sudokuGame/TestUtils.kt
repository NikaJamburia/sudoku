package org.nika.sudokuGame

import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.SudokuTable
import org.nika.sudokuGame.table.cells.ClosedCell
import org.nika.sudokuGame.table.cells.NO_VALUE
import org.nika.sudokuGame.table.cells.OpenCell
import org.nika.sudokuGame.table.cells.collection.Boxes
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.TableState

fun generate2X2Table(): SudokuTable {
    val cells= listOf(
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

fun twoCellsAreConflicting(coordinate1: Coordinates, coordinate2: Coordinates, table: TableState): Boolean =
    table.conflicts.any {
        it.conflictedCells.any { cell -> cell.coordinates() == coordinate1 }
                && it.conflictedCells.any { cell -> cell.coordinates() == coordinate2 }
    }

fun CellState.coordinates(): Coordinates = Coordinates(this.coordinateX, this.coordinateY)