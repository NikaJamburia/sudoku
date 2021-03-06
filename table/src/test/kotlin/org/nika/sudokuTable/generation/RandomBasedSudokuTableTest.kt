package org.nika.sudokuTable.generation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RandomBasedSudokuTableTest {
    @Test
    fun shouldNotGenerateAnyConflictsEver() {
        var numberOfTimesFailed = 0
        var numberOfGeneratedConflicts = 0
        repeat(1000) {
            val randomTableState = RandomBasedSudokuTable(9, 9, 20).generate().internalState()
            assertEquals(81, randomTableState.cells.size)

            if (randomTableState.conflicts.isNotEmpty()) {
                numberOfTimesFailed += 1
                numberOfGeneratedConflicts += randomTableState.conflicts.size
            }
        }
        assertEquals(0, numberOfTimesFailed)
        assertEquals(0, numberOfGeneratedConflicts)

    }
}