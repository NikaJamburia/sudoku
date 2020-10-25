package org.nika.sudokuService.service

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.game.GameStats
import org.nika.sudokuGame.gameplay.game.SudokuGame
import org.nika.sudokuGame.gameplay.game.time.GameTime
import org.nika.sudokuGame.gameplay.interaction.request.LoadGameRequest
import org.nika.sudokuGame.gameplay.interaction.request.SaveGameRequest
import org.nika.sudokuGame.gameplay.interaction.result.*
import org.nika.sudokuGame.gameplay.process.AdditionalFeatures
import org.nika.sudokuGame.gameplay.saveload.GameLoader
import org.nika.sudokuGame.gameplay.saveload.GameSaver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.nika.sudokuGame.table.generation.MockGeneratedSudokuTable
import org.nika.sudokuGame.table.state.TableState
import org.springframework.context.annotation.PropertySource
import java.lang.Exception

@Service
@PropertySource("classpath:sudoku-service.properties")
class AdditionalFeaturesService(
    @Autowired private val gameSavers: List<GameSaver>,
    @Autowired private val gameLoaders: List<GameLoader>
) : AdditionalFeatures {
    override fun saveGame(request: SaveGameRequest): GameSaved {
        val stateWithNewTimerValue = request.gameState.withNewTimerValue(request.timerValue)
        return GameSaved(true,
            gameSavers
                .first { it.supportedFormat() == request.format }
                .save(stateWithNewTimerValue))
    }

    override fun loadGame(request: LoadGameRequest): SudokuInteractionResult =
        GameLoaded(
            gameLoaders
                .first { it.supportedFormat() == request.savedSudokuGameState.serializationFormat }
                .load(request.savedSudokuGameState))

    override fun startNewGame(): SudokuInteractionResult =
        try {
            val table = MockGeneratedSudokuTable().generate()
            val game = SudokuGame(GameStats(GameTime(0, 0, 0), 0), table)
            GameStarted(game.internalState())
        } catch (e: Exception) {
            Error(
                e.message ?: let { "Unknown error" },
                GameState("", 0, false, TableState(listOf(), listOf(), false, false))
            )
        }

    private fun GameState.withNewTimerValue(newValue: String): GameState =
        GameState(newValue, this.numberOfTurns, this.gameIsWon, this.tableState)

}