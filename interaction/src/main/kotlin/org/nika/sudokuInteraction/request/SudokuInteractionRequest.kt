package org.nika.sudokuInteraction.request

import org.nika.sudokuInteraction.state.GameState

open class SudokuInteractionRequest (
    val gameState: GameState,
    val timerValue: String
)