package coroutines.chapter7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

fun main() {
    val ctx: CoroutineContext = CoroutineName("Name1") + Job()
    ctx.fold("") { acc, element -> "$acc$element" }.also(::println)

    val empty = emptyList<CoroutineContext>()
    ctx.fold(empty) { acc, element -> acc + element }.joinToString().also(::println)
}