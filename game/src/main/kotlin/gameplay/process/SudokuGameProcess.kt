package gameplay.process

import gameplay.interaction.request.FillCellRequest
import gameplay.interaction.request.SaveGameRequest
import gameplay.interaction.result.GameSaved
import gameplay.interaction.result.SudokuInteractionResult

interface SudokuGameProcess {
    fun fillCell(request: FillCellRequest): SudokuInteractionResult
    fun saveGame(request: SaveGameRequest): GameSaved
    fun loadGame(): SudokuInteractionResult
    fun restart(): SudokuInteractionResult
}