package org.nika.sudokuTable.generation.backtracking

import org.junit.jupiter.api.Test
import org.nika.sudokuTable.Coordinates
import org.nika.sudokuTable.findCell
import org.nika.sudokuTable.generation.MockGeneratedSudokuTable
import org.nika.sudokuTable.sudokuTableWithTwoSolutions
import kotlin.test.assertEquals

class SolutionsForSudokuTableTest {
    @Test
    fun shouldCountCorrectly() {
        val solutions = SolutionsForSudokuTable(MockGeneratedSudokuTable().generate())
            .findAll()

        assertEquals(1, solutions.size)
    }

    @Test
    fun correctlyCountsSolutionsForTableWithTwoSolutions() {
        val tableWith2Solutions = sudokuTableWithTwoSolutions()
        val solutions = SolutionsForSudokuTable(tableWith2Solutions).findAll()
        assertEquals(2, solutions.size)

        val firstSolutionValues = setOf(
            solutions.first().findCell(8, 2).value,
            solutions.first().findCell(9, 2).value,
            solutions.first().findCell(8, 9).value,
            solutions.first().findCell(9, 9).value
        )

        val secondSolutionValues = setOf(
            solutions.last().findCell(8, 2).value,
            solutions.last().findCell(9, 2).value,
            solutions.last().findCell(8, 9).value,
            solutions.last().findCell(9, 9).value
        )

        assertEquals(2, firstSolutionValues.size)
        assertEquals(2, secondSolutionValues.size)
    }

    @Test
    fun solutionsAreCorrect() {
        val tableWith2Solutions = sudokuTableWithTwoSolutions()
        var solutions = SolutionsForSudokuTable(tableWith2Solutions).findAll()
        assertEquals(2, solutions.size)

        tableWith2Solutions.emptyCell(Coordinates(5, 5))

        solutions = SolutionsForSudokuTable(tableWith2Solutions).findAll()
        assertEquals(2, solutions.size)
        assertEquals(8, solutions.first().findCell(5, 5).value)
        assertEquals(8, solutions.last().findCell(5, 5).value)
    }
}