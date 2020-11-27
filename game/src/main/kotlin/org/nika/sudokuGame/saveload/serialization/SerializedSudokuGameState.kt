package org.nika.sudokuGame.saveload.serialization

interface SerializedSudokuGameState {
    fun asString(): String
}