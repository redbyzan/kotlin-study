package coroutines.chapter6

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        delay(1000)
        println("World!")
    }
    println("Hello,")
}