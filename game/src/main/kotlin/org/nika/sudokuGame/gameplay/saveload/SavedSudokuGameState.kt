package org.nika.sudokuGame.gameplay.saveload

import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat

data class SavedSudokuGameState (
    val serializationFormat: SerializationFormat,
    val content: String
)