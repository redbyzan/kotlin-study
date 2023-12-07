package coroutines.chapter9

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    val job = launch {
        repeat(1_000) { i ->
            delay(100L)
            Thread.sleep(100L)
            println("I'm printing $i ...")
        }
    }

    delay(1_000)
    job.cancel()
    println("Done")
}