package com.example.kotlincombat.chapter8

import com.example.kotlincombat.chapter5.Person
import java.io.BufferedReader
import java.io.StringReader

/**
 * Create by lxx
 * Date : 2020/7/8 16:11
 * Use by
 */
class SeniorFunction {

    /**
     * 高阶函数定义： 任何以lambda或者函数引用作为参数的函数，或者返回值为lambda或函数引用的
     * 函数，或者两者都满足的函数都是高阶函数。
     */

    //1. 作为参数调用的函数。

    /**
     * 声明一个以函数类型为参数的函数。
     */
    fun func1(f: (Int, Int) -> Int) {
        val s = f(2, 3)
        println(s)
    }

    fun testFunc1() {
        func1 { a, b -> a + b }
        func1 { a, b -> a * b }
    }

    //2. 作为返回值的函数

    /**
     * 声明一个返回值是函数的函数
     */
    fun func2(a: Int): (Int, Int) -> String {
        return { c: Int, d: Int -> (c + d + a).toString() }
    }

    fun testFunc2() {
        // 保存获取到的返回函数
        val f2 = func2(2)
        // 调用这个函数。
        f2(2, 3)
    }

    //3.利用lambda消除重复代码。

    // 有一个列表
    val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOW),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/", 12.0, OS.WINDOW),
        SiteVisit("/", 6.0, OS.IOS),
        SiteVisit("/", 16.0, OS.ANDROID)
    )

    fun testFuns() {
        // 计算ANDROID 的时间
        val time0 = log.getAvgTime1 { it.os == OS.ANDROID }
        val time1 = log.getAvgTime1 { it.os == OS.IOS }
        val time2 = log.getAvgTime1 { it.os in setOf(OS.ANDROID, OS.MAC) }
    }

    // 要计算WINDOW的平均时间
    val avgTime = log.filter { it.os == OS.WINDOW }
        .map(SiteVisit::duration)   //{ it -> it.duration }
        .average()

    // 升级版-> 需要计算各种系统的平均时间。(将这个函数变换成扩展函数是一种不错的方案)
    fun List<SiteVisit>.getAvgTime(os: OS) =
        filter { it.os == os }.map(SiteVisit::duration).average()

    // 再升级-> 需要计算移动平台(IOS,ANDROID)的平均时间
    fun List<SiteVisit>.getAvgTime1(os: OS) =
        filter { it.os in setOf(OS.IOS, OS.ANDROID) }.map(SiteVisit::duration).average()

    //上面通用化：将过滤条件转换成参数
    fun List<SiteVisit>.getAvgTime1(can: (SiteVisit) -> Boolean) =
        filter(can).map(SiteVisit::duration).average()


    //4.内联函数(消除lambda带来的运行时开销)，如： with，apply。
    // 所谓内联，即是将内联的代码直接拷贝到它的使用位置，并将lambda替换到其中。


    // 例子2：
    fun testFuns2() {

        log.filter { it.os == OS.MAC }.asSequence().map(SiteVisit::duration).average()
    }
    /**
     * testFuns2 分析：
     *  filter是被声明为内联函数的，map也是，所以他们的函数体会被内联，不会产生额外的对象和类。但是上面会有
     *  一个中间集合来保存过滤后的结果。filter函数会将过滤的元素添加到这个结果，map函数会充这个集合读取元素。
     *  如果有大量元素需要处理，那这个中间集合的开销将不能忽视。处理方案：在调用链后面添加asSequence()的调用
     *  ，将集合转变成序列。但是不能总使用asSequence(),这只能在处理大集合中使用。
     */


    // 4.何时需要将函数内联。
    /**
     * inline 只能提高带有lambda的函数的性能。
     * 情况1： 需要内联的函数不大，可以使用内联。
     */

    fun func3() {
        val sr = StringReader("www")
        val bf = BufferedReader(sr)
        bf.use { }   // use是一个内联函数
    }

    // 5.lambda中的返回语句
    // 5.1 从函数返回
    /**
     * 如果在一个lambda中使用return中返回。它会直接从调用lambda的函数中返回。这样的语句叫做非局部返回。
     * 但是只有在以lambda作为参数的函数式内联函数才可以非局部返回。在一个非内联的lambda中使用return是不允许
     * 的。
     */
    fun func4() {
        val s = log.forEach {
            if (it.os == OS.MAC) {
                return  //这里直接从整个func4()返回
            }
            print(it.os)
        }
        println("77777")
    }

    // 5.2 从lambda返回 --使用标签返回

    fun func5() {
        log.forEach {
            if (it.os == OS.MAC) {
                return@forEach //这里直接从整个func4()返回
            }
            print(it.os)
        }
        println("77777")
    }

    // 5.3 匿名函数默认局部返回
    fun func6(person: List<Person>) {

        log.forEach(fun(person) { //使用匿名函数代替lambda

            if (person.os == OS.MAC) return //只会从匿名函数中返回，最后一句打印代码还是会执行
            println("person.os = ${person.os}")
        })
        println("77777")
    }

}

