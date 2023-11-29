package coroutines.chapter6

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        delay(1000)
        println("World!")
    }
    runBlocking {
        delay(1000)
        println("World!")
    }
    runBlocking {
        delay(1000)
        println("World!")
    }
    println("Hello,")
}