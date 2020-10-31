package org.nika.sudokuGame.gameplay.saveload.deserialization

import org.nika.sudokuInteraction.state.GameState

interface DeserializedSudokuGame {
    fun state(): GameState
}