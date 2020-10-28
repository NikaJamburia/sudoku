package org.nika.sudokuService.interaction.request

import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState

data class LoadGameRequest (val savedSudokuGameState: SavedSudokuGameState)