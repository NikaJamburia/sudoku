package gameplay

import gameplay.interaction.request.FillCellRequest
import gameplay.interaction.request.SaveGameRequest
import gameplay.interaction.result.CellFilled
import gameplay.interaction.result.Error
import gameplay.interaction.result.GameSaved
import gameplay.interaction.result.SudokuInteractionResult
import org.joda.time.Interval
import table.Coordinates
import table.SudokuTable
import java.lang.IllegalStateException

class SudokuGame(
    private val gameId: String,
    private var playedTime: Interval,
    private var numberOfTurns: Int,
    private var sudokuTable: SudokuTable
): SudokuGameProcess {

    override fun fillCell(request: FillCellRequest): SudokuInteractionResult {
        return try {
            val tableState = sudokuTable.fillCell(request.value, Coordinates(request.coordinateX, request.coordinateY))
            CellFilled(tableState)
        } catch (e: IllegalStateException) {
            val errorMsg = e.message?: let { "Uknown error happened" }
            Error(errorMsg, sudokuTable.internalState() )
        }
    }

    override fun startNewGame(): SudokuInteractionResult {
        TODO("Not yet implemented")
    }

    override fun saveGame(request: SaveGameRequest): GameSaved {
        TODO("Not yet implemented")
    }

    override fun loadGame(): SudokuInteractionResult {
        TODO("Not yet implemented")
    }
}