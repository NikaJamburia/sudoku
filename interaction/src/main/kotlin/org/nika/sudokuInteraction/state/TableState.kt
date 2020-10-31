package org.nika.sudokuInteraction.state

data class TableState(
    val cells: List<CellState>,
    val conflicts: List<ConflictState>,
    val tableIsFull: Boolean,
    val tableIsEmpty: Boolean
)

data class CellState (
    val value: Int,
    val coordinateX: Int,
    val coordinateY: Int,
    val cellIsOpen: Boolean
)

data class ConflictState (
    val conflictedCells: List<CellState>
)
