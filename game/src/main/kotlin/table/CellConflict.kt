package table

import table.cells.Cell

class CellConflict (
    private val conflictedCells: MutableList<Cell>
) {
    fun isResolved(): Boolean {
        return conflictedCells.isEmpty()
    }

    fun tryToResolve() {
        conflictedCells
            .groupBy { it.getValue() }
            .values
            .filter { it.size == 1 }
            .forEach { conflictedCells.removeAll(it) }
    }
}