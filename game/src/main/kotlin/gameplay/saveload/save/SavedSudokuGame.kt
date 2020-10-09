package gameplay.saveload.save

import gameplay.game.GameState
import gameplay.saveload.serialization.SudokuSerializer

class SavedSudokuGame(
    private val gameState: GameState,
    private val sudokuSerializer: SudokuSerializer
) {
    private var stringRepresentation: String = ""

    fun getStringValue(): String {
        if (stringRepresentation.isBlank()) {
            stringRepresentation = sudokuSerializer.gameToString(gameState)
        }
        return stringRepresentation
    }

}