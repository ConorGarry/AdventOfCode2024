import java.awt.Point

class Day06 {

    private fun createMap(input: List<String>) = input.map { it.toCharArray() }.toTypedArray()

    private fun startPos(map: Array<CharArray>) =
        map.mapIndexed { y, row -> row.indexOf('^') to y }.first { it.first != -1 }

    fun part1(input: List<String>): Int {
        val map = createMap(input)
        val startPos = startPos(map)
        return countSteps(map, Point(startPos.second, startPos.first))
    }

    private fun countSteps(map: Array<CharArray>, point: Point): Int {
        var direction = Direction.Up
        val visited = mutableSetOf(point)
        while (point.inBounds(map.size, map[0].size)) {
            val next = point.peek(direction)
            if (!next.inBounds(map.size, map[0].size)) break
            if (map[next.x][next.y] == '#') {
                direction = direction.rotate90()
            } else {
                visited.add(Point(next.x, next.y))
                point.translate(direction)
            }
        }
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val map = createMap(input)
        val startPos = startPos(map)
        val points = mutableListOf<Point>()
        for (row in map.indices) {
            for (col in 0 until map[0].size) {
                if (map[row][col] != '#' && hasLoop(
                        createMap(input).apply { this[row][col] = '#' },
                        Point(startPos.second, startPos.first)
                    )
                ) {
                    points.add(Point(col, row))
                }
            }
        }
        return points.size
    }

    private fun hasLoop(map: Array<CharArray>, point: Point): Boolean {
        var direction = Direction.Up
        val visited = mutableSetOf<Pair<Direction, Pair<Int, Int>>>()
        while (point.inBounds(map.size, map[0].size)) {
            val next = point.peek(direction)
            if (!next.inBounds(map.size, map[0].size)) break
            if (map[next.x][next.y] == '#') {
                direction = direction.rotate90()
            } else {
                val current = next.x to next.y
                if (visited.contains(direction to current)) return true
                visited.add(direction to current)
                point.translate(direction)
            }
        }
        return false
    }
}

fun main() {
    with(Day06()) {
        part1(readInput("Day06")).println()
        part2(readInput("Day06")).println()
    }
}
