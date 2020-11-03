package org.nika.sudokuWeb.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.enums.SerializationFormat.JSON
import org.nika.sudokuInteraction.enums.SerializationFormat.XML
import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.request.LoadGameRequest
import org.nika.sudokuInteraction.request.SaveGameRequest
import org.nika.sudokuInteraction.request.SudokuInteractionRequest
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuInteraction.state.SavedSudokuGameState
import org.nika.sudokuWeb.SudokuWebApiApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [SudokuWebApiApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BasicSudokuControllerTest(
    @Autowired private val mockMvc: MockMvc
) {
    private val mockedGameJson = this::class.java.classLoader.getResource("mocked-serialized-game.json").readText()
    private val controllerPath = "/web/api/sudoku/"

    @Test
    fun startsNewGameCorrectly() {
        mockMvc.perform(get(controllerPath + "start-new-game"))
            .andExpect(status().isOk)
    }

    @Test
    fun correctlyFillsCell() {
        mockMvc.perform(
            postWithJson(controllerPath + "fill-cell", asJsonString(FillCellRequest(4, 1, 3, mockedGameState(), "00:00:51"))))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.isSuccessful").value(true))
            .andExpect(jsonPath("$.message").value("Successfully filled cell"))
            .andExpect(jsonPath("$.content.playedTime").value("00:00:51"))
            .andExpect(jsonPath("$.content.numberOfTurns").value(1))
            .andExpect(jsonPath("$.content.tableState.conflicts").isEmpty)
    }

    @Test
    fun handlesErrorsFillsCell() {
        mockMvc.perform(
            postWithJson(controllerPath + "fill-cell", asJsonString(FillCellRequest(4, 1, 100, mockedGameState(), "00:00:51"))))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.isSuccessful").value(false))
            .andExpect(jsonPath("$.message").value("Cell not found"))

        mockMvc.perform(
            postWithJson(controllerPath + "fill-cell", asJsonString(FillCellRequest(-1, 1, 1, mockedGameState(), "00:00:51"))))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.isSuccessful").value(false))
            .andExpect(jsonPath("$.message").value("Value of closed cell can not be changed"))

        mockMvc.perform(
            postWithJson(controllerPath + "fill-cell", asJsonString(FillCellRequest(-1, 1, 3, mockedGameState(), "00:00:51"))))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.isSuccessful").value(false))
            .andExpect(jsonPath("$.message").value("Value of cell must be between 0 and 9!"))
    }

    @Test
    fun correctlyRestartsGame() {
        mockMvc.perform(
            postWithJson(controllerPath + "restart-game",asJsonString(SudokuInteractionRequest(mockedGameState(), "00:00:51"))))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.isSuccessful").value(true))
            .andExpect(jsonPath("$.message").value("New game started"))
            .andExpect(jsonPath("$.content.playedTime").value("00:00:00"))
            .andExpect(jsonPath("$.content.numberOfTurns").value(0))
        }

    @Test
    fun correctlySavesGame() {
        mockMvc.perform(
            postWithJson(controllerPath + "save",asJsonString(SaveGameRequest(JSON, mockedGameState(), "00:00:51"))))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.isSuccessful").value(true))
            .andExpect(jsonPath("$.message").value("Saved successfully"))
            .andExpect(jsonPath("$.savedGameState.serializationFormat").value("JSON"))
            .andExpect(jsonPath("$.savedGameState.content").isNotEmpty)
    }

    @Test
    fun correctlyHandlesErrorOnSaveGame() {
        mockMvc.perform(
            postWithJson(controllerPath + "save", asJsonString(SaveGameRequest(XML, mockedGameState(), "00:00:51"))))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.isSuccessful").value(false))
            .andExpect(jsonPath("$.message").value("XML format not supported"))
            .andExpect(jsonPath("$.savedGameState.serializationFormat").value("JSON"))
            .andExpect(jsonPath("$.savedGameState.content").isEmpty)
    }

    @Test
    fun correctlyLoadsGame() {
        mockMvc.perform(
            postWithJson(controllerPath + "load", asJsonString(LoadGameRequest(SavedSudokuGameState(JSON, mockedGameJson)))))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.isSuccessful").value(true))
            .andExpect(jsonPath("$.message").value("Game loaded"))
            .andExpect(jsonPath("$.content").isNotEmpty)
    }

    @Test
    fun correctlyHandlesErrorsOnLoadGame() {
        mockMvc.perform(
            postWithJson(controllerPath + "load", asJsonString(LoadGameRequest(SavedSudokuGameState(JSON, "aaaaa")))))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.isSuccessful").value(false))

        mockMvc.perform(
            postWithJson(controllerPath + "load", asJsonString(LoadGameRequest(SavedSudokuGameState(XML, mockedGameJson)))))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.isSuccessful").value(false))
            .andExpect(jsonPath("$.message").value("XML format not supported"))
    }


    private fun mockedGameState(): GameState =
         ObjectMapper().registerModule(KotlinModule()).readValue(mockedGameJson, GameState::class.java)


}