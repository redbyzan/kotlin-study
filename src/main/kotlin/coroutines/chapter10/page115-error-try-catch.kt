package coroutines.chapter10

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    // try-catch 구문으로 래핑하지 마세요. 무시됩니다.
    try {
        launch {
            delay(1000)
            throw Error("Some error")
        }
    } catch (e: Throwable) { // 여기선 아무 도움이 되지 않습니다.
        println("Will not be printed")
    }
    launch {
        delay(2000)
        println("Will not be printed")
    }
}