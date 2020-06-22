package com.example.kotlincombat.chapter7

/**
 * Create by lxx
 * Date : 2020/6/22 9:47
 * Use by
 */
data class Point(var x: Int, var y: Int) {

    //使用委托
    var point : Point by Delegates()


    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }


    override operator fun equals(other: Any?):Boolean{
        if (other === this)return true  // === 运算符不能被重载。
        if (other !is Point)return false
        return this.x == other.x && this.y == other.y
    }
}