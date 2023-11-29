package coroutines.chapter6

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
  // 이렇게 작성하지 마세요!!
  // async를 launch로 잘못 사용한 경우입니다.
  GlobalScope.async {
    delay(1000)
    println("World!")
  }
  println("Hello,")
  delay(2000)
}
