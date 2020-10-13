package table.state

import table.Coordinates

data class TableState(
    val cells: List<CellState>,
    val conflicts: List<ConflictState>,
    val tableIsFull: Boolean,
    val tableIsEmpty: Boolean
)

data class CellState (
    val value: Int,
    val coordinates: Coordinates,
    val cellIsOpen: Boolean
)

data class ConflictState (
    val conflictedCells: List<CellState>
)
