package coroutines.chapter3

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

var continuation: Continuation<Unit>? = null

suspend fun suspendAndSetContinuation() {
  suspendCoroutine<Unit> { cont: Continuation<Unit> ->
    continuation = cont
  }
}

suspend fun main() {
  println("Before")

  suspendAndSetContinuation()
  continuation?.resume(Unit)

  println("After")
}