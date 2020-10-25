package org.nika.sudokuGame.gameplay.saveload

import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat

interface GameSaver {
    fun supportedFormat(): SerializationFormat
    fun save(gameState: GameState): SavedSudokuGameState
}