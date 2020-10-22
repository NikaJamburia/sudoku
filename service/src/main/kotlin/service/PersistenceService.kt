package service

import gameplay.game.GameState
import gameplay.game.GameStats
import gameplay.game.SudokuGame
import gameplay.game.time.GameTime
import gameplay.interaction.request.LoadGameRequest
import gameplay.interaction.request.SaveGameRequest
import gameplay.interaction.result.*
import gameplay.process.SudokuPersistance
import gameplay.saveload.GameLoader
import gameplay.saveload.GameSaver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import table.generation.MockGeneratedSudokuTable
import table.state.TableState
import java.lang.Exception

@Component
class PersistenceService(
    @Autowired private val gameSavers: List<GameSaver>,
    @Autowired private val gameLoaders: List<GameLoader>
) : SudokuPersistance {
    override fun saveGame(request: SaveGameRequest): GameSaved {
        val stateWithNewTimerValue = GameState(request.timerValue, request.gameState.numberOfTurns, request.gameState.gameIsWon, request.gameState.tableState)
        return GameSaved(true,
            gameSavers
                .first { it.supportedFormat() == request.format }
                .save(stateWithNewTimerValue))
    }



    override fun loadGame(request: LoadGameRequest): SudokuInteractionResult =
        GameLoaded(
            gameLoaders
                .first { it.supportedFormat() == request.savedSudokuGame.serializationFormat }
                .load(request.savedSudokuGame))

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


}