package table.state

import table.Coordinates

data class TableState(
    val cells: List<CellState>,
    val conflicts: List<ConflictState>,
    val isFilled: Boolean,
    val isEmpty: Boolean
)

data class CellState (
    val value: Int,
    val coordinates: Coordinates,
    val isOpen: Boolean
)

data class ConflictState (
    val conflictedCells: List<CellState>
)
