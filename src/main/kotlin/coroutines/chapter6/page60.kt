package coroutines.chapter6

import kotlin.concurrent.thread

fun main() {
    thread(isDaemon = true) {
        Thread.sleep(1000)
        println("World!")
    }
    thread(isDaemon = true) {
        Thread.sleep(1000)
        println("World!")
    }
    thread(isDaemon = true) {
        Thread.sleep(1000)
        println("World!")
    }
    println("Hello,")
    Thread.sleep(2000)
}