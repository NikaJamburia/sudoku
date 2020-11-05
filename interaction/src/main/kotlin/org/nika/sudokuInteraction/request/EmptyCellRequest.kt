package org.nika.sudokuInteraction.request

import org.nika.sudokuInteraction.state.GameState

class EmptyCellRequest (
    val coordinateX: Int,
    val coordinateY: Int,
    gameState: GameState,
    timerValue: String
) : SudokuInteractionRequest(gameState, timerValue)