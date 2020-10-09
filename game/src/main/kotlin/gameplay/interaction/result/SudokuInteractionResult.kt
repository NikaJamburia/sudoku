package gameplay.interaction.result

import table.state.TableState

abstract class SudokuInteractionResult (
    val isSuccessful: Boolean,
    val message: String,
    val content: TableState
)
