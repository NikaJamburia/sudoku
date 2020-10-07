package gameplay.interaction.request

import gameplay.saveload.SerializedSudokuGame

class LoadGameRequest  (
    val serializedSudokuGame: SerializedSudokuGame,
    gameId: String,
    timePlayed: String
) : SudokuInteractionRequest(gameId, timePlayed)