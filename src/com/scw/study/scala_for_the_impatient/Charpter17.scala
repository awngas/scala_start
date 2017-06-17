package com.scw.study.scala_for_the_impatient

object Charpter17 {
   def main(args: Array[String]) = {
     println("类型参数")
   }
// 17.1 泛型类
   class Pair[T,S](val f:T,val s:S)
   val p1 = new Pair(42,"1") //调用
   val p2 = new Pair[Any,Any](42,"1")
// 17.2 泛型函数
   def getMiddle[T](a:Array[T]) = a(a.length/2)
   val f = getMiddle[String] _ //这是指定具体类型的函数，保存到f
// 17.3 类型变量界定
// 上界： 类型1 <: 类型2 指定类型1必须是类型2的子类型
// 例子： T必须是Comparable[T]的子类型
   class Pair2[T <: Comparable[T]](val f:T,val s:T){
     def snaller = if (f.compareTo(s) < 0) f else s
   }
// 对于new Pair2(4,2)会报错，因为Int不是Comparable子类型，解决见17.4
// 下界：类型1 >: 类型2 类型1必须是类型2的父类型
   class Pair3[T](val f:T,val s:T){
     def replaceFirst(nf:T) = new Pair3[T](nf,s) //只能是同类型
     def replaceFirst2[R >: T](nf:T) = new Pair3[R](nf,s) //父类也可以
     def replaceFirst3[R >: T](nf:R) = new Pair3(nf,s) //2的简写
     def replaceFirst4[R](nf:R) = new Pair3(nf,s) //不写上界，只会返回Pair[Any]
   }
// 假如有一个Pair3[Student],我们允许用Person替换第一个，当然结果会是一个Pair[Person]
// 17.4 视图界定
// Int没有实现Comparable[Int],但RichInt实现了Comparable[Int]
// 所以使用视图界定，解决17.3中问题
// 视图界定： T <% V 要求必须存在一个从T到V的隐式转换
   class Pair4[T <% Comparable[T]](f:T,s:T)
// 另外Ordered特质比Comparable更好，它在后者的基础上提供了关系操作符
// java.lang.String实现Comparable没有实现Ordered，但是RichString实现了Ordered[String]
   class Pair5[T <% Ordered[T]](f:T,s:T){
     def smaller = if(f < s) f else s
   }
// 17.5 上下文界定
// T:M M是另一个泛型类，它要求必须存在一个类型为M[T]的“隐式值”
   class Pair6[T : Ordered](val f:T,val s:T){
     def smaller(implicit ord:Ordering[T]) =
       if(ord.compare(f, s) < 0) f else s
   }
// 类定义要求必须存在一个类型为Ordering[T]的隐式值，该隐式值可以被用在该类的方法中
// 当声明一个使用隐式值得方法时，需要添加一个"隐式参数"
// 17.6 Manifest上下文界定 P249
// scala编译器是如何使用Manifest[T]对象将存放基本类型的Array[T]转换成虚拟机中的基本类型数组的
// 17.7 多重界定
// 类型变量可以同时有上界和下界，但不能有多个上界或下界
// T >: Lower <: Upper
// 可以要求一个类型实现多个特质
// T <: Comparable[T] with Serializable with Cloneable
// 可以有多个视图界定
// T <% Comparable[T] <% String
// 可以有多个上下文界定
// T : Ordering : Manifest
// 17.8 类型约束
// T =:= U   T是否等于U
// T <:< U   T是否为U的子类型
// T <%< U   T是否被视图(隐式)转换为U
// 要使用约束，需要添加"隐式类型证明参数"
   class Pair7[T](val f:T,val s:T)(implicit ev:T <:< Comparable[T])
// 约束并非內建在语言当中，是scala类库提供的特性，详细见21章
// 约束用途：可以在泛型类中定义只能在特定条件下使用的方法
   class Pair8[T](val f:T,val s:T){
     def smaller(implicit ev:T <:< Ordered[T]) = if(f < s ) f else s
   }
// 你可以构造出Pair[File],但只有调用smaller方法时，才会报错
   val firends = Map("1"->"2")
   val fopt = firends.get("1") //Option[String]
   val frendorNull = fopt.orNull //要么是String,要么是null
// 在和java代码打交道时，orNull方法和有用，java中习惯用null表示缺少某值
// 但不适用于值类型，比如Int,它们不认为null是合法的值。
// 因为orNull实现带有约束，所以你仍然可以实例化Option[Int],只要不对这些实例使用orNull就可以。
// 另一个用途是改进类型推断，比如：
   def firstLast[A,C <: Iterable[A]](it:C) = (it.head,it.last)
// 执行firstLast(List(1,2,3))会报错。
// inferred type arguments [Nothing,List[Int]] do not conform to method firstLast's type parameter bounds [A,C <: Iterable[A]]
// 推断出的类型参数[Nothing,List[Int]]不符合[A,C <: Iterable[A]]，因为它是在同一步骤匹配到A和C的，所以首先匹配C然后匹配A
   class Pair9[A,C](implicit ev:C <:< Iterable[A]){
     def firstLast2[A,C](it:C)(implicit ev: C <:< Iterable[A]) = (it.head,it.last)
   }
// 在12章曾看过类似技巧
// def corresponds[B](that: GenSeq[B])(p: (A,B) => Boolean): Boolean
// p前提是一个柯里化参数，因此类型推断器可以首先判断类型B，然后用这个信心来分析p,
   Array("1","2").corresponds(Array(4,5))( _.length == _)
// 编译器能推断出B是Int，从而理解_.length == _ 是怎么回事
// 17.9 型变
// 有函数： def makeFriends(p:Pair[Person])
// 若Student是Person子类，使用Pair[Student]做参数调用makeFriends却不可以
// 尽管Student是Person子类，但Pair[Student]与Pair[Person]无任何关系,想要这样关系，可以如下定义
   class Pair10[+T](val f:T,val s:T)
// +意味着该类型与T协变的--它与T按同样的方向型变。由于Student是Person子类，那么Pair10[Student]也就是Pair10[Person]的子类型了
// 与之相反的还有逆变
   trait Friend[-T]{
     def befriend(someone:T){}
   }
// Friend[T]表示希望与类型T的人成为朋友的人
// 假设有
   class Person extends Friend[Person]
   class Student extends Person
   val susan = new Student
   val fred = new Person
// 有如下函数 
   def makeFriendWith(s:Student,f:Friend[Student]){f.befriend(s)}
// 你能用Friend[Person]调用他吗？如：
   makeFriendWith(susan,fred)
// 结果是可以的，如果Fred愿意跟任何人成为朋友，那么也一定想要成为susan朋友
// 对这种情况，需要将类型参数声明为逆变的 使用 - 号
// 注意类型变换的方法与子类型方法是相反的，Student是Person子类，
// 但Friend[Student]是Friend[Person]的超类。
// 在一个泛型的类型声明中，可以同时使用这两种型变。
// 举例来说单函数类型Function[-A,+R]
   def friends(students:Array[Student],find:Function1[Student,Person]) = //参数2可写成find:Student => Person
     for(s <- students) yield find(s)
// 假定你有一个函数
   def findStudent(p:Person):Student = { new Student}
// 你可以把findStudent当做参数调用friends吗？当然可以
   friends(Array(new Student),findStudent)
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
}