package gameplay.interaction.request

import gameplay.saveload.save.SavedSudokuGame

class LoadGameRequest  (
    val savedSudokuGame: SavedSudokuGame,
    gameId: String,
    timePlayed: String
) : SudokuInteractionRequest(gameId, timePlayed)