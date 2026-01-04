import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.text.toInt
import kotlin.time.measureTimedValue

suspend fun main() {

    fun maxJolts(bank: String): Int {
        var max = '0'
        var maxI = 0

        bank.forEachIndexed { i, c ->
            if (c.digitToInt() > max.digitToInt() && i < bank.lastIndex) {
                max = c
                maxI = i
            }
        }

        var maxSecond = '0'
        bank.substring(maxI + 1, bank.lastIndex + 1).forEach { c ->
            if (c.digitToInt() > maxSecond.digitToInt()) {
                maxSecond = c
            }
        }
        return String(charArrayOf(max, maxSecond)).toInt()
    }

    fun part1(input: List<String>): Int {
        val totalOutputVoltage = measureTimedValue {
            input.sumOf {
                maxJolts(it)
            }
        }
        totalOutputVoltage.duration.println()
        return totalOutputVoltage.value
    }

    suspend fun part1Coroutine(input: List<String>): Int {
        val totalOutputVoltages = measureTimedValue {
            coroutineScope {
                input.map {
                    async {
                        maxJolts(it)
                    }
                }.awaitAll().sum()
            }
        }
        totalOutputVoltages.duration.println()
        return totalOutputVoltages.value
    }

    fun part2(input: List<String>): Int {
        return 0
    }

// Test if implementation meets criteria from the description, like:
// check(part1(listOf("test_input")) == 1)

//fun readInput(name: String) = Path("src/$name.txt").readText().trim().split(",")

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 357)
    //check(part2(testInput) == 4174379265L)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part1Coroutine(input).println()
    //part2(input).println()
}
