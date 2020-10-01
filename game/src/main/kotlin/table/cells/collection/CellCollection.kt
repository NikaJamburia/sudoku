package table.cells.collection

import table.CellConflict

interface CellCollection {
    fun findConflicts(): List<CellConflict>
}