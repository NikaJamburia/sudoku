package org.nika.sudokuGame.neww

import org.junit.jupiter.api.Test
import org.nika.sudokuGame.coordinates
import org.nika.sudokuGame.generation.NewSudokuGame
import org.nika.sudokuGame.generation.SudokuTableGenerationParameters
import org.nika.sudokuGame.generation.TableGenerationAlgorithm
import org.nika.sudokuInteraction.state.CellState
import org.nika.sudokuTable.cells.Cell
import org.nika.sudokuTable.cells.ClosedCell
import org.nika.sudokuTable.cells.OpenCell
import org.nika.sudokuTable.cells.collection.Columns
import org.nika.sudokuTable.cells.collection.Rows
import org.nika.sudokuTable.generation.MockGeneratedSudokuTable
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NewSudokuGameTest {

    @Test
    fun startsNewGameWithMockedTable() {
        val params = SudokuTableGenerationParameters(
            TableGenerationAlgorithm.MOCKED,
            0,
            0,
            0
        )
        val newGameState = NewSudokuGame(params).generate().internalState()

        assertEquals(0, newGameState.numberOfTurns)
        assertEquals("00:00:00", newGameState.playedTime)
        assertFalse(newGameState.gameIsWon)
        assertTrue(newGameState.tableState.tableIsEmpty)
        assertEquals(MockGeneratedSudokuTable().generate().internalState(), newGameState.tableState)
    }

    @Test
    fun startsNewGameWithRandomTable() {
        val params = SudokuTableGenerationParameters(
            TableGenerationAlgorithm.RANDOM,
            9,
            9,
            20
        )
        val newGameState = NewSudokuGame(params).generate().internalState()

        assertTrue(newGameState.tableState.tableIsEmpty)
        assertEquals(20, newGameState.tableState.cells.count { !it.cellIsOpen })
        assertEquals(9, Columns(newGameState.tableState.cells.map { stateToCell(it) }).asList().first().content.size)
        assertEquals(9, Rows(newGameState.tableState.cells.map { stateToCell(it) }).asList().first().content.size)
    }

    private fun stateToCell(cellState: CellState): Cell =
        if (cellState.cellIsOpen) {
            OpenCell(cellState.value,  cellState.coordinates())
        } else {
            ClosedCell(cellState.value,  cellState.coordinates())
        }

}