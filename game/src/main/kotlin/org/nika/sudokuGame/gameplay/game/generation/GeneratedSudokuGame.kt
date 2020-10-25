package org.nika.sudokuGame.gameplay.game.generation

import org.nika.sudokuGame.gameplay.game.SudokuGame

interface GeneratedSudokuGame {
    fun generate(): SudokuGame
}