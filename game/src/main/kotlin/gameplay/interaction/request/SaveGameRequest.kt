package gameplay.interaction.request

import gameplay.game.GameState
import gameplay.saveload.serialization.SerializationFormat

class SaveGameRequest (
    val format: SerializationFormat,
    gameState: GameState,
    timerValue: String
) : SudokuInteractionRequest(gameState, timerValue)