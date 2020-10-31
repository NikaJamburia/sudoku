package org.nika.sudokuInteraction.state

data class GameState (
    val playedTime: String,
    val numberOfTurns: Int,
    val gameIsWon: Boolean,
    val tableState: TableState
)