package gameplay.game.generation

import gameplay.game.SudokuGame

interface GeneratedSudokuGame {
    fun generate(): SudokuGame
}