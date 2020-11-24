package org.nika.sudokuInteraction.state

interface HasInternalState<T> {
    fun internalState(): T
}