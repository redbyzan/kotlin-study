package coroutines.chapter6

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        delay(1000)
        println("World!")
    }
    GlobalScope.launch {
        delay(1000)
        println("World!")
    }
    GlobalScope.launch {
        delay(1000)
        println("World!")
    }
    println("Hello,")
    Thread.sleep(2000)
}