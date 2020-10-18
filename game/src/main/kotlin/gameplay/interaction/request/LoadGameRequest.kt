package gameplay.interaction.request

import gameplay.saveload.deserialization.DeserializedSudokuGame

data class LoadGameRequest (val savedSudokuGame: DeserializedSudokuGame)