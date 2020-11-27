package org.nika.sudokuService.spring.saveload

import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.SavedSudokuGameState

interface GameSaver {
    fun supportedFormat(): SerializationFormat
    fun save(gameState: GameState): SavedSudokuGameState
}