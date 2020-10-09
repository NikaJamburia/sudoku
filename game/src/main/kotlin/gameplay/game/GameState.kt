package gameplay.game

import table.state.TableState

data class GameState (
    val gameId: String,
    val playedTime: String,
    val numberOfTurns: Int,
    val gameIsWon: Boolean,
    val tableState: TableState
)