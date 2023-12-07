package coroutines.chapter9

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(1000)
            println("I'm printing ...")
        } finally {
            println("I'm running finally")
        }
    }

    delay(1_100)
    job.cancelAndJoin()
}