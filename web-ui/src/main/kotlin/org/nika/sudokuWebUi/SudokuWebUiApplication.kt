package org.nika.sudokuWebUi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SudokuWebUiApplication

fun main(args: Array<String>) {
    runApplication<SudokuWebUiApplication>(*args)
}