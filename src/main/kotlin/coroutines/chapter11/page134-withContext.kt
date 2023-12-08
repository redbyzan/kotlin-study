package coroutines.chapter11

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun CoroutineScope.log(msg: String) {
    println("[${coroutineContext[CoroutineName]?.name}] $msg")
}

fun main() = runBlocking(CoroutineName("Parent")) {
    log("Before")

    withContext(CoroutineName("First")) {
        delay(1000)
        log("First")
    }

    withContext(CoroutineName("Second")) {
        delay(1000)
        log("Second")
    }

    log("After")
}