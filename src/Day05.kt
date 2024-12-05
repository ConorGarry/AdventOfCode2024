class Day05 {

    private val preMap = mutableMapOf<Int, MutableList<Int>>()
    private val postMap = mutableMapOf<Int, MutableList<Int>>()

    private fun populateMaps(input: List<String>): List<List<Int>> {
        val rules = input.takeWhile { it.isNotEmpty() }.map { it.split("|").map(String::toInt) }
        rules.forEach { (l, r) ->
            preMap.putIfAbsent(l, mutableListOf(r))?.apply { add(r) }
            postMap.putIfAbsent(r, mutableListOf(l))?.apply { add(l) }
        }
        return input.subList(rules.size + 1, input.size).map {
            it.split(",").map(String::toInt)
        }
    }

    fun part1(input: List<String>): Int {
        val pages = populateMaps(input)
        return pages.filter { isValidUpdate(preMap, postMap, it) }.sumOf { it[it.size / 2] }
    }

    private fun isValidUpdate(
        prevMap: Map<Int, List<Int>>,
        trailMap: Map<Int, List<Int>>,
        update: List<Int>
    ): Boolean {
        update.forEachIndexed { index, page ->
            val prev = update.subList(0, index)
            val trail = update.subList(index + 1, update.size)
            if (prev.any { it in (prevMap[page]).orEmpty() } ||
                trail.any { it in (trailMap[page].orEmpty()) }
            ) return false
        }
        return true
    }

    fun part2(input: List<String>): Int =
        populateMaps(input).filterNot { isValidUpdate(preMap, postMap, it) }.sumOf {
            val arr = it.toIntArray()
            for (i in arr.indices) {
                for (j in i..arr.lastIndex) {
                    if (preMap[arr[j]]?.contains(arr[i]) == true) {
                        arr.swap(i, j)
                    }
                    if (postMap[arr[j]]?.contains(arr[i]) == true) {
                        arr.swap(i, j)
                    }
                }
            }
            arr[arr.size / 2]
        }
}

fun main() {
    with(Day05()) {
        part1(readInput("Day05")).println()
        part2(readInput("Day05")).println()
    }
}
