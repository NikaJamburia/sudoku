package gameplay.interaction.request

import gameplay.game.GameState

open class SudokuInteractionRequest (
    val gameState: GameState,
    val timerValue: String
)