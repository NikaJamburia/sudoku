package org.nika.sudokuService.service

import org.nika.sudokuGame.gameplay.interaction.request.LoadGameRequest
import org.nika.sudokuGame.gameplay.interaction.request.SaveGameRequest
import org.nika.sudokuGame.gameplay.interaction.result.GameStarted
import org.nika.sudokuGame.gameplay.saveload.GameLoader
import org.nika.sudokuGame.gameplay.saveload.GameSaver
import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.nika.sudokuService.game4X4With1Empty
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AdditionalFeaturesServiceTest {
    private val jsonSaver: GameSaver = mockk {
        every { supportedFormat() } returns JSON
        every { save(any()) } returns SavedSudokuGameState(JSON, "{Serialized json game}" )
    }

    private val xmlSaver: GameSaver = mockk {
        every { supportedFormat() } returns XML
        every { save(any()) } returns SavedSudokuGameState(XML, "{Serialized xml game}" )
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
        val service = AdditionalFeaturesService(listOf(jsonSaver, xmlSaver), listOf())

        val resultJson = service.saveGame(saveToJsonRequest)
        assertEquals(JSON, resultJson.savedGameState.serializationFormat)
        assertEquals("{Serialized json game}", resultJson.savedGameState.content)

        val resultXlm = service.saveGame(saveToXmlRequest)
        assertEquals(XML, resultXlm.savedGameState.serializationFormat)
        assertEquals("{Serialized xml game}", resultXlm.savedGameState.content)
    }

    @Test
    fun loadsGameCorrectly() {
        val loadFromJsonRequest = LoadGameRequest(SavedSudokuGameState(JSON, "{Serialized json game}"))
        val loadFromXmlRequest = LoadGameRequest(SavedSudokuGameState(XML, "{Serialized xml game}"))
        val service = AdditionalFeaturesService(listOf(), listOf(jsonLoader, xmlLoader))

        val resultJson = service.loadGame(loadFromJsonRequest)
        assertTrue(resultJson.isSuccessful)
        assertEquals("JSON", resultJson.content.playedTime)
        assertEquals("Game loaded", resultJson.message)

        val resultXml = service.loadGame(loadFromXmlRequest)
        assertTrue(resultXml.isSuccessful)
        assertEquals("XML", resultXml.content.playedTime)
        assertEquals("Game loaded", resultXml.message)
    }

    @Test
    fun startsNewGame() {
        val service = AdditionalFeaturesService(listOf(), listOf())
        val result = service.startNewGame()

        assertTrue(result is GameStarted)
        assertEquals("00:00:00", result.content.playedTime)
        assertEquals(0, result.content.numberOfTurns)
    }

}