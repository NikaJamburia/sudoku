package gameplay.interaction.request

import gameplay.saveload.SavedSudokuGame

data class LoadGameRequest (val savedSudokuGame: SavedSudokuGame)