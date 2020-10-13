package table.generation

import table.SudokuTable

interface GeneratedSudokuTable {
    fun generate(): SudokuTable
}