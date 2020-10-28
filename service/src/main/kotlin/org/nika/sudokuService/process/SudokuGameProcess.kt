package org.nika.sudokuService.process

import org.nika.sudokuService.interaction.request.FillCellRequest
import org.nika.sudokuService.interaction.request.SudokuInteractionRequest
import org.nika.sudokuService.interaction.result.SudokuInteractionResult

interface SudokuGameProcess {
    fun fillCell(request: FillCellRequest): SudokuInteractionResult
    fun restart(request: SudokuInteractionRequest): SudokuInteractionResult
    fun startNewGame(): SudokuInteractionResult
}