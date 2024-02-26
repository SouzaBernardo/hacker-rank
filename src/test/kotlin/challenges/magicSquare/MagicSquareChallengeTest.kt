package challenges.magicSquare

import kotlin.test.Test
import kotlin.test.assertEquals

class MagicSquareChallengeTest {

    @Test
    fun execute() {
        val magicSquareChallenge = MagicSquareChallenge(
            arrayOf(
                arrayOf(4, 9, 2),
                arrayOf(3, 5, 7),
                arrayOf(8, 1, 5) // 6
            )
        )
        assertEquals(1, magicSquareChallenge.execute())
    }
}