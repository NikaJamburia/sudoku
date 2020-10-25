package org.nika.sudokuGame.gameplay.interaction.request

import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState

data class LoadGameRequest (val savedSudokuGameState: SavedSudokuGameState)