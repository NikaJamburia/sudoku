package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.cells.Cell

class Rows(
    private val source: List<Cell>
) : GroupedSelections() {
    override fun groupedCells(): List<SelectionOfCells> =
        source
            .groupBy { it.location().y }
            .values
            .map { SelectionOfCells(it) }

}