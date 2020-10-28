package org.nika.sudokuService.interaction.request

import org.nika.sudokuGame.gameplay.game.GameState

open class SudokuInteractionRequest (
    val gameState: GameState,
    val timerValue: String
)