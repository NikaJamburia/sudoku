package gameplay.interaction.request

import gameplay.saveload.save.SaveDestination
import gameplay.saveload.serialization.SerializationFormat

class SaveGameRequest (
    val format: SerializationFormat,
    val destination: SaveDestination,
    gameId: String,
    timePlayed: String
) : SudokuInteractionRequest(gameId, timePlayed)