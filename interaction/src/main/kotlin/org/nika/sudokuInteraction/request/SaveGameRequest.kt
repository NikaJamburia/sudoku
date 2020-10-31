package org.nika.sudokuInteraction.request

import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.state.GameState

class SaveGameRequest (
    val format: SerializationFormat,
    gameState: GameState,
    timerValue: String
) : SudokuInteractionRequest(gameState, timerValue)