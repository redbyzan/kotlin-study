package coroutines.chapter3

import kotlin.coroutines.suspendCoroutine

suspend fun main() {
  println("Before")

  suspendCoroutine<Unit> { continuation ->
    println("Before too")
  }

  println("After")
}