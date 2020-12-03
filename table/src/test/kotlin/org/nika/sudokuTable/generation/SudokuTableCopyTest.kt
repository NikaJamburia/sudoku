package org.nika.sudokuTable.generation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SudokuTableCopyTest {
    @Test
    fun correctlyCopiesAllStateToNewTableObject() {
        val mockTable = MockGeneratedSudokuTable().generate()
        val copyOfMockTable = SudokuTableCopy(mockTable).generate()

        assertEquals(mockTable.internalState(), copyOfMockTable.internalState())
    }
}