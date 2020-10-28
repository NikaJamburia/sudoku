package org.nika.sudokuService.interaction.result

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.table.state.TableState

data class CellFilled (
    val tableState: GameState
): SudokuInteractionResult(true, "Successfully filled cell", tableState)

data class GameWon (
    val gameState: GameState
): SudokuInteractionResult(true, "You won", gameState)

data class Error (
    val errorMsg: String,
    val gameState: GameState
): SudokuInteractionResult(false, errorMsg, gameState)

data class NoGameError (
    val errorMsg: String
): SudokuInteractionResult(false, errorMsg, GameState("", 0, false, TableState(listOf(), listOf(), false, false)))

data class GameStarted (
    val gameState: GameState
): SudokuInteractionResult(true, "New game started", gameState)

data class GameLoaded (
    val gameState: GameState
): SudokuInteractionResult(true, "Game loaded", gameState)