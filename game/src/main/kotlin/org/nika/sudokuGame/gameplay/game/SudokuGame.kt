package org.nika.sudokuGame.gameplay.game

import org.nika.sudokuGame.gameplay.game.time.GameTime
import org.nika.sudokuGame.gameplay.game.time.GameTimeFromString
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuGame.table.HasInternalState
import org.nika.sudokuGame.table.SudokuTable
import org.nika.sudokuInteraction.state.GameState

class SudokuGame(
    private var gameStats: GameStats,
    private var sudokuTable: SudokuTable
): HasInternalState<GameState> {

    fun fillCell(value: Int, coordinates: Coordinates, timePlayed: String): GameState {
        sudokuTable.fillCell(value, coordinates)
        gameStats = GameStats(GameTimeFromString(timePlayed), gameStats.numberOfTurns+1)
        return internalState()
    }

    fun restart(): GameState {
        gameStats = GameStats(GameTime(0, 0, 0), 0)
        sudokuTable.empty()
        return internalState()
    }

    override fun internalState(): GameState =
        GameState(gameStats.playedTime.asString(), gameStats.numberOfTurns, isGameWon(), sudokuTable.internalState())

    private fun isGameWon(): Boolean =
        sudokuTable.internalState().tableIsFull
                && sudokuTable.internalState().conflicts.isEmpty()


}