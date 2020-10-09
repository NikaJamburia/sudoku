package table.generation

import table.SudokuTable

interface SudokuTableGenerator {
    fun generate(): SudokuTable
}