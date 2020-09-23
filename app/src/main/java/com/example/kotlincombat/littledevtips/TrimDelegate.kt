package com.example.kotlincombat.littledevtips

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TrimDelegate : ReadWriteProperty<Student, String> {

    private var trim: String = ""

    override fun getValue(thisRef: Student, property: KProperty<*>): String {
        return trim.trim()
    }

    override fun setValue(thisRef: Student, property: KProperty<*>, value: String) {
        trim = value.trim()
    }

}
