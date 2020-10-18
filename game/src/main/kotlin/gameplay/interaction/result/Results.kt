package gameplay.interaction.result

import gameplay.game.GameState
import gameplay.saveload.serialization.SerializedSudokuGame

data class CellFilled (
    val tableState: GameState
): SudokuInteractionResult(true, "Successfully filled cell", tableState)

data class GameWon (
    val tableState: GameState
): SudokuInteractionResult(true, "You won", tableState)

data class Error (
    val errorMsg: String,
    val gameState: GameState
): SudokuInteractionResult(false, errorMsg, gameState)

data class GameRestarted (
    val gameState: GameState
): SudokuInteractionResult(true, "New game started", gameState)

data class GameLoaded (
    val gameState: GameState
): SudokuInteractionResult(true, "Game loaded", gameState)

data class GameSaved (
    val savedSuccessfully: Boolean,
    val savedGame: SerializedSudokuGame
)