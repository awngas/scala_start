package com.scw.study.scala_for_the_impatient

object Charpter03 {
   def main(args: Array[String]) = {
     println("������ز���")
   }
// 3.1 ��������,Array��java���鷽ʽʵ��
   val nums = new Array[Int](10)
   val nums2 = Array("A","B")
// 3.2 �䳤����:���黺��
   import scala.collection.mutable.ArrayBuffer
   val b = ArrayBuffer[Int]()
   b += 1
// 3.3 ������������黺��
   for(i <- 0 until nums.length)
     println(i + ": "+nums(i))
// 3.4 ����ת��
// ʹ��for�Ƶ�ʽ����һ���µ�����
// 3.5 �����㷨
   Array(1,6,3).sum //���
   ArrayBuffer(1,3,5).sortWith(_ > _) //����
// 3.7 ��ά����
   val matrix = Array.ofDim[Double](3,4); matrix(0)(0)=42
// 3.8 ��java�Ļ�����
//scalaתjavaʹ����ʽת��
   import scala.collection.JavaConversions.bufferAsJavaList
   import scala.collection.mutable.ArrayBuffer
   val pb = new ProcessBuilder(ArrayBuffer("ls","-l"))
//javaתscala
   import scala.collection.JavaConversions.asScalaBuffer
   import scala.collection.mutable.Buffer
   val cmd:Buffer[String] = pb.command()
}