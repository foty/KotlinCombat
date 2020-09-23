package com.example.kotlincombat.littledevtips

import java.io.Serializable

/**
 * Create by lxx
 * Date : 2020/9/23 9:20
 * Use by
 */
class Student : Serializable {

    // 通过使用过滤委托TrimDelegate实现自动去除前后多余空格
    var name: String by TrimDelegate()



    fun test() {


    }

}