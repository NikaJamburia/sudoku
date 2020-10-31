package org.nika.sudokuWeb.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import org.nika.sudokuInteraction.request.FillCellRequest
import org.nika.sudokuInteraction.state.GameState
import org.nika.sudokuWeb.SudokuWebApiApplication
import org.omg.CORBA.Any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [SudokuWebApiApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BasicSudokuControllerTest(
    @Autowired private val mockMvc: MockMvc
) {
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


    private fun mockedGameState(): GameState {
        val gameJson = this::class.java.classLoader.getResource("mocked-serialized-game.json").readText()
        return ObjectMapper().registerModule(KotlinModule()).readValue(gameJson, GameState::class.java)
    }
}