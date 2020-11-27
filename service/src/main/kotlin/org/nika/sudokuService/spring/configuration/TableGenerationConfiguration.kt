package org.nika.sudokuService.spring.configuration

import org.nika.sudokuGame.generation.SudokuTableGenerationParameters
import org.nika.sudokuGame.generation.TableGenerationAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:sudoku-service.properties")
class TableGenerationConfiguration {

    @Bean
    fun sudokuTableGenerationParameters(
        @Value("\${table.generation.algorithm:MOCKED}") generationAlgorithm: TableGenerationAlgorithm,
        @Value("\${table.generation.column-size:9}") columnSize: Int,
        @Value("\${table.generation.row-size:9}") rowSize: Int,
        @Value("\${table.generation.closed-cells:17}") closedCellsNumber: Int
    ): SudokuTableGenerationParameters {
        return SudokuTableGenerationParameters(
            generationAlgorithm,
            rowSize,
            columnSize,
            closedCellsNumber
        )
    }
}