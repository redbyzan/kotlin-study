package coroutines.chapter2

val seq = sequence {
  yield(1)
  yield(2)
  yield(3)
}

val seq2 = sequence {
  println("generating first")
  yield(1)
  println("generating second")
  yield(2)
  println("generating third")
  yield(3)
  println("Done")
}

fun main() {
//  for (num in seq) {
//    println(num)
//  }
//
//  for (num in seq2) {
//    println("The next number is $num")
//  }

  val iterator: Iterator<Int> = seq2.iterator()
  println("starting")
  val first: Int = iterator.next()
  println("First: $first")
  val second: Int = iterator.next()
  println("Second: $second")
}
