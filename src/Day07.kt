class Day07 {

    private fun equations(input: List<String>) = input.map {
        it.split(":").let { (testValue, operators) ->
            testValue.toLong() to operators.trim().split(" ").map(String::toLong)
        }
    }

    private val ops = listOf(
        { a: Long, b: Long -> a + b },
        { a: Long, b: Long -> a * b },
    )

    fun part1(input: List<String>): Long =
        equations(input).sumOf { if (recCalc(it.first, it.second, 0, ops)) it.first else 0 }

    private fun recCalc(testValue: Long, values: List<Long>, acc: Long, ops: List<(Long, Long) -> Long>): Boolean {
        val value = values.firstOrNull() ?: return testValue == acc
        return ops.any { operator ->
            recCalc(testValue, values.subList(1, values.size), operator(acc, value), ops)
        }
    }

    fun part2(input: List<String>): Long =
        equations(input).sumOf {
            if (recCalc(
                    it.first,
                    it.second,
                    0,
                    buildList {
                        addAll(ops)
                        add { a: Long, b: Long -> "$a$b".toLong() }
                    }
                )
            ) it.first else 0
        }
}

fun main() {
    with(Day07()) {
        part1(readInput("Day07")).println()
        part2(readInput("Day07")).println()
    }
}
