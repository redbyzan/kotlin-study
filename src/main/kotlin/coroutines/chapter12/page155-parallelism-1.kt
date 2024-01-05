package coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val dispatcher = Dispatchers.IO.limitedParallelism(1)

    repeat(10_000) {
        launch(dispatcher) {
            i++
        }
    }

    delay(1000)
    println(i)
}