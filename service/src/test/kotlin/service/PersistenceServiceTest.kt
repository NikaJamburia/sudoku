package service

import gameplay.game.GameState
import gameplay.interaction.request.LoadGameRequest
import gameplay.interaction.request.SaveGameRequest
import gameplay.saveload.GameLoader
import gameplay.saveload.GameSaver
import gameplay.saveload.SavedSudokuGame
import gameplay.saveload.serialization.SerializationFormat.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import table.Coordinates
import table.cells.NO_VALUE
import table.state.CellState
import table.state.TableState
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PersistenceServiceTest {
    private val jsonSaver: GameSaver = mockk {
        every { supportedFormat() } returns JSON
        every { save(any()) } returns SavedSudokuGame(JSON, "{Serialized json game}" )
    }

    private val xmlSaver: GameSaver = mockk {
        every { supportedFormat() } returns XML
        every { save(any()) } returns SavedSudokuGame(XML, "{Serialized xml game}" )
    }

    private val xmlLoader: GameLoader = mockk {
        every { supportedFormat() } returns XML
        every { load(any()) } returns game4X4With1Empty("XML", 1)
    }

    private val jsonLoader: GameLoader = mockk {
        every { supportedFormat() } returns JSON
        every { load(any()) } returns game4X4With1Empty("JSON", 1)
    }

    @Test
    fun savesGameCorrectly() {
        val game = game4X4With1Empty("00:00:10", 1)
        val saveToJsonRequest = SaveGameRequest(JSON, game, "00:00:20")
        val saveToXmlRequest = SaveGameRequest(XML, game, "00:00:21")
        val service = PersistenceService(listOf(jsonSaver, xmlSaver), listOf())

        val resultJson = service.saveGame(saveToJsonRequest)
        assertEquals(JSON, resultJson.savedGame.serializationFormat)
        assertEquals("{Serialized json game}", resultJson.savedGame.savedContent)

        val resultXlm = service.saveGame(saveToXmlRequest)
        assertEquals(XML, resultXlm.savedGame.serializationFormat)
        assertEquals("{Serialized xml game}", resultXlm.savedGame.savedContent)
    }

    @Test
    fun loadsGameCorrectly() {
        val loadFromJsonRequest = LoadGameRequest(SavedSudokuGame(JSON, "{Serialized json game}"))
        val loadFromXmlRequest = LoadGameRequest(SavedSudokuGame(XML, "{Serialized xml game}"))
        val service = PersistenceService(listOf(), listOf(jsonLoader, xmlLoader))

        val resultJson = service.loadGame(loadFromJsonRequest)
        assertTrue(resultJson.isSuccessful)
        assertEquals("JSON", resultJson.content.playedTime)
        assertEquals("Game loaded", resultJson.message)

        val resultXml = service.loadGame(loadFromXmlRequest)
        assertTrue(resultXml.isSuccessful)
        assertEquals("XML", resultXml.content.playedTime)
        assertEquals("Game loaded", resultXml.message)
    }

    private fun game4X4With1Empty(playedTime: String, turns: Int): GameState {
        val tableState = TableState(
            listOf(
                CellState(1, Coordinates(1, 1), true),
                CellState(2, Coordinates(1, 2), false),
                CellState(3, Coordinates(2, 1), true),
                CellState(NO_VALUE, Coordinates(2, 2), true)
            ), listOf(), false, false)

        return GameState(playedTime, turns, false, tableState)
    }
}