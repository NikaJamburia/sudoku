package gameplay.interaction.result

import gameplay.game.GameState
import gameplay.saveload.SavedSudokuGame
import gameplay.saveload.serialization.SerializedSudokuGame

data class CellFilled (
    val tableState: GameState
): SudokuInteractionResult(true, "Successfully filled cell", tableState)

data class GameWon (
    val gameState: GameState
): SudokuInteractionResult(true, "You won", gameState)

data class Error (
    val errorMsg: String,
    val gameState: GameState
): SudokuInteractionResult(false, errorMsg, gameState)

data class GameStarted (
    val gameState: GameState
): SudokuInteractionResult(true, "New game started", gameState)

data class GameLoaded (
    val gameState: GameState
): SudokuInteractionResult(true, "Game loaded", gameState)

data class GameSaved (
    val savedSuccessfully: Boolean,
    val savedGame: SavedSudokuGame
)