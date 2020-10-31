package org.nika.sudokuInteraction.request

import org.nika.sudokuInteraction.state.SavedSudokuGameState

data class LoadGameRequest (val savedSudokuGameState: SavedSudokuGameState)