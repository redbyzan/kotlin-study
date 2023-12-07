package coroutines.cahpter10

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object MyNonPropagationException : CancellationException()

suspend fun main(): Unit = coroutineScope {
    launch { // 1
        launch { // 2
            delay(1000)
            println("Will not be printed")
        }
        throw MyNonPropagationException // 3
    }
    launch { // 4
        delay(2000)
        println("Will be printed")
    }
}