package org.nika.sudokuService.spring.service

import org.nika.sudokuService.GameLoader
import org.nika.sudokuService.GameSaver
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SerializationFormat.JSON
import org.nika.sudokuInteraction.enums.SerializationFormat.XML
import org.nika.sudokuInteraction.request.LoadGameRequest
import org.nika.sudokuInteraction.request.SaveGameRequest
import org.nika.sudokuInteraction.result.NoGameError
import org.nika.sudokuInteraction.result.SavingError
import org.nika.sudokuInteraction.state.SavedSudokuGameState
import org.nika.sudokuService.game4X4With1Empty
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SaveLoadSpringServiceTest {

    @Test
    fun savesGameCorrectly() {
        val game = game4X4With1Empty("00:00:10", 1)
        val saveToJsonRequest = SaveGameRequest(JSON, game, "00:00:20")
        val saveToXmlRequest = SaveGameRequest(XML, game, "00:00:21")
        val service = SaveLoadSpringService(listOf(jsonSaver, xmlSaver), listOf())

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
        val service = SaveLoadSpringService(listOf(), listOf(jsonLoader, xmlLoader))

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
    fun correctlyHandlesErrorOnSaving() {
        val game = game4X4With1Empty("00:00:10", 1)
        val saveToJsonRequest = SaveGameRequest(JSON, game, "00:00:20")
        val service = SaveLoadSpringService(listOf(xmlSaver), listOf())

        val result = service.saveGame(saveToJsonRequest)
        assertTrue(result is SavingError)
        assertEquals("JSON format not supported", result.message)

        val service2 = SaveLoadSpringService(listOf(jsonSaverWithException), listOf())
        val result2 = service2.saveGame(saveToJsonRequest)

        assertTrue(result2 is SavingError)
        assertEquals("serialization error", result2.message)
    }

    @Test
    fun correctlyHandlesErrorOnLoading() {
        val loadFromXmlRequest = LoadGameRequest(SavedSudokuGameState(JSON, "{Serialized xml game}"))
        val service = SaveLoadSpringService(listOf(), listOf(xmlLoader))

        val result = service.loadGame(loadFromXmlRequest)
        assertTrue(result is NoGameError)
        assertEquals("JSON format not supported", result.message)

        val service2 = SaveLoadSpringService(listOf(), listOf(jsonLoaderWithException))
        val result2 = service2.loadGame(loadFromXmlRequest)

        assertTrue(result2 is NoGameError)
        assertEquals("parsing error", result2.message)
    }

    private val jsonSaver: GameSaver = mockk {
        every { supportedFormat() } returns JSON
        every { save(any()) } returns SavedSudokuGameState(JSON, "{Serialized json game}" )
    }

    private val jsonSaverWithException: GameSaver = mockk {
        every { supportedFormat() } returns JSON
        every { save(any()) } throws IllegalStateException("serialization error")
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

    private val jsonLoaderWithException: GameLoader = mockk {
        every { supportedFormat() } returns JSON
        every { load(any()) } throws IllegalStateException("parsing error")
    }

}