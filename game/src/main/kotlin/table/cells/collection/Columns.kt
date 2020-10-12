package table.cells.collection

import table.cells.Cell

class Columns(
    private val source: List<Cell>
) : GroupedSelections() {
    override fun groupedCells(): List<SelectionOfCells> =
        source
            .groupBy { it.findLocation().x }
            .values
            .map { SelectionOfCells(it) }

}