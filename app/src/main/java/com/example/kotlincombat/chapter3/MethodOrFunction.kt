import android.text.TextUtils
import com.example.kotlincombat.chapter3.printUtil
import java.lang.RuntimeException

/**
 * Create by lxx
 * Date : 2020/5/25 14:22
 * Use by 第三章(方法函数定义、调用)
 */
class MethodOrFunction {

    //集合的创建
    fun listC() {
        //创建一个set
        val aSet = setOf(1, 2, 3, 4)

        //创建map
        val map = hashMapOf(1 to "1", 2 to "2", 3 to "3")
        print(map[1])

        //创建list
        val list = listOf(1, 2, 3, 4, 5)
        print(list[1])

    }

    @JvmOverloads // @JvmOverloads注解
    fun fA(a: Int, b: Int, c: Int = 0) {

        printUtil("这个是顶层函数")
    }
    // 顶层属性： 和顶层函数一样，属性也可以放在文件顶层
    // 如果想把一个常量以 public static final 的属性暴露给java，在kotlin中可以使用const。


    //扩展函数与属性

    fun String.sss(): Char {
        return this.get(this.length - 1) //this指接收者对象
    }

    //简写版
    fun String.sss1() = get(length - 1)

    fun test() {
        print("hello".sss())
    }

    //扩展属性
    val String.lastChar: Int
        get() {
            return this.length
        }

    fun testEx() {
        print("hello".lastChar)
    }

    val list = listOf(1, 2, 3, 4, 5)
    fun show() {
        print(list.last())
    }

    //可变参数 vararg
    fun f345(vararg elements: Int) {
        print(elements.size)
    }

    fun testF345() {
        f345(0)
        f345(0, 1, 2)
        f345(0, 2, 3, 4, 5)

        val list: Array<String> = arrayOf("two","three","four")
        val list2 = listOf("one",*list) // *list被称作为解包数组，又被称之为展开运算符
        println(list2)


        val list3: Array<String> = arrayOf("two","three","four")
        val list4 = listOf("one",*list3) //
        print(list4) //这里会出现异常：展开运算符只能展开数组数据，不能展开一个可变长的列表。

    }

    //中缀调用 使用infix修饰
    private infix fun <A, B> A.t2o(that: B): Pair<A, B> = Pair(this, that)

    //三重引号字符串 这样的字符串可以包含换行符，不必用专门的字符；不需要转义符。
    val str = """ fdsf fsd/n\n\n\n/n/n"""


    //局部函数与扩展 (局部函数可以访问所在函数的所有变量和参数)
    fun halfMethod(s1: String, s2: String, s3: String) {

        if (TextUtils.isEmpty(s1)) {
            throw RuntimeException("null")
        }
        if (TextUtils.isEmpty(s2)) {
            throw RuntimeException("null")
        }

        if (TextUtils.isEmpty(s3)) {
            throw RuntimeException("null")
        }

    }
    //===>使用局部函数进化:
    fun halfMethodEx(s1: String, s2: String, s3: String) {

        fun check(value: String) {
            if (TextUtils.isEmpty(value)) {
                throw RuntimeException("$value = null")
            }
        }

        check(s1)
        check(s2)
        check(s3)

    }
}

