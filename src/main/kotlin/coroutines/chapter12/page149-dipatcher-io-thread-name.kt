package coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    repeat(1000) {
        launch(Dispatchers.IO) { // 또는 launch(Dispatchers.Default) { 로 변경해도 동일한 결과
            Thread.sleep(200)

            val threadName = Thread.currentThread().name
            println("Running on Thread: $threadName")
        }
    }
}