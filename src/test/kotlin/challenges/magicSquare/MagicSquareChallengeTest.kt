package challenges.magicSquare

import kotlin.test.Test
import kotlin.test.assertEquals

class MagicSquareChallengeTest {

    @Test
    fun execute() {
//        val magicSquareChallenge = MagicSquareChallenge(
//            arrayOf(
//                arrayOf(4, 9, 2),
//                arrayOf(3, 5, 7),
//                arrayOf(8, 1, 5)
//            )
//        )
        val magicSquareChallenge2 = MagicSquareChallenge(
            arrayOf(
                arrayOf(4, 8, 2),
                arrayOf(4, 5, 7),
                arrayOf(6, 1, 6)
            )
        )
//        assertEquals(1, magicSquareChallenge.execute())
        assertEquals(4, magicSquareChallenge2.execute())
    }
}