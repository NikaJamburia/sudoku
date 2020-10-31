package org.nika.sudokuGame.gameplay.saveload

import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.SavedSudokuGameState

interface GameLoader {
    fun supportedFormat(): SerializationFormat
    fun load(savedSudokuGameState: SavedSudokuGameState): GameState
}