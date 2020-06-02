##### KT简述
KT的目标平台: 服务器平台、android平台、甚至是IOS设备上。   
KT的设计哲学:
* 务实
* 简洁
* 安全
* 互操作性

##### KT基础

1. 函数、变量(方法)：  
 ```kotlin
  val a = 4
  var b = 12.00
  fun max(a: Int, b: Int, c: Int): Int {
        return if (a > b) a else c
    }
 ```
 进一步简化:
 ```kotlin
 fun max(a: Int, b: Int, c: Int) = if (a > b) a else c
 ```
 对于表达式函数体来讲，编译器会分析作为函数体的表达式，并且把他的类型作为函数的返回类型，即时他
 没有显示的写出来。这种分析称之为类型推导。
 
 2. 字符串模板
 ```kotlin
  val s = "小平"
System.out.println("$s下午没有来上班")
 ```
 
 
 
 关键字:
 val、var : 变量声明   
 const : ==> public static final    
 vararg : 可变参数  
 infix : (修饰函数)中缀调用  
 open : (kotlin默认是final的)想要被重写的属性或者方法用此修饰  
 inner : kotlin中内部类修饰。  
 sealed : 声明为密封类。   
 data : (修饰类)声明为数据类，自动重写tostring()、hashCode()、equals()方法。   
 object : (修饰类)实现单例效果  
 companion object : 声明为伴生对象。   