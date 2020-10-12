package gameplay.saveload.save

import gameplay.game.GameState
import gameplay.saveload.serialization.SerializationFormat
import gameplay.saveload.serialization.JacksonSerializedSudokuGame
import gameplay.saveload.serialization.SerializedSudokuGame

interface GameSaver {
    fun supportedFormat(): SerializationFormat
    fun save(gameState: GameState): SerializedSudokuGame
}