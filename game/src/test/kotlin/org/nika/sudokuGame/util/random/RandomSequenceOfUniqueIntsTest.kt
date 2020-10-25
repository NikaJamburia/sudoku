package org.nika.sudokuGame.util.random

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RandomSequenceOfUniqueIntsTest  {
    @Test
    fun throwsExceptionWhenSequenceSizeIsGreaterThenRangeSize() {
        assertThrows(IllegalStateException::class.java) { RandomSequenceOfUniqueInts(5, 1, 4) }
    }

    @Test
    fun throwsExceptionWhenRangeIsIncorrect() {
        assertThrows(IllegalStateException::class.java) { RandomSequenceOfUniqueInts(10, 100, 50) }
    }

    @Test
    fun throwsExceptionWhenWeCallNextToMuch() {
        val seq = RandomSequenceOfUniqueInts(3, 1, 5)
        seq.next()
        seq.next()
        seq.next()
        assertThrows(IllegalStateException::class.java) { seq.next() }
    }

    @Test
    fun throwsExceptionWhenTryingToGetNextAfterGettingWholeSequenceAsList() {
        val seq = RandomSequenceOfUniqueInts(3, 1, 5)
        seq.asSet()
        assertThrows(IllegalStateException::class.java) { seq.next() }
    }

    @Test
    fun allNumbersInGeneratedSequenceAreUnique() {
        val seq = RandomSequenceOfUniqueInts(1000, 1, 1000)
        val set = seq.asSet()
        assertEquals(1000, set.size)

        (1..1000).forEach { assertTrue { set.contains(it) } }
    }

    @Test
    fun canCorrectlyIterateOverWholeSequence() {
        val seq = RandomSequenceOfUniqueInts(1000, 1, 1000)
        val numbers = mutableListOf<Int>()

        repeat(1000) { numbers.add(seq.next()) }
        assertEquals(1000, numbers.size)
        numbers.groupBy { it }.all { it.value.size == 1 }

        assertThrows(IllegalStateException::class.java) { seq.next() }
    }

    @Test
    fun generatedIntsAreInCorrectRange() {
        val set = RandomSequenceOfUniqueInts(1000, 1, 1000).asSet()
        assertTrue(set.max()!! <= 1000)
        assertTrue(set.min()!! >= 1)

    }

}