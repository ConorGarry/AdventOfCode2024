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

fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}