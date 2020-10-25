package org.nika.sudokuService

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.cells.NO_VALUE
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