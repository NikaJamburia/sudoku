package gameplay.saveload.deserialization

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import gameplay.game.GameState

class JacksonDeserializedSudokuGame(
    private val stringGame: String
): DeserializedSudokuGame {
    override fun state(): GameState =
        ObjectMapper().registerModule(KotlinModule()).readValue(stringGame, GameState::class.java)

}