package gameplay.saveload

import gameplay.game.GameState
import gameplay.saveload.serialization.SerializationFormat

interface GameLoader {
    fun supportedFormat(): SerializationFormat
    fun load(savedSudokuGame: SavedSudokuGame): GameState
}