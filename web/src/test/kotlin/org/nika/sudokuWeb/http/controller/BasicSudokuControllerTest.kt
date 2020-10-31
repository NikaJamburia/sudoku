package org.nika.sudokuWeb.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import org.nika.sudokuGame.gameplay.game.GameState
import org.nika.sudokuService.interaction.request.FillCellRequest
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
    private val controllerPath = "/web/api/sudoku/"

    @Test
    fun startsNewGameCorrectly() {
        mockMvc.perform(get(controllerPath + "start-new-game"))
            .andExpect(status().isOk)
    }

    @Test
    fun correctlyFillsCell() {
        mockMvc.perform(post(controllerPath + "fill-cell")
            .content(asJsonString(FillCellRequest(6, 1, 3, mockedGameState(), "00:00:51")))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.isSuccessful").value(true))
            .andExpect(jsonPath("$.message").value("Successfully filled cell"))
    }

    private fun mockedGameState(): GameState {
        val gameJson = this::class.java.classLoader.getResource("mocked-serialized-game.json").readText()
        return ObjectMapper().registerModule(KotlinModule()).readValue(gameJson, GameState::class.java)
    }
}