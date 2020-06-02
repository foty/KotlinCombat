package com.example.kotlincombat.chapter2

/**
 * Create by lxx
 * Date : 2020/5/19 13:57
 * Use by
 */
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    YELLOW(75, 0, 130),
    BLUE(0, 0, 255);

    fun rgb() = (r * 256 + g) * 256 + b
}