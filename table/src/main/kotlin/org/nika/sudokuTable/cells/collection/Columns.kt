package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.cells.Cell

class Columns(
    private val source: List<Cell>
) : GroupedSelections() {
    override fun groupedCells(): List<SelectionOfCells> =
        source
            .groupBy { it.location().x }
            .values
            .map { SelectionOfCells(it) }

}