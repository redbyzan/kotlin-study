package coroutines.chapter9

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1_00) { i ->
            Thread.sleep(200)
            println("I'm printing $i ...")
        }
    }
    delay(1_000)
    job.cancelAndJoin()
    println("Done")
    delay(1000)
}