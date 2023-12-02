package day02

object day02 {

    suspend fun solvePuzzle() {
        val data = AocClient.getAocDayInput(2)
        println("Day02 part_1: " + partOne(data))
        println("Day02 part_2: " + partTwo(data))

    }

    fun partOne(data: Array<String>): Int {

        val delimiters = "[:,;]".toRegex()

        val games = data.map { it.split(delimiters) }.toList()

        return games.map { game ->
            val gameNumber = game.filter { it.contains("Game") }
                .map {
                    it.removePrefix("Game ")
                        .trim()
                        .toInt()
                }.first()

            val redList = game.filter { it.contains(" red") }.map { it.removeSuffix(" red").trim() }
            val blueList = game.filter { it.contains(" blue") }.map { it.removeSuffix(" blue").trim() }
            val greenList = game.filter { it.contains(" green") }.map { it.removeSuffix(" green").trim() }

            var isAllowedGame = true
            isAllowedGame =
                !(redList.any { it.toInt() > 12 } || greenList.any { it.toInt() > 13 } || blueList.any { it.toInt() > 14 })

            Pair(gameNumber, isAllowedGame)
        }.filter { it.second }
            .sumOf { it.first }
    }

    fun partTwo(data: Array<String>): Int {

        val delimiters = "[:,;]".toRegex()

        val games = data.map { it.split(delimiters) }.toList()

        return games.map { game ->

            val redList =
                game.filter { it.contains(" red") }.maxOfOrNull { it.removeSuffix(" red").trim().toInt() } ?: 0
            val blueList =
                game.filter { it.contains(" blue") }.maxOfOrNull { it.removeSuffix(" blue").trim().toInt() } ?: 0
            val greenList =
                game.filter { it.contains(" green") }.maxOfOrNull { it.removeSuffix(" green").trim().toInt() } ?: 0

            redList * blueList * greenList

        }.sum()
    }

}