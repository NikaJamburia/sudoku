package org.nika.sudokuService.spring.saveload.json


import org.nika.sudokuService.spring.saveload.GameSaver

import org.nika.sudokuGame.saveload.serialization.JacksonSerializedSudokuGameState
import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.SavedSudokuGameState

import org.springframework.stereotype.Component

@Component
class JsonGameSaver: GameSaver {
    override fun supportedFormat(): SerializationFormat = SerializationFormat.JSON
    override fun save(gameState: GameState): SavedSudokuGameState =
        SavedSudokuGameState(SerializationFormat.JSON, JacksonSerializedSudokuGameState(gameState).asString())
}