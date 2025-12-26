fun main() {

    data class SafeDial(val position: Int) {

        operator fun inc(): SafeDial {
            var tmp = position
            if (position == 99) tmp = 0
            else tmp += 1
            return SafeDial(tmp)
        }

        operator fun dec(): SafeDial {
            var tmp = position
            if (position == 0) tmp = 99
            else tmp -= 1
            return SafeDial(tmp)
        }
    }

    fun part1(input: List<String>): Int {
        var dial = SafeDial(50)
        var zeroes = 0

        input.forEach { cmd ->
            val increment = cmd.startsWith("R")
            val n = cmd.drop(1).toInt()
            repeat(n) {
                if (increment) dial++
                else dial--
            }
            if (dial.position == 0) zeroes += 1
        }

        "Number of zeroes: $zeroes".println()
        return zeroes
    }

    fun part2(input: List<String>): Int {
        var dial = SafeDial(50)
        var zeroes = 0

        input.forEach { cmd ->
            val increment = cmd.startsWith("R")
            val n = cmd.drop(1).toInt()
            repeat(n) {
                if (increment) dial++
                else dial--
                if (dial.position == 0) zeroes += 1
            }
        }

        "Number of zeroes: $zeroes".println()
        return zeroes
    }

// Test if implementation meets criteria from the description, like:
// check(part1(listOf("test_input")) == 1)

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
