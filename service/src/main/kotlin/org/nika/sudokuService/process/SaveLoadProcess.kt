package org.nika.sudokuService.process

import org.nika.sudokuInteraction.request.LoadGameRequest
import org.nika.sudokuInteraction.request.SaveGameRequest
import org.nika.sudokuInteraction.result.GameSavingResult
import org.nika.sudokuInteraction.result.SudokuInteractionResult

interface SaveLoadProcess {
    fun saveGame(request: SaveGameRequest): GameSavingResult
    fun loadGame(request: LoadGameRequest): SudokuInteractionResult
}