import kotlin.math.abs

class Day02 {

    fun part1(input: List<String>): Int =
        input.toLevelRows().count(::isLevelRowSafe)

    fun part2(input: List<String>): Int =
        input.toLevelRows().count { (isLevelRowSafe(it) || isLevelFixable(it)) }

    private fun isLevelFixable(levels: List<Int>): Boolean {
        levels.foldRightIndexed(levels) { index, _, acc ->
            if (isLevelRowSafe(acc.filterIndexed { idx, _ -> idx != index })) return true
            acc
        }
        return false
    }

    fun isLevelRowSafe(levels: List<Int>): Boolean =
        ((levels.zipWithNext().all { (a, b) -> a < b } ||
                levels.zipWithNext().all { (a, b) -> a > b })) &&
                levels.zipWithNext().all { (a, b) -> abs(b - a) in 1..3 }

    private fun List<String>.toLevelRows(): List<List<Int>> =
        map { it.trim().split(" ").map(String::toInt) }
}

fun main() {
    with(Day02()) {
        part1(readInput("Day02")).println() // 624
        part2(readInput("Day02")).println()
    }
}
