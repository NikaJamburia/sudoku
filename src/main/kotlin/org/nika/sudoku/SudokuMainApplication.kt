package org.nika.sudoku

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.nika.*"])
class SudokuMainApplication

fun main(args: Array<String>) {
    runApplication<SudokuMainApplication>(*args)
}