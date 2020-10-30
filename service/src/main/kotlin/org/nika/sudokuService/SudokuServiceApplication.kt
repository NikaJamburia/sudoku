package org.nika.sudokuService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SudokuServiceApplication

fun main(args: Array<String>) {
    runApplication<SudokuServiceApplication>(*args)
}