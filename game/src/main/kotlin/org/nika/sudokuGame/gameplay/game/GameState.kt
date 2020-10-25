package org.nika.sudokuGame.gameplay.game

import org.nika.sudokuGame.table.state.TableState

data class GameState (
    val playedTime: String,
    val numberOfTurns: Int,
    val gameIsWon: Boolean,
    val tableState: TableState
)