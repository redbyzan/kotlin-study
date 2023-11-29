package coroutines.chapter4

import coroutines.chapter2.randomNumbers
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

data class User(val id: Int, val name: String? = null)

fun getUser(token: String): Int {
  return randomNumbers(System.currentTimeMillis()).first()
}

fun getUserName(userId: Int, token: String): String {
  return User(userId, "Roger").name ?: error("No name")
}

suspend fun printUser(token: String) {
  println("Before")
  val userId: Int = getUser(token)
  println("Got user $userId")
  val userName: String = getUserName(userId, token)
  println(User(userId, userName))
  println("After")
}

// code
fun printUser(token: String, continuation: Continuation<*>): Any {
  val continuation = continuation as? PrintUserContinuation ?: PrintUserContinuation(continuation)

  if (continuation.label == 0) {
    println("Before")
    continuation.label = 1
    if (getUser(token, continuation) == COROUTINE_SUSPENDED) {
      return COROUTINE_SUSPENDED
    }
  }

  if (continuation.label == 1) {
    val userId = continuation.userId
    println("Got user $userId")
    continuation.label = 2
    if (getUserName(userId, token, continuation) == COROUTINE_SUSPENDED) {
      return COROUTINE_SUSPENDED
    }
  }

  if (continuation.label == 2) {
    val userName = continuation.userName
    println(User(continuation.userId, userName))
    println("After")
    return Unit
  }

  error("Impossible")
}

class PrintUserContinuation(
  private val completion: Continuation<Unit>,
  private val token: String
) : Continuation<String> {
  override val context: CoroutineContext
    get() = completion.context

  var label = 0
  private var result: Result<Any>? = null
  var userId: String? = null


  override fun resumeWith(result: Result<String>) {
    this.result = result
    val res = try {
      val r = printUser(token, this)
      if (r == COROUTINE_SUSPENDED) return
      Result.success(r as Unit)
    } catch (e: Throwable) {
      Result.failure(e)
    }
    completion.resumeWith(res)
  }
}


