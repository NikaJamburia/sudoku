package org.nika.sudokuTable.generation

import org.nika.sudokuTable.SudokuTable

interface GeneratedSudokuTable {
    fun generate(): SudokuTable
}