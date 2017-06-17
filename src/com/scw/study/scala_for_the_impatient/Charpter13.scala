package com.scw.study.scala_for_the_impatient

object Charpter13 {
   def main(args: Array[String]) = {
     println("����")
     
     
   /*
   * 13.1
   * ��дһ������,�����ַ���, ����һ�����������ַ����±��ӳ��. 
   * ������˵,indexes("Mississippi")Ӧ����һ��ӳ��, ��'M'��Ӧ��{0},
   * 'i'��Ӧ��{1,4,7,10}, �Դ�����
   * ʹ���ַ����ɱ伯��ӳ��, ����, ����α�֤���Ǿ��������?
   */
    def question1(str: String): Map[Char, Iterable[Int]] =
    (collection.mutable.Map[Char, Iterable[Int]]() /: (str zip (0 until str.size))) {
      (map, kv) => map += kv._1 -> (map.getOrElse(kv._1, Iterable[Int]()) ++ Iterable(kv._2))
    }.toMap
   }
// 13.1 ��Ҫ�ļ�������
// Iterable ��-> Seq ��-> IndexedSeq //�Ⱥ�����ֵ�����У����������
// Iterable ��-> Set ��-> SortedSet //û���Ⱥ�����ֵ
// Iterable ��-> Map ��-> SortedMap
// Iterableָ������Щ�������������ʼ���������Ԫ�ص�Iterator�ļ��� P166
// ÿ��scala�������ʻ��඼��һ������apply�����İ�������
// 13.2 �ɱ�Ͳ��ɱ伯��
// �������scala����Predef������ָ�򲻿ɱ����ʵ����͵ı���List,Set,Map
// ���Դ����ļ���Ĭ�϶��ǲ��ɱ�ļ��ϣ�ʹ�ÿɱ伯��Ҫ��ʽimport
// 13.3 ����
// ���ɱ䣺 Seq ��(IndexedSeq��(Vector,Range),List,Stream,Stack,Queue)
// �ɱ䣺Seq ��(IndexedSeq��(ArrayBuffer),Stack,Queue,PriorityQueue,LinkedList,DoubleLinkedList)
// 13.4 List
// scala�У�ListҪô��Nil(�ձ�)��Ҫô��һ��headԪ�ؼ���һ��tail,tail����һ��List
   val li = List(4,2) //li.head=4,li.tail=List(2),li.tail.head=2,li.tail.tail=Nil
// ���ǿ����õݹ飬��������ģʽƥ�����List
   def sum(lst:List[Int]):Int = if(lst==Nil) 0 else lst.head+sum(lst.tail) //�ݹ�
   def sum2(lst:List[Int]):Int = lst match{case Nil =>0;case h :: t => h+sum(t)} //ģʽƥ�䣺h=lst.head,t=lst.tail
// 13.5 �ɱ�List
// ��LinkedList��DoubleLinkedList����,��scala 2.11�Ѿ�������
// ʹ�ã����������и�ֵ��0��ȥ��ÿ����Ԫ���е�һ�������޸�ĳ�ڵ�Ϊ���һ���ڵ� P13.6
// 13.6 Set
   scala.collection.mutable.LinkedHashSet("A","B") //���Լ�סԪ�ر������˳��
   scala.collection.immutable.SortedSet(1,2,3) //�������Set,�����ʵ��
   scala.collection.mutable.SortedSet(1,3)
// ����BitSet��
// ���÷���contains,subsetOf,union(|),intersect(&),diff(&~),Ҳ�ɽ�����unionд��++������diffд��--
   Set(1,2) -- Set(4,5) //�����Set(1, 2)
// 13.7 ������ӻ�ȥ��Ԫ�صĲ�����
// P173
// ���ܣ�1.���(:+)����ǰ(+:)׷��Ԫ�� 2.���(+)Ԫ�ص��޴��򼯺� 3.��-�Ƴ�Ԫ�� 4.++��--������Ӻ��Ƴ� 5.����List����ʹ��::��:::
// 6.��ֵ����+=��++=��-=��--=
// 13.8 ���÷���
// Iterable���ʣ�Seq���ʳ��÷���P176
// 13.9 ������ӳ�䵽����
// map�������Խ�ĳ������Ӧ�õ������е�ÿ��Ԫ�ز����������ļ���
   List("a").map(_.toUpperCase) //List("A") �� for(n <- list) yield n.toUpperCaseЧ����ͬ
// ����������һ�����϶����ǵ���ֵ�û�������������е�ֵ������һ�𣬿�����flatMap
   List("a","b").map( s => Vector(s.toUpperCase,s.toLowerCase)) //List(Vector("A","a"),Vector("B","b"))
   List("a","b").flatMap( s => Vector(s.toUpperCase,s.toLowerCase)) //List("A","a","B","b")
// �����ʹ��flatMap�����뷵��Option�ĺ����Ļ������շ��صļ��Ͻ��������е�ֵv��ǰ���Ǻ�������Some(v)
// collect��������ƫ����(partial function)--��Щ��û�ж����п��ܵ�����ֵ���ж���ĺ���������������������в����ĺ���ֵ�ü���
   "-3+4".collect { case '+' => 1;case '-' => -1} //Vector(-1,1)
// �����Ӧ�ú���������Ԫ�ؽ�����Ϊ�˱����������ĺ�������ֵ�Ļ�������foreach
   List("a").foreach(println)
// 13.10 �����۵���ɨ��
// map������һԪ����Ӧ�õ���������Ԫ�أ����з������Խ���Ԫ��������ϼ����е�Ԫ�أ���c.reduceLeft(op)
   List(1,7,2,9).reduceLeft(_ - _) //((1-7)-2)-9 = -17 ͼʾ����P178
   List(1,7,2,9).reduceRight(_ - _) //1-(7-(2-9))=-13 //�Ӽ���β����ʼ
   List(1,7,2,9).foldLeft(0)(_ - _) // ��һ����ʼ��Ԫ�أ����õ� 0-1-7-2-9=-19
   (0 /: List(1,7,2,9))(_ - _) //Ҳ������ôдfoldLeft����
// ��Ӧ�Ļ���foldRight��:\
// �۵���ʱ������Ϊѭ�������ʵ��������ĳ���ַ�������ĸ���ֵ�Ƶ��
   val freq=scala.collection.mutable.Map[Char,Int]()
   for(c <- "Mississippi") freq(c) = freq.getOrElse(c, 0)+1 //freqΪMap(i->4,M->1,s->4,p->2)
// ����˼·����ÿһ������Ƶ��Map������������ĸ�����һ�𣬲���һ���µ�Ƶ��Map,������۵�
   (Map[Char,Int]() /: "Mississippi") {
     (m,c) => m+(c -> (m.getOrElse(c, 0)+1))
   } //���Ǹ����ɱ�Map��ÿһ���������һ���µ�Map
// �κ�whileѭ�����������۵������������һ����ѭ���б����µ����б��������һ������ݽṹ��Ȼ����һ��������ʵ��ѭ���е�һ����
// scanLeft��scanRight�������۵���ӳ����������һ����õ����ǰ��������м����ļ���
   (1 to 10).scanLeft(0)(_ + _) // Vector(0,1,3,6,10,15,21,28,36,45,55)
// 13.11 ��������
// ����(zip)������ָ���ǽ����������໥��Ӧ��Ԫ�ؽ����һ��
   List(5.0,20.0) zip List(10,2) //���List((5.0,10), (20.0,2))
// ����������������ͬ��ȡ��̼���Ԫ������
   List(5.0,20.0,9.95) zip List(10,2) //���List((5.0,10), (20.0,2))
// zipAll��������ָ���϶�List��ȱʡֵ
   List(5.0,20.0,9.95).zipAll(List(10,2),0.0,1) //���List((5.0,10),(20.0,2),(9.95,1))
// zipWinthIndex���ض�ż���б�����ÿ����ż�еڶ�����ɲ�����ÿ��Ԫ�ص��±�
   "Scala".zipWithIndex //���Vector((S,0),(c,1),(a,2),(l,3),(a,4))
   "Scala".zipWithIndex.max._2 //ȡ��������ֵ���±꣬�����3��������ֵ��l
// 13.12 ������
// ʹ��iterator�����Ӽ��ϻ��һ����������Iterable����һЩ�������Բ���������������grouped��sliding
// ʹ��1��while(iter.hasNext) ��iter.next()ִ�в���
// ʹ��2��for(elem <- iter) ��elemִ��ĳ�ֲ���
// ��������ѫ�궼�Ὣ�������ƶ������ϵ�ĩβ���ڴ�֮�����Ͳ����ٱ�ʹ���ˡ�
// Iterator�ඨ����һЩ�뼯�Ϸ���ʹ��������ȫ��ͬ�ķ�������13.8�����б�
// �ڵ���������map,filter,count,sum,length�ŷ�������������λ�ڼ��ϵ�β�ˣ��㲻���ټ���ʹ����������������find��take,������λ�����ҵ�Ԫ�ػ���ȡ��Ԫ��֮��
// �����о��������������鷳������������toArray��toIterable��toSeq��toSet��toMap������Ӧ��ֵ������һ���µļ�����
// 13.13 stream ��
// ����һ��β����������Ĳ��ɱ��б�ֻ�е�����Ҫʱ���Żᱻ����
   def numsFrom(n:BigInt):Stream[BigInt] = n #:: numsFrom(n+1) //#::����һ����
   val tenOrMore = numsFrom(10) //�����Stream(10,?) β����δ����ֵ��
   tenOrMore.tail.tail.tail //�����Stream(13,?)
// ���ķ�������ִ�еģ�����
   val squares = numsFrom(1).map(x => x*x) //�����Stream(1,?)������Ҫ����squares.tailǿ�ƶ���һ��Ԫ����ֵ
   squares.take(5).force //�����Stream(1,4,9,16,25)������ʹ��take+force���������n��ֵ
// ���Դӵ���������һ����������Source.getLines����һ��������������ÿһ��ֻ�ܷ���һ�Σ�������������ʹ����У����������·�������
// val words=scala.io.Source.fromFile("�ļ�").getLines.toStream
// words //Stream(A,?) words(5) //Aachen words //Stream(A,A's,AOL,AOL's,Aachen,?)
// 13.14 ����ͼ
// ����������ִ�еģ������������Ҫʱ�ż��㣬�����ʹ��view�������õ�����Ч�����÷�������һ���䷽�����Ǳ���ִ�еļ���
   import scala.math._
   val powers = (0 until 1000).view.map(pow(10,_)) //����һ��δ����ֵ�ļ��ϣ�����һ��Ԫ�ض�δ����ֵ
   powers(100) //pow(10,100)�����㡣
// ������ͬ����ͼ�������κ�ֵ��ÿ�ε��ö������¼��㡣������force����ǿ����ֵ�������µļ���
// �������ʺϴ�����Ҫ���ַ�ʽ���б任�Ĵ��ͼ��ϣ������Ա��⹹�������м伯��
   (0 to 1000).map(pow(10,_)).map(1 / _) //��������������
   (0 to 1000).view.map(pow(10,_)).map(1 / _).force //��ס������map��������ͼ����ֵʱ������ÿ��Ԫ�أ�����������ͬʱִ�У�����Ҫ�м伯��
// 13.15 ��java���ϵĻ�����
// JavaConversions�����ṩ��������scala��java����֮������ת����һ�鷽��
   import scala.collection.JavaConversions._
   val props:scala.collection.mutable.Map[String,String] = System.getProperties()
// P187 ��13-4��scala��java���ϵ�ת��
// P187 ��13-5��java��scala���ϵ�ת��
// ע�⣬��Щת���������ǰ�װ�����������ʹ��Ŀ��ӿ�������ԭ����ֵ��������˵��props����һ����װ��
// �䷽������õײ��java������������
   props("com.a.b") = "scala"
// ��ô��װ�������õײ�Properties�����put("com.a.b","scala")
// 13.16 �̰߳�ȫ�ļ���
// scala�ṩ�������ʣ��ü��ϵĲ������ͬ����,scala 2.11�汾�Ѿ����Ƽ�ʹ�ã���ʹ��java.util.concurrent���е���
// SynchronizedBuffer SynchronizedMap  SynchronizedPriorityQueue 
// SynchronizedQueue  SynchronizedSet  SynchronizedStack
   val scores=new scala.collection.mutable.HashMap[String,Int] with scala.collection.mutable.SynchronizedMap[String,Int]
// 13.17 ���м���
// ʹ��par�����������ǰ���ϵ�һ������ʵ�֣���ʵ�ֻᲢ�е�ִ�м��Ϸ���
   List(1,2,3,4).par.count(_ % 2 ==0) //���м�����ʽ
   for(i<-(0 until 100).par) print(i + " ") //print ��ӡ�����ֲ���0��100˳���ӡ��
   for(i<-(0 until 100).par) yield i+" "   //��for/yieldѭ���У������������װ�ģ�˳���0��100��
// par�������صĲ��м���������չ��ParSeq��ParSet��ParMap���ʣ��������ʶ���ParIterable�����ͣ�������Iterable������
// ��˲��ܽ����м��ϴ��ݸ�Ԥ��Iterable��Seq��Set��Map�ķ�����������ser���������м���ת���ش��еİ汾��
// Ҳ����ʵ�ֽ���ͨ�õ�GenIterable��GenSeq��GenSet��GenMap���͵Ĳ����ķ���.��󲢲������з��������Բ��л�������reduceLeft�����P190
}