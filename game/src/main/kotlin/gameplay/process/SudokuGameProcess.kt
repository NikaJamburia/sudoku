package gameplay.process

import gameplay.interaction.request.FillCellRequest
import gameplay.interaction.result.SudokuInteractionResult

interface SudokuGameProcess {
    fun fillCell(request: FillCellRequest): SudokuInteractionResult
    fun restart(): SudokuInteractionResult
}