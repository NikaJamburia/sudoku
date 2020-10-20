package gameplay.saveload

import gameplay.saveload.serialization.SerializationFormat

data class SavedSudokuGame (
    val serializationFormat: SerializationFormat,
    val savedContent: String
)