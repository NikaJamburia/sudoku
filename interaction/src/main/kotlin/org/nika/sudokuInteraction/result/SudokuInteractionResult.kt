package org.nika.sudokuInteraction.result

import org.nika.sudokuInteraction.state.GameState

abstract class SudokuInteractionResult (
    val isSuccessful: Boolean,
    val message: String,
    val content: GameState
)
