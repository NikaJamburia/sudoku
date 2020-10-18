package gameplay.interaction.result

import gameplay.game.GameState

abstract class SudokuInteractionResult (
    val isSuccessful: Boolean,
    val message: String,
    val content: GameState
)
