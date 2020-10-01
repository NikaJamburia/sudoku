package table

data class Coordinates (
    val x: Int,
    val y: Int
) {
    fun sameAs(other: Coordinates): Boolean =
        this.x == other.x && this.y == other.y

}