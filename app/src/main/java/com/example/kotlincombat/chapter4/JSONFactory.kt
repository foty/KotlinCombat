package com.example.kotlincombat.chapter4

/**
 * Create by lxx
 * Date : 2020/5/28 16:44
 * Use by
 */
interface JSONFactory<T> {

    fun formJSON(str: String): T

    fun json() = print("解析，解析")
}