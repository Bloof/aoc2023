import day01.day01
import day02.day02

suspend fun main(args: Array<String>) {

    println("Enter the day to solve as number (e.g '1')")

    val input = readLine() ?: run {
        println("No input received. Exiting")
        return
    }

    when(input) {
        "1" -> day01.solvePuzzle()
        "2" -> day02.solvePuzzle()
        else -> println("Day not implemented")
    }
}