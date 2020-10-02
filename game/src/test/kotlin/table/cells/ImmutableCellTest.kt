package table.cells

import org.junit.Assert.assertThrows
import org.junit.jupiter.api.Test
import table.Coordinates
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ImmutableCellTest {
    @Test
    fun changingValueIsNotPossible() {
        val immutableCell = ImmutableCell(1, Coordinates(1, 1))
        assertThrows(IllegalStateException::class.java) { immutableCell.fillValue(1) }
        assertThrows(IllegalStateException::class.java) { immutableCell.empty() }
    }

    @Test
    fun canNotBeInitializedWithoutValue() {
        assertThrows(IllegalArgumentException::class.java) { ImmutableCell(NO_VALUE, Coordinates(1, 1)) }
    }
}