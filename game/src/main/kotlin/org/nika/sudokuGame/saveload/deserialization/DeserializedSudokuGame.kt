package org.nika.sudokuGame.saveload.deserialization

import org.nika.sudokuInteraction.state.GameState

interface DeserializedSudokuGame {
    fun gameState(): GameState
}