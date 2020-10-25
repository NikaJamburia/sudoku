package org.nika.sudokuGame.gameplay.saveload.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import org.nika.sudokuGame.gameplay.game.GameState

class JacksonSerializedSudokuGameState(
    private val gameState: GameState
): SerializedSudokuGameState {
    override fun asString(): String = ObjectMapper().writeValueAsString(gameState)
}