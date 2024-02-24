package challenges.base

abstract class BaseChallenge<I, R>(open val input: I) {
    abstract fun execute(): R
    override fun toString() = "${execute()}"
}