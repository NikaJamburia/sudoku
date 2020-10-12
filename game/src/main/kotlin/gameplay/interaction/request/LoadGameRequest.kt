package gameplay.interaction.request

import gameplay.saveload.serialization.JacksonSerializedSudokuGame

class LoadGameRequest  (
    val savedSudokuGame: JacksonSerializedSudokuGame,
    gameId: String,
    timePlayed: String
) : SudokuInteractionRequest(gameId, timePlayed)