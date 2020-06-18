package com.example.kotlincombat.chapter6

/**
 * Create by lxx
 * Date : 2020/6/18 17:23
 * Use by kt的类型系统
 */
class KtTypeSystem {

    /**
     * 对于一个可能为空的s是不允许调用这个方法的。
     */
    fun strLen(s: String) = s.length

    /**
     * 如果要接收可以为空的参数，则要声明为可控参数。s: String?
     */
    fun strLen2(s: String?) = 5




}