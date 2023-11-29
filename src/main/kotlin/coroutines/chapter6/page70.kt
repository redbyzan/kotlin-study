package coroutines.chapter6

import coroutines.chapter4.User
import kotlinx.coroutines.*

class NetworkUserRepository {
    suspend fun getUser(): User {
        delay(1000)
        return User(1, "Foo")
    }
}

class NetworkNewRepository {
    suspend fun getNews(): List<News> {
        delay(1000)
        return listOf(News("News 1"), News("News 2"))
    }

    suspend fun getNewsSummary(): List<News> {
        delay(1000)
        return listOf(News("News Summary 1"), News("News Summary 2"))
    }
}

data class News(val title: String)

suspend fun present() = coroutineScope {
    val userRepository = NetworkUserRepository()
    val newsRepository = NetworkNewRepository()
    val user: Deferred<User> = async { userRepository.getUser() }
    val news: Deferred<List<News>> = async { newsRepository.getNews() }
    val newsSummary: Deferred<List<News>> = async { newsRepository.getNewsSummary() }
    println("User: $user")
    println("News: $news")
    println("News Summary: $newsSummary")
}

fun main() = runBlocking {
    present()
}
