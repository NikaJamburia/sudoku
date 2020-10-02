package table

import table.cells.Cell
import table.cells.collection.Columns
import table.cells.collection.Rows
import table.cells.collection.SelectionOfCells
import java.lang.IllegalStateException

class SudokuTable(
    private val bigCells: List<SelectionOfCells>,
    val conflicts: MutableList<CellConflict>
) {
    fun fillCell(value: Int, coordinates: Coordinates) {
        allCells()
            .find { it.findLocation().sameAs(coordinates) }
            ?.let {
                it.fillValue(value)
                resolveConflicts()
                findNewConflicts()
            }
            ?: let { throw IllegalStateException("Cell not found") }
    }

    fun isFilled(): Boolean =
        !allCells().any { it.isEmpty() }

    private fun findResolvedConflicts(): List<CellConflict> {
       return conflicts.filter { it.isResolved() }
    }

    private fun allCells(): List<Cell> = bigCells.flatMap { it.content }

    private fun findNewConflicts() {
        val rowConflicts = Rows(allCells()).findConflicts()
        val columnConflicts = Columns(allCells()).findConflicts()
        val bigCellConflicts = bigCells.map { it.findConflicts() }.flatten()

        rowConflicts.forEach { conflicts.add(it) }
        columnConflicts.forEach { conflicts.add(it) }
        bigCellConflicts.forEach { conflicts.add(it) }
    }

    private fun resolveConflicts() {
        conflicts.forEach { it.reEvaluate() }
        conflicts.removeAll (findResolvedConflicts())
    }

}