package coroutines.chapter9

import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(1000)
            println("Coroutine is running ...")
        } finally {
            println("I'm running finally")
            withContext(NonCancellable) {
                delay(1000)
                println("Cleanup done")
            }
        }
    }

    delay(100)
    job.cancelAndJoin()
    println("Done")
}