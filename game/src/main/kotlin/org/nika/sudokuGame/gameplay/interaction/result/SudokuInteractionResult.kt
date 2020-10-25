package org.nika.sudokuGame.gameplay.interaction.result

import org.nika.sudokuGame.gameplay.game.GameState

abstract class SudokuInteractionResult (
    val isSuccessful: Boolean,
    val message: String,
    val content: GameState
)
