package org.nika.sudokuGame.table.cells.collection

import org.nika.sudokuGame.table.cells.Cell

class Rows(
    private val source: List<Cell>
) : GroupedSelections() {
    override fun groupedCells(): List<SelectionOfCells> =
        source
            .groupBy { it.location().y }
            .values
            .map { SelectionOfCells(it) }

}