package coroutines.chapter7

import kotlinx.coroutines.*

fun CoroutineScope.log(msg: String) {
    val name = coroutineContext[CoroutineName]?.name
    println("$name: $msg")
}

fun main() {
    parentCoroutineContext()
    println()
    otherCoroutineContext()
}

fun parentCoroutineContext() = runBlocking(CoroutineName("Main")) {
    log("Started main coroutine")
    val v1 = async {
        delay(500)
        log("running async")
        42
    }
    launch {
        delay(1000)
        log("running launch")
    }
    log("The answer for async is ${v1.await()}")
}

fun otherCoroutineContext() = runBlocking(CoroutineName("Other")) {
    log("Started other coroutine")
    val v1 = async(CoroutineName("v1")) {
        delay(500)
        log("running async")
        42
    }
    launch(CoroutineName("launch")) {
        delay(1000)
        log("running launch")
    }
    log("The answer for async is ${v1.await()}")
}