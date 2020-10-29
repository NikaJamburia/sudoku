package org.nika.sudokuWeb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.nika.sudokuWeb", "org.nika.sudokuService"])
class SudokuWebApiApplication

fun main(args: Array<String>) {
    runApplication<SudokuWebApiApplication>(*args)
}