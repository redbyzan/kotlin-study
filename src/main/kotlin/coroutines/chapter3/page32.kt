package coroutines.chapter3

import kotlin.coroutines.Continuation
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MyException : Throwable("Just an exception")

suspend fun main() {
  try {
    suspendCoroutine<Unit> { cont: Continuation<Unit> ->
      cont.resumeWithException(MyException())
    }
  } catch (e: MyException) {
    println("Caught!")
  }
}