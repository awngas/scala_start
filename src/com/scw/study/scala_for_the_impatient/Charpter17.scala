package com.scw.study.scala_for_the_impatient

object Charpter17 {
   def main(args: Array[String]) = {
     println("���Ͳ���")
   }
// 17.1 ������
   class Pair[T,S](val f:T,val s:S)
   val p1 = new Pair(42,"1") //����
   val p2 = new Pair[Any,Any](42,"1")
// 17.2 ���ͺ���
   def getMiddle[T](a:Array[T]) = a(a.length/2)
   val f = getMiddle[String] _ //����ָ���������͵ĺ��������浽f
// 17.3 ���ͱ����綨
// �Ͻ磺 ����1 <: ����2 ָ������1����������2��������
// ���ӣ� T������Comparable[T]��������
   class Pair2[T <: Comparable[T]](val f:T,val s:T){
     def snaller = if (f.compareTo(s) < 0) f else s
   }
// ����new Pair2(4,2)�ᱨ����ΪInt����Comparable�����ͣ������17.4
// �½磺����1 >: ����2 ����1����������2�ĸ�����
   class Pair3[T](val f:T,val s:T){
     def replaceFirst(nf:T) = new Pair3[T](nf,s) //ֻ����ͬ����
     def replaceFirst2[R >: T](nf:T) = new Pair3[R](nf,s) //����Ҳ����
     def replaceFirst3[R >: T](nf:R) = new Pair3(nf,s) //2�ļ�д
     def replaceFirst4[R](nf:R) = new Pair3(nf,s) //��д�Ͻ磬ֻ�᷵��Pair[Any]
   }
// ������һ��Pair3[Student],����������Person�滻��һ������Ȼ�������һ��Pair[Person]
// 17.4 ��ͼ�綨
// Intû��ʵ��Comparable[Int],��RichIntʵ����Comparable[Int]
// ����ʹ����ͼ�綨�����17.3������
// ��ͼ�綨�� T <% V Ҫ��������һ����T��V����ʽת��
   class Pair4[T <% Comparable[T]](f:T,s:T)
// ����Ordered���ʱ�Comparable���ã����ں��ߵĻ������ṩ�˹�ϵ������
// java.lang.Stringʵ��Comparableû��ʵ��Ordered������RichStringʵ����Ordered[String]
   class Pair5[T <% Ordered[T]](f:T,s:T){
     def smaller = if(f < s) f else s
   }
// 17.5 �����Ľ綨
// T:M M����һ�������࣬��Ҫ��������һ������ΪM[T]�ġ���ʽֵ��
   class Pair6[T : Ordered](val f:T,val s:T){
     def smaller(implicit ord:Ordering[T]) =
       if(ord.compare(f, s) < 0) f else s
   }
// �ඨ��Ҫ��������һ������ΪOrdering[T]����ʽֵ������ʽֵ���Ա����ڸ���ķ�����
// ������һ��ʹ����ʽֵ�÷���ʱ����Ҫ���һ��"��ʽ����"
// 17.6 Manifest�����Ľ綨 P249
// scala�����������ʹ��Manifest[T]���󽫴�Ż������͵�Array[T]ת����������еĻ������������
// 17.7 ���ؽ綨
// ���ͱ�������ͬʱ���Ͻ���½磬�������ж���Ͻ���½�
// T >: Lower <: Upper
// ����Ҫ��һ������ʵ�ֶ������
// T <: Comparable[T] with Serializable with Cloneable
// �����ж����ͼ�綨
// T <% Comparable[T] <% String
// �����ж�������Ľ綨
// T : Ordering : Manifest
// 17.8 ����Լ��
// T =:= U   T�Ƿ����U
// T <:< U   T�Ƿ�ΪU��������
// T <%< U   T�Ƿ���ͼ(��ʽ)ת��ΪU
// Ҫʹ��Լ������Ҫ���"��ʽ����֤������"
   class Pair7[T](val f:T,val s:T)(implicit ev:T <:< Comparable[T])
// Լ�����ǃȽ������Ե��У���scala����ṩ�����ԣ���ϸ��21��
// Լ����;�������ڷ������ж���ֻ�����ض�������ʹ�õķ���
   class Pair8[T](val f:T,val s:T){
     def smaller(implicit ev:T <:< Ordered[T]) = if(f < s ) f else s
   }
// ����Թ����Pair[File],��ֻ�е���smaller����ʱ���Żᱨ��
   val firends = Map("1"->"2")
   val fopt = firends.get("1") //Option[String]
   val frendorNull = fopt.orNull //Ҫô��String,Ҫô��null
// �ں�java����򽻵�ʱ��orNull���������ã�java��ϰ����null��ʾȱ��ĳֵ
// ����������ֵ���ͣ�����Int,���ǲ���Ϊnull�ǺϷ���ֵ��
// ��ΪorNullʵ�ִ���Լ������������Ȼ����ʵ����Option[Int],ֻҪ������Щʵ��ʹ��orNull�Ϳ��ԡ�
// ��һ����;�ǸĽ������ƶϣ����磺
   def firstLast[A,C <: Iterable[A]](it:C) = (it.head,it.last)
// ִ��firstLast(List(1,2,3))�ᱨ��
// inferred type arguments [Nothing,List[Int]] do not conform to method firstLast's type parameter bounds [A,C <: Iterable[A]]
// �ƶϳ������Ͳ���[Nothing,List[Int]]������[A,C <: Iterable[A]]����Ϊ������ͬһ����ƥ�䵽A��C�ģ���������ƥ��CȻ��ƥ��A
   class Pair9[A,C](implicit ev:C <:< Iterable[A]){
     def firstLast2[A,C](it:C)(implicit ev: C <:< Iterable[A]) = (it.head,it.last)
   }
// ��12�����������Ƽ���
// def corresponds[B](that: GenSeq[B])(p: (A,B) => Boolean): Boolean
// pǰ����һ�����ﻯ��������������ƶ������������ж�����B��Ȼ�����������������p,
   Array("1","2").corresponds(Array(4,5))( _.length == _)
// ���������ƶϳ�B��Int���Ӷ����_.length == _ ����ô����
// 17.9 �ͱ�
// �к����� def makeFriends(p:Pair[Person])
// ��Student��Person���࣬ʹ��Pair[Student]����������makeFriendsȴ������
// ����Student��Person���࣬��Pair[Student]��Pair[Person]���κι�ϵ,��Ҫ������ϵ���������¶���
   class Pair10[+T](val f:T,val s:T)
// +��ζ�Ÿ�������TЭ���--����T��ͬ���ķ����ͱ䡣����Student��Person���࣬��ôPair10[Student]Ҳ����Pair10[Person]����������
// ��֮�෴�Ļ������
   trait Friend[-T]{
     def befriend(someone:T){}
   }
// Friend[T]��ʾϣ��������T���˳�Ϊ���ѵ���
// ������
   class Person extends Friend[Person]
   class Student extends Person
   val susan = new Student
   val fred = new Person
// �����º��� 
   def makeFriendWith(s:Student,f:Friend[Student]){f.befriend(s)}
// ������Friend[Person]���������磺
   makeFriendWith(susan,fred)
// ����ǿ��Եģ����FredԸ����κ��˳�Ϊ���ѣ���ôҲһ����Ҫ��Ϊsusan����
// �������������Ҫ�����Ͳ�������Ϊ���� ʹ�� - ��
// ע�����ͱ任�ķ����������ͷ������෴�ģ�Student��Person���࣬
// ��Friend[Student]��Friend[Person]�ĳ��ࡣ
// ��һ�����͵����������У�����ͬʱʹ���������ͱ䡣
// ������˵����������Function[-A,+R]
   def friends(students:Array[Student],find:Function1[Student,Person]) = //����2��д��find:Student => Person
     for(s <- students) yield find(s)
// �ٶ�����һ������
   def findStudent(p:Person):Student = { new Student}
// ����԰�findStudent������������friends�𣿵�Ȼ����
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