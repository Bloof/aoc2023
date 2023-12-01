package day01

object day01 {

    suspend fun solvePuzzle(): String {
        val data = AocClient.getAocDayInput(1)
        println("Day01 part_1: " + partOne(data))
        println("Day01 part_2: " + partTwo(data))
        return "Done"
    }

    fun partOne(data: Array<String>): Int {
        return data.map { row ->
            row.filter { it.isDigit() }
        }.map { row ->
            val first = row.first()
            val last = row.last()
            "$first$last".toInt()
        }.sum()
    }

    fun partTwo(data: Array<String>): Int {
        return data.map { row ->
            row.windowed(5, 1, true) {
                when {
                    it[0].isDigit() -> it[0].toString()
                    it.startsWith("one") -> "1"
                    it.startsWith("two") -> "2"
                    it.startsWith("three") -> "3"
                    it.startsWith("four") -> "4"
                    it.startsWith("five") -> "5"
                    it.startsWith("six") -> "6"
                    it.startsWith("seven") -> "7"
                    it.startsWith("eight") -> "8"
                    it.startsWith("nine") -> "9"
                    else -> ""
                }
            }
        }.map {
            it.filter { c -> c.isNotBlank() }
        }.sumOf { (it.first() + it.last()).toInt() }
    }
}