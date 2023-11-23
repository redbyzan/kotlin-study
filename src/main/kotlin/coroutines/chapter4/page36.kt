package coroutines.chapter4

// FIXME: 작동하지 않는 의사코드

/*
suspend fun getUser(): User?
suspend fun setUser(user: User)
suspend fun checkAvailablility(flight: Flight): Boolean

// 자세히들여다보면
fun getUser(continuation: Continuation<*>): Any?
fun setUser(user: User, continuation: Continuation<*>): Any
fun checkAvailability(flight: Flight, continuation: Continuation<*>): Any

suspend fun myFunction() {
// fun myFunction(continuation: Continuation<*>): Any
  println("Before")
  delay(1000)
  println("After")
}

fun myFunction(continuation: Continuation<Unit>): Any {
  val continuation = continuation as? MyFunctionContinuation ?: MyFunctionContinuation(continuation)

  if (continuation.label == 0) {
    println("Before")
    continuation.label = 1
    if (delay(1000, continuation) == COROUTINE_SUSPENDED) {
      return COROUTINE_SUSPENDED
    }
  }

  if (continuation.label == 1) {
    println("After")
    return Unit
  }

  error("Impossible")
}

cont = object: ContinuationImpl(continuation) {
  var result: Any? = null
  var label = 0

  override fun iunvokeSuspend(`$result`: Any?): Any? {
    this.result = `$result`
    return myFunction(this)
  }
}

class MyFunctionContinuation(private val completion: Continuation<Unit>): Continuation<Unit> {
  override val context: CoroutineContext
    get() = completion.context

  var label = 0
  private var result: Result<Any>? = null

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
*/
