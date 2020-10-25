package org.nika.sudokuGame.gameplay.interaction.request

import org.nika.sudokuGame.gameplay.game.GameState

class FillCellRequest (
    val value: Int,
    val coordinateX: Int,
    val coordinateY: Int,
    gameState: GameState,
    timerValue: String
) : SudokuInteractionRequest(gameState, timerValue)