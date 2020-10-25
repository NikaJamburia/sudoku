package org.nika.sudokuGame.gameplay.saveload.deserialization

import org.nika.sudokuGame.gameplay.game.GameState

interface DeserializedSudokuGame {
    fun state(): GameState
}