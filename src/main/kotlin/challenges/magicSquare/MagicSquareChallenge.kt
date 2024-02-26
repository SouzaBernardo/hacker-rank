package challenges.magicSquare

import challenges.base.BaseChallenge
import kotlin.math.abs

private const val MAGIC_SIZE = 15

class MagicSquareChallenge(
    input: Array<Array<Int>>
) : BaseChallenge<Array<Array<Int>>, Int>(input) {

    class Number(
        val value: Int,
        val line: Int,
        val col: Int,
        val isRepeated: Boolean
    ) {
        override fun toString(): String {
            return "value: $value\nline: $line\ncolumn: $col\nrepeated: $isRepeated"
        }
    }

    override fun execute(): Int {
        val normalizeMatrix = input.repeatedNumbers()

        println("Hi")

        return getResult()
    }

    private fun Array<Number>.getNumber(line: Int, col: Int): Number {
        return this.find { it.line == line || it.col == col }!!
    }

    private fun Array<Number>.exists(line: Int): Boolean {
        return this.any { it.line == line || it.col == line }
    }

    private fun getResult() = 1

    private fun List<Number>.contains(value: Int) =
        this.any { it.value == value }


    private fun Array<Array<Int>>.repeatedNumbers(): Array<Number> {
        val numbers = mutableListOf<Number>()
        this.forEachIndexed { line, ints ->
            ints.forEachIndexed { col, value ->
                numbers.add(
                    Number(
                        value = value, line = line, col = col,
                        isRepeated = numbers.contains(value)
                    )
                )
            }
        }
        return numbers.toTypedArray()
    }

    private fun normalizeMatrix(): Array<Array<Int>> {
        val matrix = input.clone()
        var r = matrix.repeatedNumbers()
        while (r.isNotEmpty()) {
            matrix.forEachIndexed { row, numbers ->
                var line = 0
                var col = 0
                var repeated: Number? = null
                // TODO: Can change?
                numbers.forEachIndexed { column, _ ->
                    if (r.exists(row) || r.exists(column)) {
                        line += matrix[row][column]
                        col += matrix[column][row]
                        repeated = r.getNumber(row, column)
                    }
                }

                val resultLine = abs(MAGIC_SIZE - line)
                val resultCol = abs(MAGIC_SIZE - col)

                if (repeated != null) {
                    if (resultLine != 0) {
                        matrix[repeated!!.line][repeated!!.col] = repeated!!.value + resultLine
                    }
                    if (resultCol != 0) {
                        matrix[repeated!!.col][repeated!!.line] = repeated!!.value + resultCol
                    }
                    repeated = null
                }
                println("line > $line \ncolumn > $col \nline sum > $resultLine \ncol sum > $resultCol")
            }
            r = matrix.repeatedNumbers()
            r.forEach { println(it) }
        }
        return matrix
    }

    private fun checkLine(input: Array<Int>): Boolean {
        return input.distinct().size == input.size
    }

}