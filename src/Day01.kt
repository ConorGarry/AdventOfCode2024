import kotlin.math.abs

class Day01 {
    fun part1(input: List<String>): Int =
        input.map {
            it.substringBefore(" ").toInt() to it.substringAfterLast(" ").toInt()
        }.unzip()
            .run { first.sorted() to second.sorted() }
            .let { (left, right) ->
                left.zip(right).sumOf { (a, b) -> abs(b - a) }
            }

    fun part2(input: List<String>): Int =
        input.map {
            it.substringBefore(" ").toInt() to it.substringAfterLast(" ").toInt()
        }.unzip().run { first.sorted() to second.sorted() }
            .let { (left, right) ->
                left.sumOf { l -> l * right.count { it == l } }
            }
}

fun main() {
    with(Day01()) {
        part1(readInput("Day01")).println() // 2815556
        part2(readInput("Day01")).println() // 23927637
    }
}
