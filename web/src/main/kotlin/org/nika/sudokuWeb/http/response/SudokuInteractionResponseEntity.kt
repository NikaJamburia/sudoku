package org.nika.sudokuWeb.http.response

import org.nika.sudokuInteraction.result.SudokuInteractionResult
import org.springframework.http.ResponseEntity

class SudokuInteractionResponseEntity (
    private val sudokuInteractionResult: SudokuInteractionResult
) {
    fun response(): ResponseEntity<SudokuInteractionResult> {
        return if (sudokuInteractionResult.isSuccessful) {
            ResponseEntity.ok(sudokuInteractionResult)
        } else {
            ResponseEntity.badRequest().body(sudokuInteractionResult)
        }
    }
}