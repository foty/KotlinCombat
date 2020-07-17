package com.example.kotlincombat

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

    }

    @Deprecated("remove()", ReplaceWith("removeAt()"))
    fun remove() {

    }


    fun test() {
        remove()//调用时出现废弃标志。
    }
}
