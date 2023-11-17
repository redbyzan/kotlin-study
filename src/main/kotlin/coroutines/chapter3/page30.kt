package coroutines.chapter3

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val executor = Executors.newSingleThreadScheduledExecutor() {
  Thread(it, "scheduler").apply { isDaemon = true }
}

data class User(private val name: String, private val age: Int)

suspend fun requestUser(): User {
  return suspendCoroutine<User> { cont: Continuation<User> ->
    executor.schedule({
      cont.resume(User("roger", 41))
    }, 1_000, TimeUnit.MILLISECONDS)
  }
}

suspend fun main() {
  println("Before")
  val user: User = requestUser()
  println(user)
  println("After")
}