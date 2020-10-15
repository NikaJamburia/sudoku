package table.generation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NewSudokuTableTest {
    @Test
    fun test() {
        val table = NewSudokuTable(9, 9, 10).generate()
        val state = table.internalState()
        assertEquals(81, state.cells.size)
        assertEquals(0, state.conflicts.size)
    }
}