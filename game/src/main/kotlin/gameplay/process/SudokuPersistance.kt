package gameplay.process

import gameplay.interaction.request.LoadGameRequest
import gameplay.interaction.request.SaveGameRequest
import gameplay.interaction.result.GameSaved
import gameplay.interaction.result.SudokuInteractionResult

interface SudokuPersistance {
    fun saveGame(request: SaveGameRequest): GameSaved
    fun loadGame(request: LoadGameRequest): SudokuInteractionResult
    fun startNewGame(): SudokuInteractionResult
}