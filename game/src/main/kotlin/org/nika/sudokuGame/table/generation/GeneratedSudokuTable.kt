package org.nika.sudokuGame.table.generation

import org.nika.sudokuGame.table.SudokuTable

interface GeneratedSudokuTable {
    fun generate(): SudokuTable
}