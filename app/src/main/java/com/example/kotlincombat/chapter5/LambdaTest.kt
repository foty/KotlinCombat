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
    val p = createPerson("ppp", 19, listOf())
    //引用扩展函数

    private fun Person.adult() = age >= 18

    var a = Person().adult()
//    var a2 = Person::adult


    //集合的函数式API

    //filter
    val list = listOf(1, 2, 3, 4, 5)

    fun test(): Unit {
        print(list.filter { it % 2 == 0 })

        print(personList.maxBy { it.age })
    }

    //map
    val personList = listOf(
        Person("ab", 18),
        Person("lili", 20)
    )

    fun printPerson(): Unit {
        print(personList.map { it.age })

        print(personList.map(Person::name))

        //低效率写法
        print(personList.filter { it.age == personList.maxBy { it.age }!!.age })

        val age = personList.maxBy(Person::age)!!.age
        personList.filter { it.age >= age }
    }

    // any all count find
    /**
     * 判断所有元素是否满足条件使用 all
     * 检查是否存在一个满足条件使用 any
     * 判断多少满足了条件的数量使用 count
     * 找到一个能满足条件的元素使用 find
     */

    //判断是否是未成年
    val check = { p: Person -> p.age <= 18 }

    //判断一群人是否是未成年。
    val all = personList.all(check)
    //判断是否存在未成年
    val any = personList.any(check)
    //统计未成年人的数量
    val count = personList.count(check)
    //找到一个未成年人
    val find = personList.find(check)

    // group by 分组。
    val groupList = listOf("a", "b", "bb")
//    val s = groupList.groupBy { String::first }

    public fun String.first(): Char {
        return this[0]
    }

    // 处理集合嵌套的元素：flatMap(需要变换操作时用这个) 与 flatten(不需要变换操作用这个)。

    val sssss = personList.flatMap { it.friends }.toList()

    // 惰性集合操作：序列使用api asSequence() ,原因无他，有时候更加高效而已。
    // 只有当末端操作执行时，中间操作才会执行。
    val seque = personList.asSequence()
        .filter { it.age > 18 }
        .toList()

    // 创建序列。
    val sequeue = generateSequence(0) { it + 1 }


    // lambda函数式编程

    //with函数
    fun testWith() {

        val builder = Builder()
        builder.setAge(12)
        builder.setName("lilili")
        builder.setList(listOf("1", "2", "3"))
        // 使用 with函数 ===》
        with(builder) {
            setList(listOf("1"))
            setName("nahe")
            setAge(18)
        }
    }

    //apply 与with几乎一样，但是apply有返回值
    fun testApply() {
        val builder = Builder()

        // apply 返回值类型就是调用者
        val apply = builder.apply {
            setList(listOf("1"))
            setName("nahe")
            setAge(18)
        }
    }
}
