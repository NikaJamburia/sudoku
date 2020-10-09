package gameplay.saveload.serialization

import gameplay.game.GameState

interface SudokuSerializer {
    fun gameToString(gameState: GameState) : String
}