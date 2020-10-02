package table

import table.cells.Cell

class CellConflict (
    val conflictedCells: MutableList<Cell>
) {
    fun isResolved(): Boolean {
        return conflictedCells.isEmpty()
    }

    fun reEvaluate() {
        conflictedCells
            .groupBy { it.getValue() }
            .values
            .filter { it.size == 1 }
            .forEach { conflictedCells.removeAll(it) }
    }

    fun sameAs(other: CellConflict): Boolean =
        conflictedCells.all {
            other.conflictedCells.any { otherCell ->
                it.findLocation().sameAs(otherCell.findLocation())
            }
        }

}