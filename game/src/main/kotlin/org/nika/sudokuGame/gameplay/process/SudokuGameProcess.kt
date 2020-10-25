package org.nika.sudokuGame.gameplay.process

import org.nika.sudokuGame.gameplay.interaction.request.FillCellRequest
import org.nika.sudokuGame.gameplay.interaction.request.SudokuInteractionRequest
import org.nika.sudokuGame.gameplay.interaction.result.SudokuInteractionResult

interface SudokuGameProcess {
    fun fillCell(request: FillCellRequest): SudokuInteractionResult
    fun restart(request: SudokuInteractionRequest): SudokuInteractionResult
}