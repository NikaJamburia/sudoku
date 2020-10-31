package org.nika.sudokuInteraction.state

import org.nika.sudokuInteraction.enums.SerializationFormat

data class SavedSudokuGameState (
    val serializationFormat: SerializationFormat,
    val content: String
)