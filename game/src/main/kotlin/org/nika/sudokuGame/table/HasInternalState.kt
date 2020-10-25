package org.nika.sudokuGame.table

interface HasInternalState<T> {
    fun internalState(): T
}