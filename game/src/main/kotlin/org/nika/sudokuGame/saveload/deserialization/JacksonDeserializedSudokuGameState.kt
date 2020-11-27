package org.nika.sudokuGame.saveload.deserialization

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.nika.sudokuInteraction.state.GameState

class JacksonDeserializedSudokuGameState(
    private val stringGame: String
): DeserializedSudokuGame {
    override fun state(): GameState =
        ObjectMapper().registerModule(KotlinModule()).readValue(stringGame, GameState::class.java)

}