package org.nika.sudokuService.service

import org.nika.sudokuGame.gameplay.game.generation.SudokuGameFromState
import org.nika.sudokuGame.gameplay.game.neww.NewSudokuGame
import org.nika.sudokuGame.gameplay.game.neww.SudokuTableGenerationParameters
import org.nika.sudokuService.process.SudokuGameProcess
import org.springframework.stereotype.Component
import org.nika.sudokuGame.table.Coordinates
import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.request.SudokuInteractionRequest
import org.nika.sudokuInteraction.result.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.PropertySource
import java.lang.Exception


@Component
@PropertySource("classpath:sudoku-service.properties")
class SudokuGameService (
    @Autowired
    @Qualifier("sudokuTableGenerationParameters")
    private val sudokuTableGenerationParameters: SudokuTableGenerationParameters
) : SudokuGameProcess {
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

    override fun startNewGame(): SudokuInteractionResult =
        try {
            val game = NewSudokuGame(sudokuTableGenerationParameters).start()
            GameStarted(game.internalState())
        } catch (e: Exception) {
            NoGameError(e.message ?: let { "Unknown error" })
        }
}