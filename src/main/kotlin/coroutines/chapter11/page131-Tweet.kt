package coroutines.chapter11

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class ApiException(val code: Int, override val message: String = "error!!") : Throwable(message)

suspend fun getUserDetails2(): Details = coroutineScope {
    val name = async { getUserName() }
    val followers = async { getFollowers() }
    Details(name.await(), followers.await())
}

fun main() = runBlocking<Unit> {
    val details = try {
        getUserDetails2()
    } catch (e: ApiException) {
        null
    }
    val tweets = async { getTweets() }
    println("User: $details")
    println("tweets: ${tweets.await()}")
}