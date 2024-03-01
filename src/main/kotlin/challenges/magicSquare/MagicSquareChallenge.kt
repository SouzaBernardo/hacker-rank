package challenges.magicSquare

import challenges.base.BaseChallenge
import kotlin.math.abs

private const val MAGIC_SIZE = 15

class MagicSquareChallenge(
    input: Array<Array<Int>>
) : BaseChallenge<Array<Array<Int>>, Int>(input) {

    override fun execute(): Int {
        val normalizedSquare = normalizeInput(input)
        val magicSquare = convertToMagicSquare(clone(normalizedSquare))
        return getResult(normalizedSquare, magicSquare)
    }

    private fun getResult(input: Array<Array<Number>>, normalizeMatrix: Array<Array<Number>>): Int {
        var result = 0
        input.forEachIndexed { row, ints ->
            ints.forEachIndexed { column, number ->
                val normalized = normalizeMatrix[row][column]
                if (number.value != normalized.value) {
                    result += abs(number.value - normalized.value)
                }
            }
        }
        return result
    }

    private fun clone(numbers: Array<Array<Number>>) = numbers.map { row ->
        row.map { number ->
            number.clone()
        }.toTypedArray()
    }.toTypedArray()

    private fun normalizeInput(input: Array<Array<Int>>): Array<Array<Number>> {
        val viewedNumbers = mutableListOf<Number>()
        val numbers = mutableListOf<Array<Number>>()
        input.forEachIndexed { line, ints ->
            val row = mutableListOf<Number>()
            ints.forEachIndexed { col, value ->
                val element = Number(
                    value = value, line = line, col = col,
                    isRepeated = viewedNumbers.toTypedArray().findNumberRepeted(value)
                )
                viewedNumbers.add(element)
                row.add(element)
            }
            numbers.add(row.toTypedArray())
        }
        return numbers.toTypedArray()
    }

    private fun Array<Number>.findNumberRepeted(
        value: Int
    ) = this.any { it.value == value }

    fun Array<Array<Number>>.findNumberRepeted(value: Int) = this.any { it.findNumberRepeted(value) }

    private fun convertToMagicSquare(square: Array<Array<Number>>): Array<Array<Number>> {
        do {
            var lineWithRepeatedNumber = getRepeatedNumbers(square)
            lineWithRepeatedNumber.forEach { numbers ->
                val number = numbers.find { it.isRepeated }!!
                val sumLine = abs(MAGIC_SIZE - numbers.sumOf { it.value })
                val newNumber = Number(
                    value = number.value + sumLine,
                    line = number.line,
                    col = number.col,
                    square.findNumberRepeted(number.value + sumLine)
                )
                square[number.line][number.col] = newNumber
            }
            lineWithRepeatedNumber = getRepeatedNumbers(square)
        } while (lineWithRepeatedNumber.isNotEmpty())

        return square
    }

    private fun getRepeatedNumbers(square: Array<Array<Number>>) = square.filter {
        it.filter { number ->
            number.isRepeated
        }.isNotEmpty()
    }

//    private fun normalizeMatrix(): Array<Array<Int>> {
//        val matrix = input.clone()
//        var r = matrix.normalizeInput()
//        while (r.isNotEmpty()) {
//            matrix.forEachIndexed { row, numbers ->
//                var line = 0
//                var col = 0
//                var repeated: Number? = null
//                // TODO: Can change?
//                numbers.forEachIndexed { column, _ ->
//                    if (r.exists(row) || r.exists(column)) {
//                        line += matrix[row][column]
//                        col += matrix[column][row]
//                        repeated = r.getNumber(row, column)
//                    }
//                }
//
//                val resultLine = abs(MAGIC_SIZE - line)
//                val resultCol = abs(MAGIC_SIZE - col)
//
//                if (repeated != null) {
//                    if (resultLine != 0) {
//                        matrix[repeated!!.line][repeated!!.col] = repeated!!.value + resultLine
//                    }
//                    if (resultCol != 0) {
//                        matrix[repeated!!.col][repeated!!.line] = repeated!!.value + resultCol
//                    }
//                    repeated = null
//                }
//                println("line > $line \ncolumn > $col \nline sum > $resultLine \ncol sum > $resultCol")
//            }
//            r = matrix.normalizeInput()
//            r.forEach { println(it) }
//        }
//        return matrix
//    }

}