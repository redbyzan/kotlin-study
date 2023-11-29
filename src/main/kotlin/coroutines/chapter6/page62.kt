package coroutines.chapter6

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
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
    delay(2000)

}