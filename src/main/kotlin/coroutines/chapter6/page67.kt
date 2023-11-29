package coroutines.chapter6

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    this.launch {
        delay(1000)
        println("World!")
    }
    launch {
        delay(1000)
        println("World!")
    }
    println("Hello,")
}