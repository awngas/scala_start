package com.scw.study.scala_for_the_impatient

object Charpter01 {
   def main(args: Array[String]) = {
     println("����")
   }
/**
*  1.4 �����Ͳ���������
*  Scala��������ʹ���κη�����Ϊ������
*  �����ĵ��ÿ��ԣ� a ���� b �� a.����(b)�ļ�д
*  1.6 apply����
*  ͨ�����ǻ�ʹ�����ƺ������õ��﷨�������ʵ��ԭ����ʵ�ǵ���һ��apply����������:
*/
   "Hello"(4) //������'o'
   "Hello".apply(4) //������ȼ�
   BigInt("123456789")
   BigInt.apply("123456789") //������ȼ�

}