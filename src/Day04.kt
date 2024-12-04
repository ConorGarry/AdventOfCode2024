import Direction.*

class Day04 {
    private fun createBoard(input: List<String>): Array<CharArray> = input.map { it.toCharArray() }.toTypedArray()

    fun part1(input: List<String>): Int = xmasLineCount(createBoard(input))

    fun part2(input: List<String>): Int = xmasCrossCount(createBoard(input))

    private fun xmasLineCount(board: Array<CharArray>): Int {
        val word = "XMAS"
        val rows = board.size
        val cols = board[0].size
        var count = 0
        val directions = listOf(Up, Down, Left, Right, UpLeft, UpRight, DownLeft, DownRight)

        fun dfs(row: Int, col: Int, index: Int, direction: Pair<Int, Int>): Boolean {
            if (index == word.length) return true
            val newRow = row + direction.first
            val newCol = col + direction.second
            if ((newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols) ||
                (board[newRow][newCol] != word[index])
            ) return false
            return dfs(newRow, newCol, index + 1, direction)
        }

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (board[i][j] == word[0]) {
                    for (direction in directions) {
                        if (dfs(i, j, 1, direction.coords)) {
                            count++
                        }
                    }
                }
            }
        }
        return count
    }

    private fun xmasCrossCount(board: Array<CharArray>): Int {
        val rows = board.size
        val cols = board[0].size
        var count = 0

        fun isXPattern(row: Int, col: Int): Boolean {
            if (row - 1 < 0 || row + 1 >= rows || col - 1 < 0 || col + 1 >= cols) return false
            val topLeft = board[row - 1][col - 1]
            val topRight = board[row - 1][col + 1]
            val bottomLeft = board[row + 1][col - 1]
            val bottomRight = board[row + 1][col + 1]
            if (topLeft == 'A' || topRight == 'A' || bottomLeft == 'A' || bottomRight == 'A') return false
            return topLeft == 'M' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'S' ||
                    topLeft == 'S' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'M' ||
                    topLeft == 'M' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'S' ||
                    topLeft == 'S' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'M'
        }

        // Omit the border cells, as they can't form an X pattern from A.
        for (i in 1 until rows - 1) {
            for (j in 1 until cols - 1) {
                if (board[i][j] == 'A') {
                    if (isXPattern(i, j)) count++
                }
            }
        }

        return count
    }
}

fun main() {
    with(Day04()) {
        part1(readInput("Day04")).println()
        part2(readInput("Day04")).println()
    }
}
