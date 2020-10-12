package gameplay.saveload.load

import gameplay.game.SudokuGame

interface GameLoader {
    fun load(): SudokuGame
}