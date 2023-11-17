package coroutines.chapter3

import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
  println("Before")

  suspendCoroutine<Unit> { continuation ->
    continueAfterSecond(continuation)
  }

  println("After")
}

fun continueAfterSecond(continuation: Continuation<Unit>) {
  thread {
    Thread.sleep(1_000L)
    continuation.resume(Unit)
  }
}