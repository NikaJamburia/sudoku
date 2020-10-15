package table

data class Coordinates (
    val x: Int,
    val y: Int
) {
    override fun equals(other: Any?): Boolean {
        other as Coordinates
        return this.x == other.x && this.y == other.y
    }
}