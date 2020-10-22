package application

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
open class SudokuServiceApplication

fun main(args: Array<String>) {
    runApplication<SudokuServiceApplication>(*args)
}