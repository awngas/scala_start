package com.scw.study.scala_for_the_impatient

object Charpter04 {
   def main(args: Array[String]) = {
     println("ӳ���Ԫ��")
   }
// 4.1 ����ӳ��
   val map1 = Map("a" -> 10,"b" -> 5,"c" -> 3) //���ɱ�
   val map2 = scala.collection.mutable.Map("a" -> 10,"b" -> 5) //�ɱ�
   val map3 = new scala.collection.mutable.HashMap[String,Int]
   val map4 = Map(("a",10),("b",5))
// 4.2 ��ȡӳ���е�ֵ
   val value1 = map1("a")
   val value2 = map1.getOrElse("a", 0) //����a���ض�Ӧֵ,���򷵻�0
// 4.3 ����ӳ���е�ֵ
   map2("a") = 5 //����
   map2 += ("d" -> 5,"e" -> 5) //���
   map2 -= "a" //ɾ��
// ��һ�����ɱ��ӳ�������»᷵��һ���µ�ӳ��
   val newMap = map1 + ("f" -> 5)
// 4.4 ����ӳ��
// for((k,v) <- ӳ��) ����k��v
   map1.keySet
   for(v <- map1.values) println(v)
// ��������ֵ��λ��
// for((k,v) <- ӳ��) yield (v,k)
// 4.5 ������ӳ��
// scala.collections.immutable.SortedMap ���ɱ�����ӳ��
// ����Scala(2.9) û�пɱ� ��������ӳ��,������TreeMap
// ������˳����ʼ��� scala.collecton.mutable.LinkedHashMap
// 4.6 ��java�Ļ�����
// ʹ����ʽת��
   import scala.collection.JavaConversions.mapAsScalaMap
   val map5:scala.collection.mutable.Map[String,Int] = new java.util.TreeMap[String,Int]
   import scala.collection.JavaConversions.propertiesAsScalaMap
   val pro:scala.collection.Map[String,String] = System.getProperties() // Properties -> Map
  
   import scala.collection.JavaConversions.mapAsJavaMap
// val font = new java.awt.Font(Map(FAMILY->"b")) //��scala -> java
   
// 4.7 Ԫ��
// tuple Ԫ���ǲ�ͬ���͵�ֵ�ľۼ�
   (1,3.4,"a") //����һ��Ԫ��,����Tuple3[Int,Double,java.lang.String]
   val second1 = (1,2)._2
// ͨ��ʹ��ģʽƥ������ȡԪ�����Ԫ,
   val t = (1,3.4,"v"); 
   val (first,second,third) =t
// val (first,second,_) = t //������������еĲ�������Ҫ,���ڲ���Ҫ�Ĳ���λ��ʹ��_
// 4.8 ��������
// ʹ��zip�����Ѷ�����Ϻϳ�һ��
   val sy = Array("a","b","c")
   val ct = Array(1,2,3)
   val pairs = sy.zip(ct) //Array(("a",1),("b",2),("c",3))
}