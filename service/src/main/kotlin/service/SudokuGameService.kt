package service

import gameplay.game.generation.SudokuGameFromState
import gameplay.interaction.request.FillCellRequest
import gameplay.interaction.request.SudokuInteractionRequest
import gameplay.interaction.result.*
import gameplay.process.SudokuGameProcess
import org.springframework.stereotype.Component
import table.Coordinates
import java.lang.Exception

@Component
class SudokuGameService() : SudokuGameProcess {
    override fun fillCell(request: FillCellRequest): SudokuInteractionResult {
        try {
            val game = SudokuGameFromState(request.gameState).generate()
            val newGameState = game.fillCell(request.value, Coordinates(request.coordinateX, request.coordinateY), request.timerValue)
            if (newGameState.gameIsWon) {
                return GameWon(newGameState)
            }
            return CellFilled(newGameState)
        } catch (e: Exception) {
            return Error(e.message ?: let { "Unknown error" }, request.gameState)
        }
    }

    override fun restart(request: SudokuInteractionRequest): SudokuInteractionResult =
        try {
            val game = SudokuGameFromState(request.gameState).generate()
            GameStarted(game.restart())
        } catch (e: Exception) {
            Error(e.message ?: let { "Unknown error" }, request.gameState)
        }
}