package gameplay.interaction.result

import table.interaction.result.TableState

abstract class SudokuInteractionResult (
    val isSuccessful: Boolean,
    val message: String,
    val content: TableState
)
