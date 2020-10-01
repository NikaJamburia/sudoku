package table.cells.collection

import table.cells.Cell

class Rows(
    private val source: List<Cell>
) : GroupedSelections() {
    override fun groupCells(): List<SelectionOfCells> =
        source
            .groupBy { it.findLocation().y }
            .values
            .map { SelectionOfCells(it) }

}