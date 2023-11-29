package coroutines.chapter6

import kotlinx.coroutines.*

fun main() = runBlocking {
    val res1: Deferred<String> = GlobalScope.async {
        delay(1000)
        "Text 1"
    }
    val res2: Deferred<String> = GlobalScope.async {
        delay(3000)
        "Text 2"
    }
    val res3: Deferred<String> = GlobalScope.async {
        delay(2000)
        "Text 3"
    }
    println(res1.await())
    println(res2.await())
    println(res3.await())
}