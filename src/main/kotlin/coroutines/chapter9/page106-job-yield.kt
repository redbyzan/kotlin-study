package coroutines.chapter9

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1000) {
            Thread.sleep(200)
            yield()
            println("I'm sleeping $it ...")
        }
    }
    delay(1000)
    job.cancelAndJoin()
    println("Done")
    delay(1000)
}