package org.nika.sudokuService.spring.service


import org.nika.sudokuService.SaveLoadService
import org.nika.sudokuService.spring.saveload.GameLoader
import org.nika.sudokuService.spring.saveload.GameSaver
import org.nika.sudokuInteraction.request.LoadGameRequest
import org.nika.sudokuInteraction.request.SaveGameRequest
import org.nika.sudokuInteraction.result.*
import org.nika.sudokuInteraction.state.GameState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class SaveLoadSpringService(
    @Autowired private val gameSavers: List<GameSaver>,
    @Autowired private val gameLoaders: List<GameLoader>
) : SaveLoadService {
    override fun saveGame(request: SaveGameRequest): GameSavingResult {
        return try {
            val stateWithNewTimerValue = request.gameState.withNewTimerValue(request.timerValue)
            gameSavers
                .firstOrNull { it.supportedFormat() == request.format }
                ?.let { GameSaved(it.save(stateWithNewTimerValue)) }
                ?: SavingError("${request.format} format not supported")
        } catch (e: Exception) {
            SavingError(e.message ?: let { "Unknown error" })
        }
    }

    override fun loadGame(request: LoadGameRequest): SudokuInteractionResult =
        try {
            gameLoaders
                .firstOrNull { it.supportedFormat() == request.savedSudokuGameState.serializationFormat }
                ?.let { GameLoaded(it.load(request.savedSudokuGameState)) }
                ?: NoGameError("${request.savedSudokuGameState.serializationFormat} format not supported")
        } catch (e: Exception) {
            NoGameError(e.message ?: let { "Unknown error" })
        }

    private fun GameState.withNewTimerValue(newValue: String): GameState =
        GameState(newValue, this.numberOfTurns, this.gameIsWon, this.tableState)

}