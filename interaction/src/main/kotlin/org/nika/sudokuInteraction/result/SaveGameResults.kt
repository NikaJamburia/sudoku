package org.nika.sudokuInteraction.result

import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.state.SavedSudokuGameState

abstract class GameSavingResult(
    val savedSuccessfully: Boolean,
    val message: String,
    val savedGameState: SavedSudokuGameState
)

class GameSaved (
    savedGame: SavedSudokuGameState
): GameSavingResult(true, "Saved successfully", savedGame)

class SavingError (
    msg: String
): GameSavingResult(false, msg, SavedSudokuGameState(SerializationFormat.JSON, ""))