package challenges.magicSquare

import challenges.base.BaseChallenge

class MagicSquareChallenge(
    input: Array<Array<Int>>
) : BaseChallenge<Array<Array<Int>>, Int>(input) {
    override fun execute(): Int {

        val mergeInput = mergeInput(input)
        println(mergeInput)

        return 0
    }

    private fun mergeInput(input: Array<Array<Int>>): List<Int> {
        return input[0].plus(input[1]).plus(input[2]).sorted()
    }

}