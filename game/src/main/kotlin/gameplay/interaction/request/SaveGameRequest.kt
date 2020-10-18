package gameplay.interaction.request

import gameplay.game.GameState
import gameplay.saveload.save.SaveDestination
import gameplay.saveload.serialization.SerializationFormat

class SaveGameRequest (
    val format: SerializationFormat,
    val destination: SaveDestination,
    gameState: GameState
) : SudokuInteractionRequest(gameState)