package com.scw.study.scala_for_the_impatient
object Charpter07 {
   def main(args: Array[String]) = {
     println("��������")
   }
}

// 7.1 �� 7.2���������
// 1.���������ڲ�������Ƕ��
// 2.Դ�ļ���Ŀ¼�Ͱ�֮��û�й�����ϵ
// 3.��������������һ��֧��Ƕ�ף�����Է����ϲ��������е�����
// 4.���а��ĳ�������Ϊ_root_�����������ð���ͻ���þ��԰�����_root_.x.y.z
// 5.��������x.y.z�����Զ����м��x��x.y��ɿɼ�
   package com{
     package study{ class Emp1 } // ʹ��com.study.Emp1����
     class Emp2
     package st{
       class Emp3{
         var vc = new Emp2 //Emp3���Է����ϲ��Emp2
       }
     }
     package st3.st4.st5{
       //st3.st4�ĳ�Ա�����ﲻ�ɼ�
       package st6{}
     }
   }
// 7.4 �ļ�������Ƿ�
// λ���ļ��������������ŵİ������������ļ���Χ����Ч
// package com.a.b
// package c //��������У��ļ���������������com.a.b.c����com.a.b���������ǿɼ��ģ����Ա�ֱ������
// 7.5 ������
// �������԰�������������Ķ��壨��������ޣ����ѹ��ߺ���������ӵ��������Ƕ����У��Ǹ��Ӻ�������
// ������ĳ�������Ϊ�˽���������.��������Գ��к����ͱ���
// ÿ����������һ����������Ҫ�ڸ����ж��壬�������Ӱ�һ����
   package com.a.b{
     package object c{
       val defaultName = "j.k.l" //ʹ��com.a.b.c.defaultName����
     }
     package c{
       class Person{var name = defaultName} //�Ӱ������õ�����
     }
   }
// ʵ�֣�������ᱻ����ɴ��о�̬�������ֶε�JVM�࣬��package.class,λ����Ӧ����   
// 7.6 ���ɼ���
// ��java�У�û�б�����Ϊpublic��private��protected�����Ա�ڰ�������İ��пɼ���
// ��scala��ʹ��private[����]���η��ﵽjava��Ч�������·��������Լ��İ��пɼ�
   package com.a.b.c{
     class Class1{ private[c] def sc = "he"}
     class Class2{ val c = (new Class1).sc} //���Է���Class1˽�г�Ա
   }
// ��Ҳ���Խ��ɼ�����չ���κΰ�
// 7.7 ����
// ������������������ࡢ����
   import java.awt._ //����ĳ����ȫ����Ա
// 7.8 �κεط���������������
// scala�У�import�����Գ������κ�λ�ã�import����Ч��һֱ���쵽���������Ŀ�ĩβ
// 7.9 �����������ط���
// �����������������������ض���Ա
   import java.awt.{Color,Font} //ʹ��ѡȡ����selector����ֻ�����������г�Ա
   import java.util.{HashMap => JHashMap} //����HashMap��������ΪJHashMap
   import java.util.{HashMap => _,_} //����util�����г�Ա������HashMap
// 7.10 ��ʽ����
// java.lang._��scala._��Predef._���Ǳ�����
