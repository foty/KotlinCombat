package com.example.kotlincombat.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {

    //示例1
//    f()

    //示例2
//    f2()

//    f4()

//    f4_1()

//    f6()

    f7()

    f8()
}

private fun f() {
    // 在 GlobalScope 中启动了一个新的协程，这意味着新协程的生命周期只受整个应用程序的生命周期限制。
    GlobalScope.launch {
        // delay是一个特殊的挂起函数，它不会造成线程阻塞，但是会挂起协程，并且只能在协程中使用。
        delay(1000L)
        print("world")
    }
    // 主线程中的代码会立即执行
    print("Hello,")

    //调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
    runBlocking {// 这个表达式阻塞了主线程
        delay(2000L)  // 延迟 秒来保证 JVM 的存活
    }
}

suspend fun f1() {
    val job = GlobalScope.launch {
        delay(1000L)
        print("world")
    }

    job.join()
}

fun f2() = runBlocking {
    launch {
        delay(200L)
        println("hello，")  //第二顺序执行
    }
    coroutineScope {//创建协程作用域
        launch {
            delay(500L)
            println("task has work!")  //第三顺序执行
        }

        delay(100L)
        println("666666")  //第一顺序执行
    }

    println("world")  //最后顺序执行


}

fun f3() = runBlocking {
    launch {
        doF3()
    }
    print("hello")
}

// 挂起函数
suspend fun doF3() {
    delay(100)
    print(", world")
}

// 函数可取消
fun f4() = runBlocking {

    val job = launch {
        repeat(10) { i ->
            println("job:-- $i")
            delay(500L)
        }
    }
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
    job.cancel() // 立即取消该作业，后面看不到输出结果了。
    job.join() // 等待作业执行结束
//    job.cancelAndJoin() // 取消一个作业并且等待它结束，能看到它的所有结果。
    println("main: Now I can quit.")

}

//第二种取消办法
fun f4_1() = runBlocking {
    val job = launch(Dispatchers.Default) {
        while (isActive) {
            println("job:-- $")
            delay(500L)
        }
    }
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消一个作业并且等待它结束，能看到它的所有结果。
    println("main: Now I can quit.")
}

//不能被取消的代码块： finally
/**
 * 任何尝试在 finally 块中调用挂起函数的行为都会抛出 CancellationException，
 * 因为这里持续运行的代码是可以被取消的。当你需要挂起一个被取消的协程，你可以将相应的代码包装在
 * withContext(NonCancellable) {……} 中，并使用 withContext 函数以及 NonCancellable 上下文
 */
fun f5() = runBlocking {
    val job = launch {
        try {
            repeat(10) { i ->
                println(i)
                delay(500L)
            }

        } finally {
            withContext(NonCancellable) {
                delay(1000L)
                print("withContext")
            }
        }
    }
    delay(1300L)
    job.cancelAndJoin()
    println("end")
}


//超时 -> withTimeout / withTimeoutOrNull
/**
 * 使用withTimeout 函数时会抛出一个TimeoutCancellationException的异常，没有在控制台上看到堆栈跟
 * 踪信息的打印。这是因为在被取消的协程中 CancellationException 被认为是协程执行结束的正常原因。
 * 然而，使用 withTimeoutOrNull 通过返回 null 来进行超时操作，从而替代抛出一个异常。
 *
 *
 */
fun f6() = runBlocking {
    val e = withTimeoutOrNull(1500) {
        repeat(50) { i ->
            println(i)
            delay(500L)
        }
    }
    println("e=  $e")
}


// 使用 async 并发
suspend fun sf1(): Int {
    delay(1000)
    return 13
}

suspend fun sf2(): Int {
    delay(1000)
    return 20
}

fun f7() = runBlocking {
    // 测量时间
    val time = measureTimeMillis {
        val i1 = sf1()
        val i2 = sf2()
        println(i1 + i2)
    }
    println(time)  //  time = 2041
}

//下面使用并发改进
fun f8() = runBlocking {
    val time = measureTimeMillis {
        val i1 = async { sf1() }
        val i2 = async { sf2() }
        println(i1.await() + i2.await())
    }
    println(time)  //  time = 1047
}