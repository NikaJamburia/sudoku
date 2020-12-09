package org.nika.sudokuWeb.http.response

import org.springframework.http.ResponseEntity

interface SudokuWebResponse<T> {
    fun respond(): ResponseEntity<T>
}