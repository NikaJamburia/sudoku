package org.nika.sudokuGame.gameplay.saveload

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat

interface GameLoader {
    fun supportedFormat(): SerializationFormat
    fun load(savedSudokuGameState: SavedSudokuGameState): GameState
}