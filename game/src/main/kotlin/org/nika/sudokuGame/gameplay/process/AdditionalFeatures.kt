package org.nika.sudokuGame.gameplay.process

import org.nika.sudokuGame.gameplay.interaction.request.LoadGameRequest
import org.nika.sudokuGame.gameplay.interaction.request.SaveGameRequest
import org.nika.sudokuGame.gameplay.interaction.result.GameSaved
import org.nika.sudokuGame.gameplay.interaction.result.SudokuInteractionResult

interface AdditionalFeatures {
    fun saveGame(request: SaveGameRequest): GameSaved
    fun loadGame(request: LoadGameRequest): SudokuInteractionResult
    fun startNewGame(): SudokuInteractionResult
}