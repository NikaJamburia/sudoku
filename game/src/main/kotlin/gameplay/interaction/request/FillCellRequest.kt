package gameplay.interaction.request

import gameplay.game.GameState

class FillCellRequest (
    val value: Int,
    val coordinateX: Int,
    val coordinateY: Int,
    gameState: GameState
) : SudokuInteractionRequest(gameState)