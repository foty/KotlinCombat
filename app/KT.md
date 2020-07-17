##### KT简述
KT的目标平台: 服务器平台、android平台、甚至是IOS设备上。   
KT的设计哲学:
* 务实
* 简洁
* 安全
* 互操作性

##### 第二章(KT基础)

1. 函数、变量(方法)：  
 ```kotlin
 //变量的声明
  val a = 4
  var b = 12.00
  //函数声明
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
 
 3. if else作为函数表达式
 ```kotlin
  fun max(a: Int, b: Int) = if (a > b) a else b
 ```
 
 4. when表达式(kt中没有switch，而是用when替代了，但是when功能更加强大)
 
 5. 循环(while、for)
 
 6. (range)区间的迭代
 
 7. 迭代map
 
 8. in 检查区间和集合
 
 9. try catch 异常使用(能作为表达式)
 
##### 第三章
 
 1. 集合  
 
 2. 顶层函数、顶层属性概念
 
 3. 扩展函数与扩展属性
 
 4. 可变参数 vararg (展开运算符的使用)
 
 5. 中缀调用 (常见于map的创建)
 
 6. 三重引号字符串
 
 7. 局部函数与局部属性 (函数嵌套函数，局部函数可以访问所在函数的所有变量和参数)
 
 
##### 第四章

 1. 类与接口
 
 2. open、final、abstract修饰符
 
 3. 可见性修饰符 (public protected private internal<模块中可见>)
 
 4. 内部类与嵌套类 (内部类:(隐式存储外部类的引用) 嵌套类:(使用了static修饰，不存储外部类的引用))
 
 5. 密封类 (配合when表达式使用有奇效)
 
 6. 类的构造方法 (主构造，次构造)
 
 7. 初始化语句块 (init{ })
 
 8. 接口声明属性
 
 9. 接口访问器(getter、setter)的可见性
 
 10. 数据类与委托类
 
 11. kt中的对等性 (kt中的 == 是比较对象是否相等)
 
 12. object的使用 (单例写法、伴生对象、对象表达式)
 
 
##### 第五章

 1. lambda概念
 
 2. 集合的函数式
 
 3. 惰性集合操作(概念，用法)
 
 4. lambda的函数式编程
 
 5. with、apply、、


##### 第六章
 
 1. 可空性
 
 2. 基本数据类型 
 
 3. 只读集合与可变集合
 
 4. 数组


##### 第七章

 1. 重载算术运算符(=,-,*,/,%)
 
 2. 重载复合算术运算符(+=,-= *=,/=)
 
 3. 重载一元运算符(+a,-a,--a(a--),++a(a++))
 
 4. 重载比较运算符(==,>,<,>=,<=,!=)
 
 5. 重载集合区间约束(get,set,in,..)
 
 6. 结构声明与组件函数
 
 7. 实现惰性初始化的技术手段(支持属性)
 
 
 ##### 第八章
 1. lambda作为参数的高级函数
 
 2. lambda作为返回值的高级函数
 
 3. 内联函数(with、apply)
 
 4. 从lambda中返回
 
 
 ##### 第九章
 
 1. 泛型上界
 
 2. 泛型檫除
 
 3. 类型参数与实化类型参数
 
 4. 协变
 
 5. 逆变
 
 6. KT中的in、out与Java通配符的<? extends T>、<? super T>


##### 第十章。

 1. @Test(表示测试方法)
 
 2. @Deprecated(声明废弃，平滑过渡到另一个方法或类)
 
 
 
 
 
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
 operator : 用于重载运算符的所有函数都需要用这个修饰。(不是所有的函数都能用这个修饰)  
 by lazy{ } : 延迟初始化   
 inline : 声明为内联函数   
 noinline : 禁止内联   
 reified : 类型参数不会再运行时被擦除  