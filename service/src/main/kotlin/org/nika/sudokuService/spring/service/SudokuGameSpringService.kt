package org.nika.sudokuService.spring.service

import org.nika.sudokuGame.generation.SudokuGameFromState
import org.nika.sudokuGame.generation.NewSudokuGame
import org.nika.sudokuGame.generation.parameters.SudokuDifficultyParameters
import org.nika.sudokuGame.generation.parameters.SudokuTableGenerationParameters
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuService.SudokuGameService
import org.springframework.stereotype.Component
import org.nika.sudokuInteraction.request.EmptyCellRequest
import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.request.StartNewGameRequest
import org.nika.sudokuInteraction.request.SudokuInteractionRequest
import org.nika.sudokuInteraction.result.*
import org.nika.sudokuInteraction.state.GameState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.PropertySource
import java.lang.Exception


@Component
@PropertySource("classpath:sudoku-service.properties")
class SudokuGameSpringService (
    @Autowired
    @Qualifier("sudokuTableGenerationParameters")
    private val sudokuTableGenerationParameters: SudokuTableGenerationParameters,

    @Autowired
    @Qualifier("tableDifficultyConfiguration")
    private val tableDifficultyConfiguration: Map<SudokuDifficulty, Int>
) : SudokuGameService {

    override fun fillCell(request: FillCellRequest): SudokuInteractionResult {
        try {
            val game = SudokuGameFromState(request.gameState).generate()
            val newGameState = game.fillCell(request.value, request.coordinateX, request.coordinateY, request.timerValue)
            if (newGameState.gameIsWon) {
                return GameWon(newGameState)
            }
            return CellFilled(newGameState)
        } catch (e: Exception) {
            return error(e.message , request.gameState)
        }
    }

    override fun restart(request: SudokuInteractionRequest): SudokuInteractionResult =
        try {
            val game = SudokuGameFromState(request.gameState).generate()
            GameStarted(game.restart())
        } catch (e: Exception) {
            error(e.message , request.gameState)
        }

    override fun startNewGame(request: StartNewGameRequest): SudokuInteractionResult =
        try {
            val game = NewSudokuGame(
                sudokuTableGenerationParameters,
                SudokuDifficultyParameters(
                    request.difficulty,
                    tableDifficultyConfiguration[request.difficulty] ?: sudokuTableGenerationParameters.defaultClosedCells
                )
            ).generate()
            GameStarted(game.internalState())
        } catch (e: Exception) {
            NoGameError(e.message ?: let { "Unknown error" })
        }

    override fun emptyCell(request: EmptyCellRequest): SudokuInteractionResult {
       return try {
           val game = SudokuGameFromState(request.gameState).generate()
           CellEmptied(game.emptyCell(request.coordinateX, request.coordinateY, request.timerValue))
        } catch (e: Exception) {
           error(e.message , request.gameState)
        }
    }

    private fun error(msg: String?, gameState: GameState): Error = Error(msg ?: let { "Unknown error" }, gameState)

}