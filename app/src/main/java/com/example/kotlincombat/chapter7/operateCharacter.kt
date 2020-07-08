package com.example.kotlincombat.chapter7

/**
 * Create by lxx
 * Date : 2020/6/22 9:46
 * Use by 运算重载符与其他约束
 */


class operateCharacter : Comparable<Point> {

    // 重载运算符

    /**
     * 使用operator修饰了 plus函数就可以使用 + 求和了。
     */
    fun test1() {
        val p1 = Point(10, 20)
        val p2 = Point(1, 1)
        print(p1 + p2)
    }

    /**
     * 还可以写成扩展函数
     */
    operator fun Point.plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y)
    }

    /**
     * 可以重载的二元运算符有：
     *
     *  a + b(加)    plus
     *  a - b(减)    minus
     *  a * b(乘)    times
     *  a / b(除)    div
     *  a % b(模)    mod
     *
     * 重载复合赋值运算符
     *  a+=b         a.plusAssign(b)
     *  a-=b         a.minusAssign(b)
     *  a*=b         a.timesAssign(b)
     *  a/=b         a.divAssign(b) (不支持mod 运算)
     *
     * kotlin 的运算符不支持交换性(交换运算符的左右两边)。即 2*1.5 与 1.5*2是不同，如果需要使这两者
     * 都适用，需要为它定义两个函数。
     *
     * 一般来讲，一个类最好不要同时重写两个类似 plus 与 plusAssign这样的运算符。如果是不可变的，那么就
     * 应该提供一个返回新值的运算(plus)，如果是可以被修改(可变的)的，那么提供一个plusAssign或者类似的即可
     *
     *
     * 重载一元运算符
     *
     * +a      unaryPlus
     * -a      unaryMinus
     * !a      not
     * ++a,a++   inc
     * --a,a--   dec
     *
     *
     * 重载比较运算符
     *
     * ==      equals(比较的是值相等。==运算在KT运算中是转换成equals的，而且还会比较是否为null)
     *
     */

    /**
     * 重载 += 运算符。注意这个返回值必须是Unit
     */
    operator fun Int.plusAssign(other: Int) {
        this.plus(other)
    }

    /**
     * 重载 -= 运算符。
     */
    operator fun Int.minusAssign(other: Int) {
        this.plus(other)
    }

    fun testPlus2() {
        val a = 14
        val b = 10
        print(a.plusAssign(b))

        //集合也支持 +=，-= 这两种方法。读写集合得用使用val修饰。var修饰的不行
        val list = arrayListOf(1, 2)
        var list2 = arrayListOf(3, 4)
        list += 4 //   this.add(element)
        list -= 2 //   this.remove(element)
        list += list2  //  this.addAll(elements)
        list -= list2  //  this.removeAll(elements)

        // 使用只读集合时必须要用var修饰。
        var l2 = listOf(1, 2, 3)
        l2 += 5
        val s = 5
        s.unaryPlus()
    }


    /**
     * 定义返回不同类型的运算符。
     */
    operator fun Char.times(count: Int): String {
        return this.toString().repeat(count)
    }

    /**
     * 重载一元运算符
     */
    operator fun Int.unaryPlus() = this + 5


    /**
     * 重载比较运算符 ===
     *
     * equals 不能被实现为扩展函数，继承至Any类的实现方法始终优先于扩展函数。
     */
    fun testEquals() {
        val p = Point(12, 20)
        // 见Point的equals 方法
        print(p == Point(1, 2))
    }

    /**
     * 排序运算符 <,>,<=,>= 都会转换成使用 compareTo。注意的是compareTo的返回值必须是Int。
     * a < b ====> a.compareTo(b) < 0 。
     *
     * 需要实现Comparable接口，才能重写他的compareTo方法。
     */
    val p: Point = Point(1, 1)

    override fun compareTo(other: Point): Int {
//        return p.x - other.x
        return compareValuesBy(p::x, other::x)
    }


    //集合与区间的约定

    /**
     * 集合与区间使用[]来获取以及修改元素，相应的函数是 get，set
     */

    operator fun Point.set(x: Int, y: Int) {
        //扩大两倍
        this.x = x * 2
        this.y = y * 2
    }

    operator fun Point.get(x: Int, y: Int): Int {
        return this.x * 2
    }

    /**
     * 运算符 in 对应的函数名称是 contains。
     *
     * 区间补充：
     * .. 是闭区间，until是开区间(不包括最后一个元素)
     * rang.first 是区间的下界值，range.last是区间的上界值
     */
    val range = 1..5

    operator fun Point.contains(p: Point): Boolean {
        return p.x in range.first..range.last &&
                p.y in range.first..range.last
    }

    /**
     * .. 运算符对应的函数为 rangeTo
     */


    // 解构声明与组件函数。

    /**
     * 解构声明
     *
     * 场景: 从一个函数中返回多个值。如例子test4()。
     * 对map的遍历就是一种解构声明
     */
    fun test3() {
        val p2 = Point(10, 20)
        val (x, y) = p2  //声明x，y变量，然后使用p2来初始化。
        print(x)
        print(y)

        // 将"name:18"这种格式的字符串转换为对象。
        test4("name:18")
    }

    private fun test4(s: String): Point {
        val (x, y) = s.split('.') //解构声明处理集合。
        return Point(x.toInt(), y.toInt())
    }

    //使用支持属性实现惰性初始化
    private var _s: Point? = null

    val s: Point //实际访问属性
        get() {
            if (_s == null) {
                _s = Point(10, 10)
            }
            return _s!!
        }

    /**
     * 支持属性技术:
     * 有一个属性，使用_s来保存这个值，s来提供对这个属性的访问。_s可以为空，s不能为空。
     */
    fun test5() {
        print(_s)
        print(s)
    }

    /**
     * 使用委托属性实现惰性初始化。
     */
    val s2: Point by lazy { Point(1, 2) }


}