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

    suspend fun getAocDayInput(day: Int): String {
        val file = File("inputs/$day.txt")

        if (file.exists()) {
            return file.readText()
        }

        val data = try {
            HttpClient(CIO).use { client ->
                client.get("https://adventofcode.com/2022/day/$day/input") {
                    header("Cookie", "session=$sessionId")
                }.bodyAsText().also {
                    file.parentFile.mkdirs()
                    file.writeText(it)
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to fetch input for day $day: ${e.message}")
        }

        return data
    }

    private fun saveInputToFile(data: String, day: Int) {
        try {
            val directory = File("inputs")
            if (!directory.exists()) {
                directory.mkdirs()
            }

            val file = File(directory, "$day.txt")
            file.writeText(data)
        } catch (e: Exception) {
            throw RuntimeException("Failed to save AoC input to file for day: $day: ${e.message}")
        }
    }


}