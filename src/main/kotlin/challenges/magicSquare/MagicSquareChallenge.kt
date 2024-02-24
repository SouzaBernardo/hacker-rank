package challenges.magicSquare

import challenges.base.BaseChallenge
import kotlin.math.abs

private const val MAGIC_SIZE = 15

class MagicSquareChallenge(
    input: Array<Array<Int>>
) : BaseChallenge<Array<Array<Int>>, Int>(input) {
    override fun execute(): Int {

        val see = mutableListOf<Int>()
        val repeated = mutableListOf<Int>()

        input.forEachIndexed { i, ints ->
            // linha repete?
            val repeat = checkLine(ints)
            // linha tem 15?
            val sum = abs(MAGIC_SIZE - ints.sum())
            repeatedNumbers(ints, see, repeated)

        }
        return 0
    }

    private fun repeatedNumbers(
        numbers: Array<Int>,
        see: MutableList<Int>,
        repeated: MutableList<Int>
    ) {
        numbers.forEach { number ->
            if (see.contains(number))
                repeated.add(number)
            else
                see.add(number)
        }
    }

    private fun checkLine(input: Array<Int>): Boolean {
        return input.distinct().size == input.size
    }

}