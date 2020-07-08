package com.example.kotlincombat.chapter8

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

}