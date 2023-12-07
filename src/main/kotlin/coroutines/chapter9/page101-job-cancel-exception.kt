package coroutines.chapter9

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            repeat(1_000) { i ->
                delay(100L)
                Thread.sleep(100L)
                println("I'm printing $i ...")
            }
        } catch (e: CancellationException) {
            println(e)
            throw e
        }
    }

    delay(1_100)
    job.cancelAndJoin()
    println("Done")
    delay(1000)
}