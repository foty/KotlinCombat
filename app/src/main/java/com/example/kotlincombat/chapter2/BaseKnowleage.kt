package com.example.kotlincombat.chapter2

import java.util.*

/**
 * Create by lxx
 * Date : 2020/5/19 10:48
 * Use by 第二章(基础知识)。
 */
class BaseKnowleage {

    val a = 9
    val b = "sss"
    val c = 100
    val goodMan: Boolean
        get() {
            return a > c
        }
    var badMan: Boolean = false
        get() {
            return a > c
        }
        set(value) {
            field = value
        }

    fun max(a: Int, b: Int, c: Int): Int {
        return if (a > b) a else c
    }

    //进一步简化
    fun max(a: Int, b: Int) = if (a > b) a else b

    fun method1(): Unit {
        val s = "8989"
        print("$s != 0") //编译后的代码创建了一个StringBuild对象将变量与常量附加上去。
    }

    //枚举

    val color = Color.RED
    fun getColor(color: Color) =
        when (color) {
            Color.RED -> "red"
            Color.BLUE -> "blue"
            Color.YELLOW -> "huangse"
            Color.GREEN -> "绿色"
        }

    fun mixColor(c1: Color, c2: Color) =
        when (setOf(c1, c2)) {
            setOf(Color.GREEN, Color.YELLOW) -> "hahha"
            setOf(Color.BLUE, Color.RED) -> "hahha"
            else -> "null"
        }
    //这里使用setOf()创建包含这2种颜色的实例，它的作用仅仅用来检查这两种颜色是否和另外的两种颜色是否相等。
    //同样可以写成下面的形式(不带参数的when表达式)：

    fun mixColor2(c1: Color, c2: Color) =
        when {
            (c1 == Color.GREEN && c2 == Color.YELLOW) -> "hahha"
            (c1 == Color.BLUE && c2 == Color.RED) -> "hahha"
            else -> "null"
        }

    //迭代区间
    val range = 1..100

    fun yls() {
        for (i in range) {
            print(fizzBuzz(i))
        }

        for (i in 100 downTo 1 step 2) { //倒着计算，步长跨度为2
            print(fizzBuzz(i))
        }

    }

    fun fizzBuzz(i: Int) =
        when {
            i % 3 == 0 -> "fizz"
            i % 5 == 0 -> "buzz"
            i % 15 == 0 -> "fizzbuzz"
            else -> "$i"
        }

    //迭代map
    val map = TreeMap<String, String>()

    fun imap() {
        for (i in 1..5) {
            map["$i"] = "$i"
        }

        for ((key, value) in map) {
            print("[$key = $value]")
        }
    }

    //in 检查是否在区间。
    fun check() = 1 in 1..10

    //kotlin的异常
    fun ex(s: String) {
        try {
            val i = Integer.parseInt(s)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            print("finally")
        }
    }

    //try作为表达式使用。但是跟when if表达式不一样的是它总是要括号包裹起来，没有明确指定返回时，最后一
    // 行代码就是表达式的值。
    fun exTry(s: String): Int {
        return try {
            Integer.parseInt(s)
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

}