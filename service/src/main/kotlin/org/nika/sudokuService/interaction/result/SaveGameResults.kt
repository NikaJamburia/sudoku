package org.nika.sudokuService.interaction.result

import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat

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