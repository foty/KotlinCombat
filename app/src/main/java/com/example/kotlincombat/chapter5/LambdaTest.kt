package com.example.kotlincombat.chapter5

import android.widget.Button

fun main() {

}

/**
 * Create by lxx
 * Date : 2020/6/12 11:02
 * Use by
 */
class LambdaTest {

    val peoples = listOf(Person("A", 18), Person("B", 20))

    /**
     * 使用函数库找到年龄最大的人
     */
    fun findMaxAgePeople() {
        print(peoples.maxBy { it.age })
        print(peoples.maxBy(Person::age))
    }

    fun lambda(button: Button) {

    }

    //这种形式叫做成员引用。表示调用单个方法或者访问单个属性。
    val ages = Person::age

    //使用构造方法引用存储或者延期执行创建类实例的动作。构造方法引用的形式是在双冒号候命指定类名称。
    val createPerson = ::Person
    val p = createPerson("ppp", 19)
    //引用扩展函数


    private fun Person.adult() = age >= 18

    var a = Person().adult()
//    var a2 = Person::adult  //暂时没想明白为什么会报错


    //集合的函数式API

    //filter
    val list = listOf(1, 2, 3, 4, 5)

    fun test(): Unit {
        print(list.filter { it % 2 == 0 })
    }

    //map
    val personList = listOf(
        Person("ab", 18),
        Person("lili", 20)
    )

    fun printPerson(): Unit {
        print(personList.map { it.age })

        print(personList.map(Person::name))
    }

}
