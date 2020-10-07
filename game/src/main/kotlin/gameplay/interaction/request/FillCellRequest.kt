package gameplay.interaction.request

class FillCellRequest (
    val value: Int,
    val coordinateX: Int,
    val coordinateY: Int,
    gameId: String,
    timePlayed: String
) : SudokuInteractionRequest(gameId, timePlayed)