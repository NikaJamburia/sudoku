package org.nika.sudokuWeb.http.controller

import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.request.LoadGameRequest
import org.nika.sudokuInteraction.request.SaveGameRequest
import org.nika.sudokuInteraction.request.SudokuInteractionRequest
import org.nika.sudokuInteraction.result.GameSavingResult
import org.nika.sudokuInteraction.result.NoGameError
import org.nika.sudokuInteraction.result.SudokuInteractionResult
import org.nika.sudokuService.process.SaveLoadProcess
import org.nika.sudokuService.process.SudokuGameProcess
import org.nika.sudokuWeb.http.response.HttpResponseFromGameSavingResult
import org.nika.sudokuWeb.http.response.HttpResponseFromSudokuInteractionResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping("/web/api/sudoku/")
class BasicSudokuController (
    @Autowired private val game: SudokuGameProcess,
    @Autowired private val saveLoad: SaveLoadProcess
) {

    @GetMapping("start-new-game")
    fun startNewGame(): ResponseEntity<SudokuInteractionResult> =
        HttpResponseFromSudokuInteractionResult(game.startNewGame()).response()

    @PostMapping("restart-game")
    fun restartGame(@RequestBody request: SudokuInteractionRequest): ResponseEntity<SudokuInteractionResult> =
        HttpResponseFromSudokuInteractionResult(game.restart(request)).response()

    @PostMapping("fill-cell")
    fun fillCell(@RequestBody request: FillCellRequest): ResponseEntity<SudokuInteractionResult> =
        HttpResponseFromSudokuInteractionResult(game.fillCell(request)).response()




    @PostMapping("save")
    fun saveGame(@RequestBody request: SaveGameRequest): ResponseEntity<GameSavingResult> =
        HttpResponseFromGameSavingResult(saveLoad.saveGame(request)).response()


    @PostMapping("load")
    fun loadGame(@RequestBody request: LoadGameRequest): ResponseEntity<SudokuInteractionResult> =
        HttpResponseFromSudokuInteractionResult(saveLoad.loadGame(request)).response()

}