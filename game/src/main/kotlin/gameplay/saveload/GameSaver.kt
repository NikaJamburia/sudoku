package gameplay.saveload

import gameplay.game.GameState
import gameplay.saveload.serialization.SerializationFormat

interface GameSaver {
    fun supportedFormat(): SerializationFormat
    fun save(gameState: GameState): SavedSudokuGame
}