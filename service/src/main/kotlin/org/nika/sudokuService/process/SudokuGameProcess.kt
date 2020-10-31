package org.nika.sudokuService.process

import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.request.SudokuInteractionRequest
import org.nika.sudokuInteraction.result.SudokuInteractionResult

interface SudokuGameProcess {
    fun fillCell(request: FillCellRequest): SudokuInteractionResult
    fun restart(request: SudokuInteractionRequest): SudokuInteractionResult
    fun startNewGame(): SudokuInteractionResult
}