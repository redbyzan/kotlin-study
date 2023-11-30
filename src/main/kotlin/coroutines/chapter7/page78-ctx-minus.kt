package coroutines.chapter7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

fun main() {
    val ctx: CoroutineContext = CoroutineName("Name1") + Job()
    println(ctx[CoroutineName]?.name)
    println(ctx[Job]?.isActive)

    val ctx2: CoroutineContext = ctx.minusKey(CoroutineName)
    println(ctx2[CoroutineName]?.name)
    println(ctx2[Job]?.isActive)

    val ctx3: CoroutineContext = (ctx + CoroutineName("Name2")).minusKey(CoroutineName)
    println(ctx3[CoroutineName]?.name)
    println(ctx3[Job]?.isActive)
}