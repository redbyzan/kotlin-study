package coroutines.chapter9

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = launch {
        repeat(1_000) { i ->
            delay(200L)
            println("I'm printing $i ...")
        }
    }

    delay(1_100)
    job.cancel()
    job.join()
    println("Done")
}