package org.nika.sudokuService


import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.cells.ClosedCell
import org.nika.sudokuGame.table.cells.NO_VALUE
import org.nika.sudokuGame.table.cells.OpenCell
import org.nika.sudokuGame.table.cells.collection.Columns
import org.nika.sudokuGame.table.cells.collection.Rows
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.TableState


fun game4X4With1Empty(playedTime: String, turns: Int): GameState {
    val tableState = TableState(
        listOf(
            CellState(1, 1, 1, true),
            CellState(2, 1, 2, false),
            CellState(3, 2, 1, true),
            CellState(NO_VALUE, 2, 2, true)
        ), listOf(), false, false)

    return GameState(playedTime, turns, false, tableState)
}

fun stateToCell(cellState: CellState): Cell =
    if (cellState.cellIsOpen) {
        OpenCell(cellState.value, Coordinates(cellState.coordinateX, cellState.coordinateY))
    } else {
        ClosedCell(cellState.value,Coordinates(cellState.coordinateX, cellState.coordinateY))
    }

fun rowSize(tableState: TableState): Int =
    Rows(tableState.cells.map { stateToCell(it) })
        .groupedCells()
        .first()
        .content.size

fun columnSize(tableState: TableState): Int =
    Columns(tableState.cells.map { stateToCell(it) })
        .groupedCells()
        .first()
        .content.size

fun countClosedCells(tableState: TableState): Int = tableState.cells.count { !it.cellIsOpen }

fun TableState.findCell(x: Int, y: Int): CellState = this.cells.first { it.coordinateX == x && it.coordinateY == y }