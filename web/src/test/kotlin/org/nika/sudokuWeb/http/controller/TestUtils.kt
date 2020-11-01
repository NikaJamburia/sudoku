package org.nika.sudokuWeb.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)

fun postWithJson(url: String, content: String): MockHttpServletRequestBuilder =
    MockMvcRequestBuilders.post(url)
        .content(content)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)

fun String.isGameStateJson(): Boolean {
    return this.contains("playedTime")
            && this.contains("numberOfTurns")
            && this.contains("gameIsWon")
            && this.contains("tableState")
}


