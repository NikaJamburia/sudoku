package org.nika.sudokuWeb.http.response

import org.nika.sudokuInteraction.result.GameSavingResult
import org.springframework.http.ResponseEntity

class GameSavingResponseEntity (
    private val gameSavingResult: GameSavingResult
) {
    fun respond(): ResponseEntity<GameSavingResult> {
        return if (gameSavingResult.isSuccessful) {
            ResponseEntity.ok(gameSavingResult)
        } else {
            ResponseEntity.badRequest().body(gameSavingResult)
        }
    }
}