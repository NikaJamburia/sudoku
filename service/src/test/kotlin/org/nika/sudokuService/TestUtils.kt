package org.nika.sudokuService

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.cells.Cell
import org.nika.sudokuGame.table.cells.ClosedCell
import org.nika.sudokuGame.table.cells.NO_VALUE
import org.nika.sudokuGame.table.cells.OpenCell
import org.nika.sudokuGame.table.cells.collection.Columns
import org.nika.sudokuGame.table.cells.collection.Rows
import org.nika.sudokuGame.table.state.CellState
import org.nika.sudokuGame.table.state.TableState

fun game4X4With1Empty(playedTime: String, turns: Int): GameState {
    val tableState = TableState(
        listOf(
            CellState(1, Coordinates(1, 1), true),
            CellState(2, Coordinates(1, 2), false),
            CellState(3, Coordinates(2, 1), true),
            CellState(NO_VALUE, Coordinates(2, 2), true)
        ), listOf(), false, false)

    return GameState(playedTime, turns, false, tableState)
}

fun stateToCell(cellState: CellState): Cell =
    if (cellState.cellIsOpen) {
        OpenCell(cellState.value, cellState.coordinates)
    } else {
        ClosedCell(cellState.value, cellState.coordinates)
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