package org.nika.sudokuService

import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.TableState


fun game4X4With1Empty(playedTime: String, turns: Int): GameState {
    val tableState = TableState(
        listOf(
            CellState(1, 1, 1, true),
            CellState(2, 1, 2, false),
            CellState(3, 2, 1, true),
            CellState(0, 2, 2, true)
        ), listOf(), false, false)

    return GameState(playedTime, turns, false, tableState)
}

fun rowSize(tableState: TableState): Int =
    tableState.cells.groupBy { it.coordinateY }.size


fun columnSize(tableState: TableState): Int =
    tableState.cells.groupBy { it.coordinateX }.size

fun countClosedCells(tableState: TableState): Int = tableState.cells.count { !it.cellIsOpen }

fun TableState.findCell(x: Int, y: Int): CellState = this.cells.first { it.coordinateX == x && it.coordinateY == y }
