package coroutines.chapter7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val ctx1: CoroutineContext = CoroutineName("Name1")
    println(ctx1[CoroutineName]?.name)
    println(ctx1[Job]?.isActive)

    val ctx2: CoroutineContext = Job()
    println(ctx2[CoroutineName]?.name)
    println(ctx2[Job]?.isActive)

    val ctx3: CoroutineContext = ctx1 + ctx2
    println(ctx3[CoroutineName]?.name)
    println(ctx3[Job]?.isActive)

    println("")
    println("replaceCoroutineName()")
    replaceCoroutineName()

    println("")
    println("emptyContextPlus()")
    emptyContextPlus()
}

fun replaceCoroutineName() {
    val ctx1: CoroutineContext = CoroutineName("Name1")
    println(ctx1[CoroutineName]?.name)

    val ctx2: CoroutineContext = CoroutineName("Name2")
    println(ctx2[CoroutineName]?.name)

    val ctx3: CoroutineContext = ctx1 + ctx2
    println(ctx3[CoroutineName]?.name)
}

fun emptyContextPlus() {
    val empty: CoroutineContext = EmptyCoroutineContext
    println(empty[CoroutineName])
    println(empty[Job])

    val ctxName: CoroutineContext = empty + CoroutineName("Name1") + empty
    println(ctxName[CoroutineName])
}