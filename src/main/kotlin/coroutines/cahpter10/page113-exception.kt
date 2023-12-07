package coroutines.cahpter10

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch {
        launch {
            delay(1000)
            throw Error("Error in child coroutine")
        }

        launch {
            delay(2000)
            println("Will not be printed")
        }

        launch {
            delay(500)
            println("Will be printed")
        }
    }

    launch {
        delay(2000)
        println("Will not be printed")
    }
}