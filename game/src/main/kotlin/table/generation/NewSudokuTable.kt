package table.generation

import table.Coordinates
import table.SudokuTable
import table.cells.Cell
import table.cells.ClosedCell
import table.cells.NO_VALUE
import table.cells.OpenCell
import table.cells.collection.Boxes
import table.cells.collection.SelectionOfCells
import java.time.LocalDateTime
import kotlin.random.Random

class NewSudokuTable(
    private val cellsOnXAxis: Int,
    private val cellsOnYAxis: Int,
    private val numberOfClosedCells: Int
): GeneratedSudokuTable {
    private val closedCells = createClosedCells()


    override fun generate(): SudokuTable {
        val cells = (1..cellsOnXAxis).map { x->
            (1..cellsOnYAxis).map {y ->
                createCell(Coordinates(x, y))
            }
        }.flatten()


        return SudokuTable(Boxes(cells), listOf())
    }

    private fun createCell(coordinates: Coordinates): Cell =
        closedCells.find { it.location().sameAs(coordinates) }
            ?: OpenCell(NO_VALUE, coordinates)

    private fun createClosedCells(): List<ClosedCell> {
        val initialCells = (1..numberOfClosedCells)
            .map { ClosedCell(randomCellValue(), Coordinates(randomX(), ramdonY())) }

        val conflicts = SelectionOfCells(initialCells).findConflicts()
        return listOf()
    }


    private fun randomCellValue(): Int = Random(LocalDateTime.now().minute).nextInt(9)
    private fun randomX(): Int = Random(LocalDateTime.now().minute).nextInt(cellsOnXAxis)
    private fun ramdonY(): Int = Random(LocalDateTime.now().minute).nextInt(cellsOnYAxis)

}