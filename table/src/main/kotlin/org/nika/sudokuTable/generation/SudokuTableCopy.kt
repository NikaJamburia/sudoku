package org.nika.sudokuTable.generation

import org.nika.sudokuTable.SudokuTable

class SudokuTableCopy (
    private val tableForCopy: SudokuTable
) : GeneratedSudokuTable {
    override fun generate(): SudokuTable = SudokuTableFromState(tableForCopy.internalState()).generate()
}