package org.nika.sudokuService.spring.configuration

import org.nika.sudokuGame.generation.parameters.SudokuTableGenerationParameters
import org.nika.sudokuGame.generation.parameters.TableGenerationAlgorithm
import org.nika.sudokuInteraction.enums.SudokuDifficulty
import org.nika.sudokuInteraction.enums.SudokuDifficulty.*
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
        @Value("\${table.generation.default-closed-cells:17}") closedCellsNumber: Int
    ): SudokuTableGenerationParameters {
        return SudokuTableGenerationParameters(
            generationAlgorithm,
            rowSize,
            columnSize,
            closedCellsNumber
        )
    }

    @Bean
    fun tableDifficultyConfiguration(
        @Value("\${table.difficulty.easy.closed-cells:70}") easyClosedCells: Int,
        @Value("\${table.difficulty.medium.closed-cells:60}") mediumClosedCells: Int,
        @Value("\${table.difficulty.hard.closed-cells:50}") hardClosedCells: Int
    ): Map<SudokuDifficulty, Int> =
        mapOf(
            Pair(EASY, easyClosedCells),
            Pair(MEDIUM, mediumClosedCells),
            Pair(HARD, hardClosedCells)
        )

}