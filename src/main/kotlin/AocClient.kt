import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.io.File
import java.lang.Exception

object AocClient {

    private val sessionId: String = try {
        File("cookie.txt").readText().trim()
    } catch (e: Exception) {
        throw RuntimeException("Failed to  read cookie: ${e.message}")
    }

    suspend fun getAocDayInput(day: Int): Array<String> {
        val file = File("inputs/$day.txt")

        if (file.exists()) {
            return getDayInputAsArray(day)
        }

        try {
            HttpClient(CIO).use { client ->
                client.get("https://adventofcode.com/2023/day/$day/input") {
                    header("Cookie", "session=$sessionId")
                }.bodyAsText().also {
                    file.parentFile.mkdirs()
                    file.writeText(it)
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to fetch input for day $day: ${e.message}")
        }

        return getDayInputAsArray(day)
    }

    private fun getDayInputAsArray(day: Int): Array<String> {
        val inputString = File("inputs/$day.txt").readText()
        return inputString.trim().split("\n").toTypedArray()
    }

}