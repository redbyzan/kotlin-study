package coroutines.chapter11

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

data class Details(val name: String, val followers: Int)
data class Tweet(val text: String)

fun getFollowers(): Int =
//    throw Error("Network error")
    throw ApiException(404, "Service not found")


suspend fun getUserName(): String {
    delay(500)
    return "kotlin"
}

suspend fun getTweets(): List<Tweet> {
    return listOf(Tweet("hello"))
}

suspend fun CoroutineScope.getUserDetails(): Details {
    val name = async { getUserName() }
    val followers = async { getFollowers() }
    return Details(name.await(), followers.await())
}

fun main() = runBlocking {
    val details = try {
        getUserDetails()
    } catch (e: Error) {
        null
    }
    val tweets = async { getTweets() }
    println("User: $details")
    println("tweets: ${tweets.await()}")
}