package org.nika.sudokuService.process

import org.nika.sudokuService.interaction.request.LoadGameRequest
import org.nika.sudokuService.interaction.request.SaveGameRequest
import org.nika.sudokuService.interaction.result.GameSaved
import org.nika.sudokuService.interaction.result.GameSavingResult
import org.nika.sudokuService.interaction.result.SudokuInteractionResult

interface SaveLoadProcess {
    fun saveGame(request: SaveGameRequest): GameSavingResult
    fun loadGame(request: LoadGameRequest): SudokuInteractionResult
}