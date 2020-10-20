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
import org.springframework.stereotype.Component
import table.generation.MockGeneratedSudokuTable
import table.state.TableState
import java.lang.Exception

@Component
class PersistenceService(
    private val gameSavers: List<GameSaver>,
    private val gameLoaders: List<GameLoader>
) : SudokuPersistance {
    override fun saveGame(request: SaveGameRequest): GameSaved =
        GameSaved(true,
            gameSavers
                .first { it.supportedFormat() == request.format }
                .save(request.gameState))


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