import java.awt.Point
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

enum class Direction(val coords: Pair<Int, Int>) {
    Up(-1 to 0),
    Down(1 to 0),
    Left(0 to -1),
    Right(0 to 1),
    UpLeft(-1 to -1),
    UpRight(-1 to 1),
    DownLeft(1 to -1),
    DownRight(1 to 1),
    None(0 to 0)
}

fun Direction.rotate90(clockWise: Boolean = true) =
    when (this) {
        Direction.Up -> if (clockWise) Direction.Right else Direction.Left
        Direction.Down -> if (clockWise) Direction.Left else Direction.Right
        Direction.Left -> if (clockWise) Direction.Up else Direction.Down
        Direction.Right -> if (clockWise) Direction.Down else Direction.Up
        else -> error("Only cardinal directions can be rotated.")
    }

fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun Point.translate(direction: Direction) {
    val (x, y) = direction.coords
    this.translate(x, y)
}

fun Point.peek(direction: Direction) =
    Point(x + direction.coords.first, y + direction.coords.second)

fun Point.inBounds(width: Int, height: Int) =
    x in 0 until height && y in 0 until width