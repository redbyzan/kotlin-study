package coroutines.chapter7

import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

data class User(val id: String, val name: String)

abstract class UuidProviderContext : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<UuidProviderContext>

    override val key: CoroutineContext.Key<*> get() = Key
    abstract fun uuid(): String
}

class RealUuidProviderContext : UuidProviderContext() {
    override fun uuid(): String = UUID.randomUUID().toString()
}

class FakeUuidProviderContext(private val fakeUuid: String) : UuidProviderContext() {
    override fun uuid(): String = fakeUuid
}

suspend fun nextUuid(): String = checkNotNull(coroutineContext[UuidProviderContext]) {
    "UuidProviderContext not present in the context"
}.uuid()

// test fun
suspend fun makeUser(name: String): User = User(nextUuid(), name)

suspend fun main(): Unit {
    // production
    withContext(RealUuidProviderContext()) {
        println(makeUser("John"))
    }

    // test
    withContext(FakeUuidProviderContext("FAKE_UUID")) {
        val user = makeUser("John")
        println(user)
        assert(User("FAKE_UUID", "John") == user)
    }
}