package org.nika.sudokuService.service

import org.nika.sudokuService.interaction.request.LoadGameRequest
import org.nika.sudokuService.interaction.request.SaveGameRequest
import org.nika.sudokuGame.gameplay.saveload.GameLoader
import org.nika.sudokuGame.gameplay.saveload.GameSaver
import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.nika.sudokuService.game4X4With1Empty
import org.nika.sudokuService.interaction.result.NoGameError
import org.nika.sudokuService.interaction.result.SavingError
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SaveLoadServiceTest {

    @Test
    fun savesGameCorrectly() {
        val game = game4X4With1Empty("00:00:10", 1)
        val saveToJsonRequest = SaveGameRequest(JSON, game, "00:00:20")
        val saveToXmlRequest = SaveGameRequest(XML, game, "00:00:21")
        val service = SaveLoadService(listOf(jsonSaver, xmlSaver), listOf())

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
        val service = SaveLoadService(listOf(), listOf(jsonLoader, xmlLoader))

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
        val service = SaveLoadService(listOf(xmlSaver), listOf())

        val result = service.saveGame(saveToJsonRequest)
        assertTrue(result is SavingError)
        assertEquals("JSON format not supported", result.message)

        val service2 = SaveLoadService(listOf(jsonSaverWithException), listOf())
        val result2 = service2.saveGame(saveToJsonRequest)

        assertTrue(result2 is SavingError)
        assertEquals("serialization error", result2.message)
    }

    @Test
    fun correctlyHandlesErrorOnLoading() {
        val loadFromXmlRequest = LoadGameRequest(SavedSudokuGameState(JSON, "{Serialized xml game}"))
        val service = SaveLoadService(listOf(), listOf(xmlLoader))

        val result = service.loadGame(loadFromXmlRequest)
        assertTrue(result is NoGameError)
        assertEquals("JSON format not supported", result.message)

        val service2 = SaveLoadService(listOf(), listOf(jsonLoaderWithException))
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