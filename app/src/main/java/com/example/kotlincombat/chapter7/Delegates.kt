package com.example.kotlincombat.chapter7

import kotlin.reflect.KProperty


/**
 * Create by lxx
 * Date : 2020/6/22 16:24
 * Use by 关于委托
 */
class Delegates {
    operator fun getValue(point: Point, property: KProperty<*>): Point {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    operator fun setValue(point: Point, property: KProperty<*>, point1: Point) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}