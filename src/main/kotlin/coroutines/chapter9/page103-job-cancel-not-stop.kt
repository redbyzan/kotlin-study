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
            launch {// job이 이미 취소되었기 때문에 실행되지 않는다.
                println("I'm running another coroutine")
            }
            delay(1000) // 중단하려고 하면 예외가 발생
            println("I'm running finally again")
        }
    }
    delay(1000)
    job.cancelAndJoin()
    println("Cancel and join")
}