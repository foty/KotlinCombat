package com.example.kotlincombat.chapter9

/**
 * Create by lxx
 * Date : 2020/7/10 11:17
 * Use by
 */
class Herd<out T : Animal> {
    val size: Int
        get() {
            return 1
        }

    operator fun get(int: Int): T {
        val t : T = Animal() as T
        return t
    }
}

