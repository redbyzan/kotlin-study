package coroutines.chapter3

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

var continuation: Continuation<Unit>? = null

suspend fun suspendAndSetContinuation() {
  suspendCoroutine<Unit> { cont: Continuation<Unit> ->
    continuation = cont
  }
}

suspend fun main() = coroutineScope {
  println("Before")

  launch {
    delay(1000)
    continuation?.resume(Unit)
  }

  suspendAndSetContinuation()
  println("After")
}