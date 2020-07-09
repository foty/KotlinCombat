package com.example.kotlincombat.chapter9

/**
 * Create by lxx
 * Date : 2020/7/9 11:43
 * Use by 泛型。
 */
class genericity {

    //指定泛型的上界。一旦指定，那么就可以将泛型当成上界来使用。默认上界是 Any?


    fun <T : Number> oneHalf(value: T): Double {
        return value.toDouble()
    }

    fun <T> func1(value: T) {
        value?.hashCode() // 默认上界是Any?,需要判空。
    }


    // 1.运行时泛型行为 (檫除与实化类型参数)

    // 2.泛型檫除的好处： 保存到程序内的类型信息更少，程序使用的内存总量变小。

    // 3.声明带实化类型参数的函数(内联函数的类型形参能够被实化且只在内联函数中生效，(意味着可以在运行时
    // 引用具体的类型实参))

    //使用reified 修饰类型参数。
    inline fun <reified T> isA(value: Any) = value is T

    //4.为什么实化只对内联函数起作用?
    /**
     * 编译器将内联函数的字节码插入到每一次调用的位置。每次调用带实化类型参数的函数时，编译器都知道这次调用
     * 用作类型实参的确切类型。因此，编译器能够生成引用作为类型实参的具体类的字节码。
     */


    // 5.使用实化类型参数代替类引用。KT中的 *::clss.java 与 Java中的*.class是等同的。


    // 6.实化类型参数的限制
    /**
     * 使用场景:
     * 用在类型检查或者类型转换中(is、!is、as、!as)
     * 用在KT中的反射API(::class)
     * 获取对应的class类( ::class.java)
     * 作为其他函数的类型参数
     *
     * 禁止使用场景：
     * 创建指定为类型参数的类的实例
     * 调用类型参数类的伴生对象的方法
     * 调用带实化类型参数的函数使用非实化类型参数作为类型参数
     * 把类、属性或者非内联函数的类型参数标记为reified
     */
}