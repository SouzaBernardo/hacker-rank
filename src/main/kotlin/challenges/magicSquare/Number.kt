package challenges.magicSquare

class Number(
    val value: Int,
    val line: Int,
    val col: Int,
    val isRepeated: Boolean
) {
    override fun toString(): String {
        return "value: $value\nline: $line\ncolumn: $col\nrepeated: $isRepeated"
    }

    fun clone() = Number(
        value = this.value,
        line = this.line,
        col = this.col,
        isRepeated = this.isRepeated
    )
}