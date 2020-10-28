package org.nika.sudokuService.interaction.request

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat

class SaveGameRequest (
    val format: SerializationFormat,
    gameState: GameState,
    timerValue: String
) : SudokuInteractionRequest(gameState, timerValue)