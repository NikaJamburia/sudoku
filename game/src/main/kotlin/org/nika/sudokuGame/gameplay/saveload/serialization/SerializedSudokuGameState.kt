package org.nika.sudokuGame.gameplay.saveload.serialization

interface SerializedSudokuGameState {
    fun asString(): String
}