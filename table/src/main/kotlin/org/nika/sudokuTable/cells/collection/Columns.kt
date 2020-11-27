package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.cells.Cell

class Columns(
    private val source: List<Cell>
) : GroupedCells() {
    override fun asList(): List<SelectionOfCells> =
        source
            .groupBy { it.location().x }
            .values
            .map { SelectionOfCells(it) }

}