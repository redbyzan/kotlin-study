package coroutines.chapter7

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MyCustomContext : CoroutineContext.Element {
    override val key: CoroutineContext.Key<*> = Key

    companion object Key : CoroutineContext.Key<MyCustomContext>
}

class CounterContext(private val name: String) : CoroutineContext.Element {
    override val key: CoroutineContext.Key<*> = Key
    private var nextNumber = 0

    fun printNext() {
        println("$name: $nextNumber")
        nextNumber++
    }

    companion object Key : CoroutineContext.Key<CounterContext>
}

suspend fun printNext() {
    coroutineContext[CounterContext]?.printNext()
}

suspend fun main(): Unit = withContext(CounterContext("Outer")) {
    printNext() // Outer: 0
    launch {
        printNext() // Outer: 1
        launch {
            printNext() // Outer: 2
        }

        launch(CounterContext("Inner")) {
            printNext() // Inner: 0
            printNext() // Inner: 1
            launch {
                printNext() // Inner: 2
            }
        }
    }
    delay(50)
    printNext() // Outer: 3
}