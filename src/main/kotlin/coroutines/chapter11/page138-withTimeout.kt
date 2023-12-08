package coroutines.chapter11

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.withTimeout

suspend fun test(): Int = withTimeout(1500) {
    delay(1000)
    println("Still thinking...")
    delay(1000)
    println("Done!")
    42
}

suspend fun main(): Unit = coroutineScope {
    try {
        test()
    } catch (e: TimeoutCancellationException) {
        println("Caught $e")
        coroutineContext.job.invokeOnCompletion { cause ->
            println("Job cancelled due to $cause")
        }
    }

    delay(1000) // test 함수가 취소되었기 때문에 타임아웃 시간을 늘려도 아무런 도움이 안됨
}