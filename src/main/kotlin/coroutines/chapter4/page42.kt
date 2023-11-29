package coroutines.chapter4

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

const val COROUTINE_SUSPENDED = "foo"

//suspend fun myFunction() {
//  println("Before")
//  var counter = 0
//  delay(1000) // 중단함수
//  counter++
//  println("Counter: $counter")
//  println("After")
//}

fun myFunction(continuation: Continuation<Unit>): Any {
  val continuation: MyFunctionContinuation =
    continuation as? MyFunctionContinuation ?: MyFunctionContinuation(continuation)

  var counter = continuation.counter

  if (continuation.label == 0) {
    println("Before")
    counter = 0
    continuation.counter = counter
    continuation.label = 1
//    if (delay(1000, continuation) == COROUTINE_SUSPENDED) {
//      return COROUTINE_SUSPENDED
//    }
  }

  if (continuation.label == 1) {
    counter = (counter as Int) + 1
    println("Counter: $counter")
    println("After")
    return Unit
  }

  error("Impossible")
}

class MyFunctionContinuation(private val completion: Continuation<Unit>) : Continuation<Unit> {
  override val context: CoroutineContext
    get() = completion.context

  private var result: Result<Unit>? = null
  var label = 0
  var counter = 0

  override fun resumeWith(result: Result<Unit>) {
    this.result = result
    val res = try {
      val r = myFunction(this)
      if (r == COROUTINE_SUSPENDED) return
      Result.success(r as Unit)
    } catch (e: Throwable) {
      Result.failure(e)
    }

    completion.resumeWith(res)
  }
}