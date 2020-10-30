package org.nika.sudokuWeb.http.controller

import org.nika.sudokuService.interaction.request.FillCellRequest
import org.nika.sudokuService.interaction.request.LoadGameRequest
import org.nika.sudokuService.interaction.request.SaveGameRequest
import org.nika.sudokuService.interaction.request.SudokuInteractionRequest
import org.nika.sudokuService.interaction.result.GameSavingResult
import org.nika.sudokuService.interaction.result.SudokuInteractionResult
import org.nika.sudokuService.process.SaveLoadProcess
import org.nika.sudokuService.process.SudokuGameProcess
import org.nika.sudokuWeb.http.response.HttpResponseFromGameSavingResult
import org.nika.sudokuWeb.http.response.HttpResponseFromSudokuInteractionResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/web/api/sudoku/")
class BasicSudokuController (
    @Autowired private val game: SudokuGameProcess,
    @Autowired private val saveLoad: SaveLoadProcess
) {

    @GetMapping("start-new-game")
    fun startNewGame(): ResponseEntity<SudokuInteractionResult> {
        return HttpResponseFromSudokuInteractionResult(game.startNewGame()).response()
    }

    @PostMapping("restart-game")
    fun restartGame(@RequestBody request: SudokuInteractionRequest): ResponseEntity<SudokuInteractionResult> {
        return HttpResponseFromSudokuInteractionResult(game.restart(request)).response()
    }

    @PostMapping("fill-cell")
    fun fillCell(@RequestBody request: FillCellRequest): ResponseEntity<SudokuInteractionResult> {
        return HttpResponseFromSudokuInteractionResult(game.fillCell(request)).response()
    }

    @PostMapping("save")
    fun saveGame(@RequestBody request: SaveGameRequest): ResponseEntity<GameSavingResult> {
        return HttpResponseFromGameSavingResult(saveLoad.saveGame(request)).response()
    }

    @PostMapping("load")
    fun loadGame(@RequestBody request: LoadGameRequest): ResponseEntity<SudokuInteractionResult> {
        return HttpResponseFromSudokuInteractionResult(saveLoad.loadGame(request)).response()
    }
}