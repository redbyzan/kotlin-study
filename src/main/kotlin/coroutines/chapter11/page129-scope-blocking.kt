package coroutines.chapter11

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val a = coroutineScope {
        delay(1000)
        10
    }
    println("a is calculated: $a")

    val b = coroutineScope {
        delay(1000)
        20
    }

    println(a)
    println(b)
}