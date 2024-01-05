package coroutines.chapter12

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main() = coroutineScope {
    repeat(100) {
        launch { // 또는 launch(Dispatchers.Default) { 로 변경해도 동일한 결과
            List(10000) { Random.nextLong() }.maxOrNull()

            val threadName = Thread.currentThread().name
            println("Running on Thread: $threadName")
        }
    }
}