package org.nika.sudokuService.spring.saveload.json


import org.nika.sudokuService.spring.saveload.GameLoader

import org.nika.sudokuGame.saveload.deserialization.JacksonDeserializedSudokuGameState
import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.SavedSudokuGameState

import org.springframework.stereotype.Component

@Component
class JsonGameLoader: GameLoader {
    override fun supportedFormat(): SerializationFormat = SerializationFormat.JSON

    override fun load(savedSudokuGameState: SavedSudokuGameState): GameState =
        JacksonDeserializedSudokuGameState(savedSudokuGameState.content).gameState()

}