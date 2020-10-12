package gameplay.saveload.deserialization

import com.fasterxml.jackson.databind.ObjectMapper
import gameplay.game.GameState

class JacksonDeserializedSudokuGame(
    private val stringGame: String
): DeserializedSudokuGame {
    override fun state(): GameState =
        ObjectMapper().readValue(stringGame, GameState::class.java)

}