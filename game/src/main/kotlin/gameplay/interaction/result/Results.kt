package gameplay.interaction.result

import gameplay.saveload.SerializedSudokuGame
import table.interaction.result.TableState

data class CellFilled (
    val tableState: TableState
): SudokuInteractionResult(true, "Successfully filled cell", tableState)

data class GameWon (
    val tableState: TableState
): SudokuInteractionResult(true, "You won", tableState)

data class Error (
    val errorMsg: String,
    val tableState: TableState
): SudokuInteractionResult(false, errorMsg, tableState)

data class NewGame (
    val tableState: TableState
): SudokuInteractionResult(true, "New game started", tableState)

data class GameLoaded (
    val tableState: TableState
): SudokuInteractionResult(true, "Game loaded", tableState)

data class GameSaved (
    val savedSuccessfully: Boolean,
    val serializedGame: SerializedSudokuGame
)