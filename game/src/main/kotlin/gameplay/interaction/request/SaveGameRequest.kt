package gameplay.interaction.request

class SaveGameRequest (
    val format: String,
    val destination: String,
    gameId: String,
    timePlayed: String
) : SudokuInteractionRequest(gameId, timePlayed)