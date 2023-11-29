package coroutines.chapter6

import kotlinx.coroutines.*

class NewsRepo() {
  suspend fun getNews(): String {
    delay(500)
    return "News"
  }

  suspend fun getNewSummary(): String {
    delay(2500)
    return "News Summary"
  }
}


fun main2() = runBlocking {
  val repo = NewsRepo()
  println("start1")
  val news = GlobalScope.async { repo.getNews() }
  println("start2")
  val summary = repo.getNewSummary() // async를 사용하지 않으면 병렬성이 없어지고 await()를 호출하지 않아도 값을 출력할 수 있음.
  println("start3")

  println("$summary")
  println("${news.await()}")
}

fun main() = runBlocking {
  val repo = NewsRepo()
  println("${java.time.LocalTime.now()}:before launch: ${Thread.currentThread().name}")
  launch {
    println(repo.getNewSummary())
    println(repo.getNewSummary())
  }
  launch {
    println(repo.getNewSummary())
    println(repo.getNewSummary())
  }
  println("${java.time.LocalTime.now()}:after launch: ${Thread.currentThread().name}")
}
