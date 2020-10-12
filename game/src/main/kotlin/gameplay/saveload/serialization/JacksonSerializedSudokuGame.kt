package gameplay.saveload.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import gameplay.game.GameState

class JacksonSerializedSudokuGame(
    private val gameState: GameState
): SerializedSudokuGame {
    override fun asString(): String = ObjectMapper().writeValueAsString(gameState)
}