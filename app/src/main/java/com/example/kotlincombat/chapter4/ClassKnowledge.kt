package com.example.kotlincombat.chapter4

import com.example.kotlincombat.chapter2.Food

/**
 * Create by lxx
 * Date : 2020/5/27 15:05
 * Use by 类
 */
class ClassKnowledge() : InterfaceKnowledge, ITest {

    val str: String = "class"

    fun localFun() = 100

    override fun move() {
        super<ITest>.move()
    }

    override fun click() {
//        test.
    }

    //内部类与嵌套类
    // 内部类(隐式存储外部类的引用) 嵌套类(使用了static修饰，不存储外部类的引用)
    // java形式 : class A - 内部类; static class A - 嵌套类。
    // kotlin形式 : class A - 嵌套类; inner class A - 内部类。

    //这是嵌套类
    class A {
        fun f1() {
//            localFun() //这里不能访问到外部方法。
        }
    }

    //这是内部类
    inner class B {

        /**
         * 内部类引用外部类的实例
         */
        private fun getOuter(): ClassKnowledge = this@ClassKnowledge

        fun f2() {
            localFun()

            getOuter().localFun()

            print(getOuter().str)
            print(str)
        }
    }

    //密封类 sealed 配合when表达式使用有奇效。

    //类委托


    //object 的使用
    // 1.使用object 是用单例写法更加简便
    fun testObject() {
        Utils.base_url
        Utils.show()
    }

    // 2.伴生对象 (工厂方法和静态成员的的地盘)。伴生对象成员在子类不能被重写。
    //普通使用
    companion object{
        fun bar(){

        }
    }

    //伴生对象在接口中的使用
//    companion object : JSONFactory<ClassKnowledge> {
//        override fun formJSON(str: String): ClassKnowledge {
//            return ClassKnowledge()
//        }
//    }

    fun <T> loadJSON(factory: JSONFactory<T>) {
        print("===========")
    }

    fun testT() {
//        loadJSON(ClassKnowledge)
    }

    //声明一个伴生对象的扩展函数(注意：为了能够让类能够定义扩展，必须声明一个伴生对象，哪怕他是空的)
    fun ClassKnowledge.Companion.fc1(){

    }

    //3.对象表达式(匿名对象的使用)


}