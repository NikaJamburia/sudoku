package gameplay.game

import gameplay.saveload.save.GameSaver
import gameplay.saveload.serialization.SerializedSudokuGame
import table.Coordinates
import table.HasInternalState
import table.SudokuTable

class SudokuGame(
    private val gameId: String,
    private var gameStats: GameStats,
    private var sudokuTable: SudokuTable
): HasInternalState<GameState> {

    fun fillCell(value: Int, coordinates: Coordinates, timePlayed: String): GameState {
        val tableState = sudokuTable.fillCell(value, coordinates)
        gameStats = GameStats(GameTime.fromString(timePlayed), gameStats.numberOfTurns+1)
        return internalState()
    }

    fun restart(): GameState {
        gameStats = GameStats(GameTime(0, 0, 0), 0)
        sudokuTable.empty()
        return internalState()
    }

    fun save(saver: GameSaver): SerializedSudokuGame = saver.save(internalState())

    override fun internalState(): GameState =
        GameState(gameId, gameStats.playedTime.toString(), gameStats.numberOfTurns, isGameWon(), sudokuTable.internalState())

    private fun isGameWon(): Boolean =
        sudokuTable.internalState().isFilled
                && sudokuTable.internalState().conflicts.isEmpty()


}