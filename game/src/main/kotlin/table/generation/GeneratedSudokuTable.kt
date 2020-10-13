package table.generation

import table.SudokuTable

interface GeneratedSudokuTable {
    fun table(): SudokuTable
}