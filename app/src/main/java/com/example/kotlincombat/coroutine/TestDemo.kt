package com.example.kotlincombat.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {

    //示例1
//    f()

    //示例2
//    f2()

    //协程取消--------
//    f4()

//    f4_0()

//    f4_1()

    //超时-----------
//    f6()


    //组合挂起函数 async 并发--------
//    f7()

//    f8()

//    f8_1()

    f9()
}

// 1、协程基础写法

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


// 2、协程的取消、超时

// 函数可取消1: cancel 与join
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
//    job.cancelAndJoin() // 合并了cancel()与join(),效果相同。
    println("main: Now I can quit.")

}

// 函数可取消2:  协程的取消是协作的。一段协程代码必须协作才能被取消,如果协程正
// 在执行计算任务，并且没有检查取消的话，那么它是不能被取消的，需要等待计算任务结束才停止
fun f4_0() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
            // 每秒打印消息两次
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
// 等待一段时间
    delay(1300L)
    println("main: I'm tired of waiting!")
// 取消一个作业并且等待它结束
    job.cancelAndJoin() //这里到时要取消协程，但是上面while循环还在执行计算，一共能输出满5次结果才结束。
    println("main: Now I can quit.")
}

/*** 输出结果
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
job: I'm sleeping 3 ...
job: I'm sleeping 4 ...
main: Now I can quit.
 */

// 使计算代码可取消: 将上例条件 while (i < 5) 替换为 while (isActive)
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


//不能被取消的代码块
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
        } finally {  // finally一般在协程被取消的时候执行它们的终结动作：
            withContext(NonCancellable) {//可以在这里处理产生的异常
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


// 3、使用 async 并发
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
    val time = measureTimeMillis { // 普通顺序调用，执行顺序也是按照调用顺序
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
        println(i1.await() + i2.await())  // 使用await()在延期的值上得到它的最终结果
    }
    println(time)  //  time = 1047
}

// 惰性启动的 async
fun f8_1() = runBlocking {
    val time = measureTimeMillis {
        val i1 = async(start = CoroutineStart.LAZY) { sf1() }
        val i2 = async(start = CoroutineStart.LAZY) { sf2() }
        i1.start() //启动第一个
        i2.start() //启动第二个
        println("i1=  ${i1.await()} ,i2=  ${i2.await()}") // 使用await()在延期的值上得到它的最终结果
    }
    println(time) // time = 1031
}

// 4、使用async的结构化并发

// 异步计算并且需要使用延期的值来获得结果。这里的as3() as4()并不是挂起函数，只是表示异步调用sf1()
// 与sf2()，可以在任何地方使用这个函数
fun as3() = GlobalScope.async {
    sf1()
}

fun as4() = GlobalScope.async {
    sf2()
}

fun f9() {
    val time = measureTimeMillis {
        // 协程外面启动异步执行
        val t1 = as3()
        val t2 = as4()
        //但是等待结果必须要调用其他挂起或者阻塞。
        runBlocking {//使用 runBlocking阻塞
            println(" ${t1.await()} ==  ${t2.await()}")
        }
    }
    println(time) // time = 1093
}
// 如果在val t1 = as3()或者val t2 = as4(),t1.await()有逻辑错误产生异常，异常被捕获上报，但是程
// 序as3()还是在继续执行的。对此可以将异步执行内容写进作用域CoroutineScope内，使其结构化并发。(如果
// 函数内部发生了错误，并且它抛出了一个异常，所有在作用域中启动的协程都会被取消。)
// 如果其中一个子协程失败，第一个async以及等待中的父协程都会被取消。


fun f10() = runBlocking{
    val time = measureTimeMillis {
        println("666  ${as5()}")
    }
    println(time)
}

suspend fun as5(): Int = coroutineScope {
    val i1 = async { sf1() }
    val i2 = async { sf2() }
    i1.await() + i2.await()
}


// 5、协程上下文与调度器。


