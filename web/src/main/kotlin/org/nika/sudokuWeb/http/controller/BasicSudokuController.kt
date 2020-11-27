package org.nika.sudokuWeb.http.controller

import org.nika.sudokuInteraction.request.*
import org.nika.sudokuInteraction.result.GameSavingResult
import org.nika.sudokuInteraction.result.SudokuInteractionResult
import org.nika.sudokuService.SaveLoadService
import org.nika.sudokuService.SudokuGameService
import org.nika.sudokuWeb.http.response.GameSavingResponseEntity
import org.nika.sudokuWeb.http.response.SudokuInteractionResponseEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:8080"], maxAge = 3600)
@RequestMapping("/sudoku/web/api/")
class BasicSudokuController (
    @Autowired private val gameService: SudokuGameService,
    @Autowired private val saveLoadService: SaveLoadService
) {

    @GetMapping("start-new-game")
    fun startNewGame(): ResponseEntity<SudokuInteractionResult> =
        SudokuInteractionResponseEntity(gameService.startNewGame()).respond()

    @PostMapping("restart-game")
    fun restartGame(@RequestBody request: SudokuInteractionRequest): ResponseEntity<SudokuInteractionResult> =
        SudokuInteractionResponseEntity(gameService.restart(request)).respond()

    @PostMapping("fill-cell")
    fun fillCell(@RequestBody request: FillCellRequest): ResponseEntity<SudokuInteractionResult> =
        SudokuInteractionResponseEntity(gameService.fillCell(request)).respond()

    @PostMapping("empty-cell")
    fun fillCell(@RequestBody request: EmptyCellRequest): ResponseEntity<SudokuInteractionResult> =
        SudokuInteractionResponseEntity(gameService.emptyCell(request)).respond()

    @PostMapping("save")
    fun saveGame(@RequestBody request: SaveGameRequest): ResponseEntity<GameSavingResult> =
        GameSavingResponseEntity(saveLoadService.saveGame(request)).respond()

    @PostMapping("load")
    fun loadGame(@RequestBody request: LoadGameRequest): ResponseEntity<SudokuInteractionResult> =
        SudokuInteractionResponseEntity(saveLoadService.loadGame(request)).respond()

}