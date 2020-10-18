package table.generation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MockGeneratedSudokuTableTest {
    @Test
    fun shouldContainCorrectAmountOfClosedCellsAndBeValidTable() {
        val mockTableState = MockGeneratedSudokuTable().generate().internalState()
        assertEquals(81, mockTableState.cells.size)
        assertEquals(32, mockTableState.cells.filter { !it.cellIsOpen }.size)
        assertEquals(0, mockTableState.conflicts.size)
    }
}