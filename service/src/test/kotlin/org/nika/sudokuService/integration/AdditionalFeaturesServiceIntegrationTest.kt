package org.nika.sudokuService.integration

import org.nika.sudokuService.SudokuServiceApplication
import org.junit.jupiter.api.Test
import org.nika.sudokuGame.gameplay.interaction.request.LoadGameRequest
import org.nika.sudokuGame.gameplay.interaction.request.SaveGameRequest
import org.nika.sudokuGame.gameplay.interaction.result.GameStarted
import org.nika.sudokuGame.gameplay.saveload.SavedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.deserialization.JacksonDeserializedSudokuGameState
import org.nika.sudokuGame.gameplay.saveload.serialization.SerializationFormat.*
import org.nika.sudokuService.game4X4With1Empty
import org.nika.sudokuService.service.AdditionalFeaturesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@SpringBootTest(classes = [SudokuServiceApplication::class])
class AdditionalFeaturesServiceIntegrationTest (
    @Autowired private val additionalFeaturesService: AdditionalFeaturesService
) {

    @Test
    fun startsGame() {
        val result = additionalFeaturesService.startNewGame()

        assertTrue(result is GameStarted)
        assertEquals("00:00:00", result.content.playedTime)
        assertEquals(0, result.content.numberOfTurns)
    }

    @Test
    fun loadsGame() {
        val savedJson = this::class.java.classLoader.getResource("game4X4With1Empty_JSON_pretty.json").readText()
        val result = additionalFeaturesService.loadGame(LoadGameRequest(SavedSudokuGameState(JSON, savedJson)))

        assertEquals( "00:02:01", result.content.playedTime)
        assertEquals(5, result.content.numberOfTurns)
        assertFalse(result.content.gameIsWon)
        assertEquals(0, result.content.tableState.conflicts.size)
        assertEquals(3, result.content.tableState.cells.count { it.cellIsOpen })
        assertEquals(1, result.content.tableState.cells.count { !it.cellIsOpen })
    }

    @Test
    fun savesGame() {
        val expectedJson = this::class.java.classLoader.getResource("game4X4With1Empty_JSON_raw.json").readText()
        val result = additionalFeaturesService.saveGame(SaveGameRequest(JSON, game4X4With1Empty("00:02:31", 5), "00:03:05"))

        assertEquals(JSON, result.savedGameState.serializationFormat)
        assertEquals(expectedJson, result.savedGameState.content)
        assertEquals("00:03:05", JacksonDeserializedSudokuGameState(result.savedGameState.content).state().playedTime)
    }
}