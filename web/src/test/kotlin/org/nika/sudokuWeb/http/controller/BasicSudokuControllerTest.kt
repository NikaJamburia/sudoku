package org.nika.sudokuWeb.http.controller

import org.junit.jupiter.api.Test
import org.nika.sudokuWeb.SudokuWebApiApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [SudokuWebApiApplication::class])
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
}