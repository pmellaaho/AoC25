import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.text.toInt

fun main() {

    fun beginningAndEndingMatches(s: String): Boolean {
        return if (s.length % 2 != 0) false
        else {
            val len = s.length / 2
            val beginning = s.take(len)
            val ending = s.takeLast(len)
            beginning.contentEquals(ending)
        }
    }

    fun part1(input: List<String>): Long {
        val allInvalids = input.sumOf {
            val p = it.split("-").let { it.first().toLong() to it.last().toLong() }
            val range = p.first..p.second

            var invalids = 0L
            for (i in range) {
                val s = i.toString()
                if (beginningAndEndingMatches(s)) invalids += i
            }
            invalids
        }
        return allInvalids
    }

    fun part2(input: List<String>): Long {
        val allInvalids = input.sumOf {
            val p = it.split("-").let { it.first().toLong() to it.last().toLong() }
            val range = p.first..p.second

            var invalids = 0L
            for (i in range) {
                val s = i.toString()
                if (s.length < 2) continue

                // all same, even/odd length
                if (s.all { it == s[0] }) {
                    invalids += i
                    //"Number $i is invalid".println()
                    continue
                }
                if (beginningAndEndingMatches(s)) {
                    invalids += i
                    //"Number $i is invalid".println()
                    continue
                }

                val idLength = s.length
                if (idLength >= 4) {
                    var j = 2
                    while (idLength / j > 2) {
                        if (idLength % j == 0) {
                            val chunks = s.chunked(j)
                            if (chunks.all { it.contentEquals(chunks[0]) }) {
                                invalids += i
                                //"Number $i is invalid".println()
                                break
                            }
                        }
                        j++
                    }
                }
            }
            invalids
        }
        return allInvalids
    }

// Test if implementation meets criteria from the description, like:
// check(part1(listOf("test_input")) == 1)

    fun readInput(name: String) = Path("src/$name.txt").readText().trim().split(",")

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    //check(part1(testInput) == 1227775554L)
    //check(part2(testInput) == 4174379265L)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
