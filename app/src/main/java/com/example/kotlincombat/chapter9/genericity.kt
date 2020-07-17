package com.example.kotlincombat.chapter9

import android.content.Context
import android.content.Intent
import com.example.kotlincombat.R

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

    // 2.泛型檫除 -- 泛型类实例的类型实参在运行时是不被保留的。如创建了一个List<String>并将一些字符
    // 串放入其中，但在运行中你只能看到一个List，不能识别出包含的是那种类型的元素。
    // 好处： 保存到程序内的类型信息更少，程序使用的内存总量变小。


    // 3.声明带实化类型参数的函数(内联函数的类型形参能够被实化且只在内联函数中生效，(意味着可以在运行时
    // 引用具体的类型实参))

    //使用reified 修饰类型参数。
    inline fun <reified T> isA(value: Any) = value is T

    /**
     * activity跳转的优雅写法(内联+扩展形式)
     */
    inline fun <reified T> Context.startActivity() {
        val i = Intent(this, T::class.java)
        startActivity(i)
    }


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

    //7.变型。

    /**
     * 为什么存在变型： 在给函数传递实参时，实参类型是函数期望类型，这是完全安全的。当实参类型与期望类型不一致
     * 时即为变型。就比如列表，实参类型与期望类型可以通过如果接收的是只读列表，可以规定更加具体的元素类型。如果
     * 列表是可修改的，那么函数添加或者修改列表中的元素类型会产生类型不一致的可能性，这是不安全的。
     */

    // 子类型： 任何时候如果需要A类型的值都能够使用B类型的值(当做A的值)，类型B就成为A的子类型。
    // 子类跟子类型不是同一个事物。

    // 型变。
    /**
     * 0.如果A是B的子类型，那么*<A>就是*<B>子类型，子类型化被保留了。
     * 1.在该类型实参没有精确匹配到函数中定义的类型形参时，可以让该类的值作为这些函数的实参传递，也可以
     *   作为这些函数的返回值。
     * 2.型变out只能生产类型，而不能消费。(函数的参数位置成为in位置，返回值位置成为out位置，out关键字只能放
     *   在out位置)。
     * 3.构造方法的参数既不在in位置，也不在out位置。所以即使声明了out，也能在构造方法参数上使用它
     * 4.变型规则只会防止外部使用者对类的误用，不会对类自己的使用产生作用。(如果使用private修饰，
     *   不区分out，in位置)。
     */
    fun feedAll(animals: Herd<Animal>) {
        for (i in 0 until animals.size) {
            animals[i].feed()
        }
    }

    fun takeCareOfCats(cats: Herd<Cat>) {
        for (i in 0 until cats.size) {
            cats[i].cleanFille()

            feedAll(cats) //编译不通过，类型错误:要求是Herd<Animal>。当T使用out修饰后，编译通过
        }
    }


    //8.逆变
    /**
     * 1.如果A是B的子类型，那么*<B>就是*<A>的子类型。类型A、B交换了位置，子类型化被反转了。
     * 2.逆变in只能在in位置，不能在out位置。
     * 3.
     */

    //9. 使用点变型 --> 在类型出现的地方指定变型
    // 在每次使用带类型参数的类型的时候，还可以指定这个类型参数是否可以使用它的子类型或者超类型替换。这就叫做使用点类型。
    // 如下例子:

    fun <T> copyData(
        source: MutableList<T>,
        destion: MutableList<T>
    ) {
        for (item in source) {
            destion.add(item)
        }
    }
    //来源集合与写入集合都是不变型的类型，这是没有问题的，现在要这个方法支持不同类型的列表:

    fun <T : R, R> copyData2(
        source: MutableList<T>,
        destion: MutableList<R>
    ) {
        for (item in source) {
            destion.add(item)
        }
    }

    //来源集合类似是目标集合类型的子类型。类型<Int,Any>是完全可以接受的，Int是Any的子类型。

    fun <T> copyData3(
        source: MutableList<out T>,
        destion: MutableList<T>
    ) {
        for (item in source) {
            destion.add(item)
        }
    }

    //检验copyData3();
    fun testCopyData3(){
        val list1 : MutableList<out Number> = mutableListOf(1,2,3)
        //list1.add(45) list1声明了 out后，不能再使用add方法了，
        val list2 : MutableList<Any> = mutableListOf()
        copyData3(list1,list2)
    }


    // 星投影 --> 使用*代替类型参数。当确切的类形实参是未知的或者不重要的时候，使用星投影语法。
}
