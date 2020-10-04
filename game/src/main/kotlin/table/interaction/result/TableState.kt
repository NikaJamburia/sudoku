package table.interaction.result

import table.CellConflict
import table.Coordinates
import table.cells.Cell

data class TableState(
    val cells: List<Cell>,
    val conflicts: List<CellConflict>,
    val isFilled: Boolean
)

data class CellState (
    val value: Int,
    val coordinates: Coordinates
)

class ConflictState (
    val conflictedCells: List<Cell>
)