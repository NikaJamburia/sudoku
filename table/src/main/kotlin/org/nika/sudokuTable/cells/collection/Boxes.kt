package org.nika.sudokuTable.cells.collection

import org.nika.sudokuTable.cells.Cell
import kotlin.math.max

class Boxes(
    private val cells: List<Cell>
) : GroupedCells() {
    override fun asList(): List<SelectionOfCells> {
        val biggestXBorder = calculateBiggestBorderValue(maxXCoordinate())
        val biggestYBorder = calculateBiggestBorderValue(maxYCoordinate())

        val boxUpperBordersX = (1..biggestXBorder).filter { it.canBeDividedBy(3) }
        val boxUpperBordersY = (1..biggestYBorder).filter { it.canBeDividedBy(3) }

        return boxUpperBordersX.map { x ->
            boxUpperBordersY.map { y ->
                SelectionOfCells(
                    cells.filter {
                        cellFitsIntoBorders(it, y, x)
                    }
                )
            }
        }.flatten()

    }

    private fun calculateBiggestBorderValue(maxCoordinate: Int): Int {
        val threeOrGreater = max(3, maxCoordinate)
        return if (threeOrGreater.canBeDividedBy(3)) {
            threeOrGreater
        } else {
            threeOrGreater.nextIntDividedBy(3)
        }
    }

    private fun cellFitsIntoBorders(it: Cell, y: Int, x: Int) =
        (it.location().y <= y && it.location().y > y - 3)
                && (it.location().x <= x && it.location().x > x - 3)

    private fun maxYCoordinate() = cells.map { it.location().y }.max()!!
    private fun maxXCoordinate() = cells.map { it.location().x }.max()!!
    private fun Int.canBeDividedBy(other: Int): Boolean = this % other == 0
    private fun Int.nextIntDividedBy(other: Int): Int = this + (other - (this % other))

}

