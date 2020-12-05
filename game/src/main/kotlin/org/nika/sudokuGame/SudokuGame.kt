package org.nika.sudokuGame

import org.nika.sudokuGame.time.GameTime
import org.nika.sudokuGame.time.GameTimeFromString
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuInteraction.state.HasInternalState
import org.nika.sudokuTable.SudokuTable

class SudokuGame(
    private var gameStats: GameStats,
    private var sudokuTable: SudokuTable
): HasInternalState<GameState> {

    fun fillCell(value: Int, coordinateX: Int, coordinateY: Int, timePlayed: String): GameState {
        sudokuTable.fillCell(value, Coordinates(coordinateX, coordinateY))
        gameStats = gameStats.update(GameTimeFromString(timePlayed))
        return internalState()
    }

    fun restart(): GameState {
        gameStats = GameStats(this.gameStats.difficulty, GameTime(0, 0, 0), 0)
        sudokuTable.emptyTable()
        return internalState()
    }

    fun emptyCell(coordinateX: Int, coordinateY: Int, timePlayed: String): GameState {
        gameStats = gameStats.update(GameTimeFromString(timePlayed))
        sudokuTable.emptyCell(Coordinates(coordinateX, coordinateY))
        return internalState()
    }

    override fun internalState(): GameState =
        GameState(gameStats.difficulty, gameStats.playedTime.asString(), gameStats.numberOfTurns, isGameWon(), sudokuTable.internalState())

    private fun isGameWon(): Boolean =
        sudokuTable.internalState().tableIsFull
                && sudokuTable.internalState().conflicts.isEmpty()
}