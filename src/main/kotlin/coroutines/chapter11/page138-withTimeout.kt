package coroutines.chapter11

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.withTimeout

/**
 * withTimeout 함수는 코루틴을 취소하고 TimeoutCancellationException 예외를 발생시킨다.
 * 참고링크
 * https://github.com/FrancescoJo/fj-exercises/blob/master/study/Kotlin%20Coroutine.adoc#week_3
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#timeouts
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#timeout
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#non-cancellable-block
 */

suspend fun test(): Int = withTimeout(1500) {
    delay(1000)
    println("Still thinking...job name = ${coroutineContext.job}")
    delay(1000)
    println("Done!")


    42
}

suspend fun main(): Unit = coroutineScope {
    try {
        test()
    } catch (e: TimeoutCancellationException) {
        println("Caught $e")
        coroutineContext.job.invokeOnCompletion {
            when (it) {
                is TimeoutCancellationException -> println("타임아웃으로 코루틴 종료") /* 타임아웃시 전략 구현 */
                else -> println("왜 실패했지? job name = ${coroutineContext.job}")
            }
        }
    }

    delay(1000) // test 함수가 취소되었기 때문에 타임아웃 시간을 늘려도 아무런 도움이 안됨
}