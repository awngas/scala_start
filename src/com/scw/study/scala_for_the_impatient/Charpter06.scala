package com.scw.study.scala_for_the_impatient

object Charpter06 {
   def main(args: Array[String]) = {
     println("��6�� ����")
   }
}
// ��6�� ����
// 6.1 ��������
   object Accounts{ private var ls =0 }
// 1.��������ӵ������������ԣ�������չ�����������
// 2.�����������ṩ����������
// 3.��������Ĺ������ڸö����һ�α�ʹ��ʱ����
// 6.2 ��������
// ����ͬ���ĵ�������Ϊ����İ�������
   class Account{ val id = Account.nln() }
   object Account{ private def nln() = 1 } // ��������
// ��Ͱ���������Ի������˽�����ԡ����Ǳ������ͬһ��Դ�ļ���
// 6.4 apply����
// ��ʹ�� Object(����1,..,����N) ʱ��apply���������
// ���ǿ�������İ���������apply����һ���½����࣬��ȥʹ��new Object��������
// 6.5 Ӧ�ó������
// ÿ��Scala���򶼱����һ�������main������ʼ����������ΪArray[String]=>Unit
// Ҳ������չApp���ʣ������������빹������������,args����Ϊ�����в���
   object Hello extends App{ println("hello")}
// ԭ�����ӡ����ʱ���DelayedInit��P73
// 6.6 ö��
// scalaû��ö�����ͣ�ʹ��Enumeration�����ö��
   object Enume extends Enumeration{
     val Aa,Ba = Value;val Ca = Value(0,"St")
   }
// ʹ�ã�Enume.Aa;Enume.values;Enume(0);Enume.withName("Aa")
