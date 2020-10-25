package org.nika.sudokuService.saveload.json

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.saveload.GameSaver
import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.serialization.JacksonSerializedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat.*
import org.springframework.stereotype.Component

@Component
class JsonGameSaver: GameSaver {
    override fun supportedFormat(): SerializationFormat = JSON
    override fun save(gameState: GameState): SavedSudokuGameState =
        SavedSudokuGameState(JSON, JacksonSerializedSudokuGameState(gameState).asString())
}