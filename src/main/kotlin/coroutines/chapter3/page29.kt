package coroutines.chapter3

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
  val i: Int = suspendCoroutine<Int> { cont: Continuation<Int> ->
    cont.resume(42)
  }
  println(i)

  val str: String = suspendCoroutine<String> { cont: Continuation<String> ->
    cont.resume("Some text")
  }
  println(str)

  val b: Boolean = suspendCoroutine<Boolean> { cont: Continuation<Boolean> ->
    cont.resume(true)
  }
  println(b)
}