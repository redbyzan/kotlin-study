package coroutines.chapter6

import kotlinx.coroutines.*

fun main() = runBlocking {
    val resultDeferred: Deferred<Int> = GlobalScope.async {
        delay(1000)
        42
    }
    val result: Int = resultDeferred.await()
    println(result)
    println(resultDeferred.await())
}