package org.nika.sudokuGame.gameplay.interaction.request

import org.nika.sudokuGame.gameplay.game.GameState

open class SudokuInteractionRequest (
    val gameState: GameState,
    val timerValue: String
)