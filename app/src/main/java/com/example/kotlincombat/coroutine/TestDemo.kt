package com.example.kotlincombat.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    GlobalScope.launch {
        delay(1000L)
        print("world")
    }
    // 主线程中的代码会立即执行
    println("Hello,")

    //调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
    runBlocking {     // 这个表达式阻塞了主线程
        delay(2000L)  // 延迟 2 秒来保证 JVM 的存活
    }


}


suspend fun ss(){
    val job = GlobalScope.launch {
        delay(1000L)
        print("world")
    }

    job.join()
}
