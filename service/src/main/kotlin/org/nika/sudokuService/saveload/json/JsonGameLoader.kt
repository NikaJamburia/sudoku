package org.nika.sudokuService.saveload.json

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.saveload.GameLoader
import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.deserialization.JacksonDeserializedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat.*
import org.springframework.stereotype.Component

@Component
class JsonGameLoader: GameLoader {
    override fun supportedFormat(): SerializationFormat = JSON

    override fun load(savedSudokuGameState: SavedSudokuGameState): GameState =
        JacksonDeserializedSudokuGameState(savedSudokuGameState.content).state()

}