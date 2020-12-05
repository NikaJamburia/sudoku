package org.nika.sudokuService

import org.nika.sudokuInteraction.request.EmptyCellRequest
import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.request.StartNewGameRequest
import org.nika.sudokuInteraction.request.SudokuInteractionRequest
import org.nika.sudokuInteraction.result.SudokuInteractionResult

interface SudokuGameService {
    fun fillCell(request: FillCellRequest): SudokuInteractionResult
    fun restart(request: SudokuInteractionRequest): SudokuInteractionResult
    fun startNewGame(request: StartNewGameRequest): SudokuInteractionResult
    fun emptyCell(request: EmptyCellRequest): SudokuInteractionResult
}