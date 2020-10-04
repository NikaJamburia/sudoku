package table

interface HasInternalState<T> {
    fun internalState(): T
}