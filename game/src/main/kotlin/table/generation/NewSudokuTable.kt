package table.generation

import table.SudokuTable

class NewSudokuTable(
    private val cellsOnXAxis: Int,
    private val cellsOnYAxis: Int,
    private val numberOfClosedCells: Int
): GeneratedSudokuTable {
    override fun table(): SudokuTable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}