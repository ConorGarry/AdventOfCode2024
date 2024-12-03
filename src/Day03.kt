class Day03 {

    fun part1(input: List<String>): Int =
        extractInstructions(input).sumOf {
            it.extractDigits().run {
                substringBefore(",").toInt() * substringAfter(",").toInt()
            }
        }

    fun part2(input: List<String>): Int {
        var sum = 0
        var enabled = true
        extractInstructions(input, """mul\((-?\d+),(-?\d+)\)|do\(\)|don't\(\)""".toRegex()).map {
            when (it) {
                "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> if (enabled) {
                    val (a, b) = it.extractDigits().split(",").map(String::toInt)
                    sum += a * b
                }
            }
        }
        return sum
    }

    private fun extractInstructions(
        input: List<String>,
        regex: Regex = """mul\((-?\d+),(-?\d+)\)""".toRegex()
    ): List<String> = buildList {
        input.forEach {
            regex.findAll(it).forEach { match ->
                add(match.value)
            }
        }
    }

    private fun String.extractDigits(): String =
        removePrefix("mul(").removeSuffix(")")
}

fun main() {
    with(Day03()) {
        part1(readInput("Day03")).println()
        part2(readInput("Day03")).println()
    }
}
