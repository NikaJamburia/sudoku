package org.nika.sudokuGame.util.random

import java.lang.IllegalStateException

open class RandomSequenceOfUniqueInts(
    private val sequenceSize: Int,
    private val from: Int,
    private val until: Int
) {
    private var sequenceContent: Set<Int> = generateSequence()
    private var currentIndex = -1

    fun next(): Int {
        currentIndex += 1
        if (currentIndex > lastIndex()) {
            throw IllegalStateException("Already iterated over full sequence")
        }
        return sequenceContent.toList()[currentIndex]
    }

    fun asSet(): Set<Int> {
        currentIndex = lastIndex()
        return sequenceContent
    }

    private fun lastIndex(): Int = sequenceContent.indexOf(sequenceContent.last())

    private fun generateSequence(): Set<Int> {
        if (sequenceSize > (until - (from-1))) {
            throw IllegalStateException("Size of the sequence cant be greater size of range")
        }  else if (from > until) {
            throw IllegalStateException("Incorrect range")
        }

        val generatedInts = mutableSetOf<Int>()
        while (generatedInts.size < sequenceSize) {
            generatedInts.add((from..until).random())
        }
        return generatedInts
    }
}