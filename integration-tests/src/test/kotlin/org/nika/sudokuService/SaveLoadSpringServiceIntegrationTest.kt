package org.nika.sudokuService

import game4X4With1Empty
import org.junit.jupiter.api.Test

import org.nika.sudokuGame.saveload.deserialization.JacksonDeserializedSudokuGameState
import org.nika.sudokuInteraction.enums.SerializationFormat
import org.nika.sudokuInteraction.request.LoadGameRequest
import org.nika.sudokuInteraction.request.SaveGameRequest
import org.nika.sudokuInteraction.state.SavedSudokuGameState

import org.nika.sudokuService.spring.service.SaveLoadSpringService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@SpringBootTest(classes = [SudokuServiceApplication::class])
class SaveLoadSpringServiceIntegrationTest (
    @Autowired private val saveLoadSpringService: SaveLoadSpringService
) {
    @Test
    fun loadsGame() {
        val savedJson = this::class.java.classLoader.getResource("game4X4With1Empty_JSON_pretty.json").readText()
        val result = saveLoadSpringService.loadGame(LoadGameRequest(SavedSudokuGameState(SerializationFormat.JSON, savedJson)))

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
        val result = saveLoadSpringService.saveGame(SaveGameRequest(SerializationFormat.JSON, game4X4With1Empty("00:02:31", 5), "00:03:05"))

        assertEquals(SerializationFormat.JSON, result.savedGameState.serializationFormat)
        assertEquals(expectedJson, result.savedGameState.content)
        assertEquals("00:03:05", JacksonDeserializedSudokuGameState(result.savedGameState.content).state().playedTime)
    }
}