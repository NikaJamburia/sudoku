package gameplay.saveload.deserialization

import gameplay.game.GameState

interface DeserializedSudokuGame {
    fun state(): GameState
}