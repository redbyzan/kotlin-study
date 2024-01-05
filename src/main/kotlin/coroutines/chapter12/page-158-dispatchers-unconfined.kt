package coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

suspend fun main(): Unit = withContext(newSingleThreadContext("Thread1")) {
    var cont: Continuation<Unit>? = null

    launch(newSingleThreadContext("Thread2")) {
        delay(1000)
        cont?.resume(Unit)
    }

    launch(Dispatchers.Unconfined) {
        println(Thread.currentThread().name)

        suspendCancellableCoroutine { continuation ->
            cont = continuation
        }

        println(Thread.currentThread().name)

        delay(1000)

        println(Thread.currentThread().name)
    }
}