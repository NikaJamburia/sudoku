package org.nika.sudokuService.interaction.result

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.table.state.TableState

class CellFilled (
    tableState: GameState
): SudokuInteractionResult(true, "Successfully filled cell", tableState)

data class GameWon (
    val gameState: GameState
): SudokuInteractionResult(true, "You won", gameState)

class Error (
    errorMsg: String,
    gameState: GameState
): SudokuInteractionResult(false, errorMsg, gameState)

class NoGameError (
    errorMsg: String
): SudokuInteractionResult(false, errorMsg, GameState("", 0, false, TableState(listOf(), listOf(), false, false)))

class GameStarted (
    gameState: GameState
): SudokuInteractionResult(true, "New game started", gameState)

class GameLoaded (
    gameState: GameState
): SudokuInteractionResult(true, "Game loaded", gameState)