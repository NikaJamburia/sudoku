package org.nika.sudokuGame.gameplay.saveload.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import org.nika.sudokuInteraction.state.GameState

class JacksonSerializedSudokuGameState(
    private val gameState: GameState
): SerializedSudokuGameState {
    override fun asString(): String = ObjectMapper().writeValueAsString(gameState)
}