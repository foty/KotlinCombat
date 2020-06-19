package com.example.kotlincombat.chapter6

import android.app.Person

/**
 * Create by lxx
 * Date : 2020/6/18 17:23
 * Use by kt的类型系统
 */
class KtTypeSystem {

    //可空性判断

    /**
     * 对于一个可能为空的s是不允许调用这个方法的。
     */
    fun strLen(s: String) = s.length

    /**
     * 如果要接收可以为空的参数，则要声明为可控参数。s: String?
     */
    fun strLen2(s: String?) = 5

    /**
     * 安全调用运算符 ?.
     */
    fun testNull(s: String?) {

        val s0: String? = s?.toUpperCase()

        val s1 = s?.toUpperCase()
        //==>等价于
        val s2 = if (s != null) {
            s.toUpperCase()
        } else {
            null
        }
    }

    /**
     * elvis 运算符 ?:
     * 例: val q = s?:"哈哈哈"  如果是s== null，那么q= "哈哈哈"，否则q= s。
     */
    fun testNull2(s: String?) {

    }

    /**
     * 安全转换 as?。 如果类型无法转换则返回null
     */
    fun testNull3(o: Any?): Boolean {
        val person = o as? Person ?: return false
        return true
    }

    /**
     * 非空断言 !!。如果检查对象为空，则显式抛出异常。
     */
    fun testNull4(s: String?) {

        val s = s!!.length
    }

    /**
     * let 函数。让接收非空参数的函数传入可空参数变得简单。
     */
    fun testLet(string: String?) {
        //strLen() 是不能接受一个可能为空的参数的。
        // strLen(string) //直接传无法通过编译检查。

        // 只有当string != null时才会执行lambda函数，否则什么也不会发生。
        string?.let {
            strLen(string)
        }
    }

    /**
     * 延迟初始化属性。
     */
    lateinit var person: Person


    // kt中的基本数据类型

    //数字转换
    // Unit Any Nothing
    /**
     * Any 是非空类型的。
     * Unit 类似java下的void的意思
     * Nothing 没有任何意义，只有当做函数返回值
     */


    //数组和集合

    /**
     * 只读集合与可变集合。(只读集合不一定是不可变得)
     */
    fun testList() {
        /**
         * 类型：     只读          可变
         * list     listOf()      mutableListOf()、arrayListOf()
         * set      setOf()       mutableSetOf()、hashSetOf()、sortedSetOf()、linkedSetOf()
         * map      mapOf()       mutableMapOf()、hashMapOf()、sortedMapOf()、linkedMapOf()
         */

        val list = listOf(1, 2, 3, 4)
//        list.add(2)  //没有add()方法
        val list2 = arrayListOf(1, 2, 3, 4, 5)
        list2.add(4)
//        list[0] = 5  //内容也不允许修改
        list2[0] = 6
        for (s in list) {
            print(s)
        }
    }

    /**
     * 创建数组
     */
    fun testArray() {
        val i1 = IntArray(5)
        val i3 = BooleanArray(1)


        val i2 = intArrayOf(1, 2, 3, 4, 5)

        val arrayOf = arrayOf("", "", "")
    }

}