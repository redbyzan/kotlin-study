package coroutines.chapter2

import java.math.BigInteger
import java.util.*

val fibonacci: Sequence<BigInteger> = sequence {
  var first: BigInteger = 0.toBigInteger()
  var second: BigInteger = 1.toBigInteger()

  while (true) {
    yield(first)
    val temp = first
    first += second
    second = temp
  }
}

fun main() {
//  print(fibonacci.take(10).toList())
  println("randomUniqueStrings")
  val message = randomUniqueStrings(5)
  print(message.take(5).toList())
}

fun randomNumbers(seed: Long = System.currentTimeMillis()): Sequence<Int> = sequence {
  val random: Random = Random(seed)
  while (true) {
    yield(random.nextInt())
  }
}

fun randomUniqueStrings(length: Int, seed: Long = System.currentTimeMillis()): Sequence<String> =
  sequence {
    val random: Random = Random(seed)
    val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    while (true) {
      val randomString: String = (1..length)
        .map { i -> random.nextInt(charPool.size) }
        .map(charPool::get)
        .joinToString("")
      yield(randomString)
    }
  }.distinct()