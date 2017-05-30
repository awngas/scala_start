package com.scw.study.scala_for_the_impatient

import javafx.scene.web.WebEngine.SelfDisposer

object Charpter10 {
   def main(args: Array[String]) = {
     println("����")
   }

// ���ʿ����ɳ�����ֶκͷ�����Ҳ�����ṩ������ֶκͷ���
// δʵ�ַ���Ĭ���ǳ���ģ�������д���ʳ��󷽷�����Ҫoverride�ؼ��� 
// ʹ��extends(һ��) �� with(���)��Ӷ�������ʣ������ʵ�ֶ������
// 10.4 �������ʵĶ���
// �ڹ��쵥������ʱ�������Ϊ���������
// val ob1 = new SaveClass1 with ConsoleLoger
// 10.5 ����һ�������
   trait Logged{ def log(msg:String){println(msg)} }
   trait TimeLogger extends Logged{ //��ӡʱ������
     override def log(msg:String){ super.log(new java.util.Date()+msg)}
   }
   trait ShortLogger extends Logged{ //��ͼ����log������
     override def log(msg:String){
       super.log( if(msg.length <= 4) msg else msg.substring(0,4)+".." )
     }
   }
   class SavingsAccout extends Account with Logged{
     def wc = log("123456")
   }
// �����е�super.log���಻ͬ��super.log���õ������ʲ㼶�е���һ�����ʣ������������˳�����
// һ����˵�������һ�����ʿ�ʼ����
   val act1 = new SavingsAccout with TimeLogger with ShortLogger
   val act2 = new SavingsAccout with ShortLogger with TimeLogger
   act1.wc //��ӡ��Sat May 27 15:42:27 CST 20171234..
   act2.wc //��ӡ��Sat ..
// 10.6 ����������д���󷽷�
// �������У����Logged.log�ǳ���ģ���ô���뽫�ᱨ��Ҫ����abstract��override�ؼ���
   trait Logged2{def log(msg:String) }
   trait TimeLogger2 extends Logged2{
     abstract override def log(msg:String){ 
       super.log(msg)
     }
   }
// 10.7 �������ӿ�ʹ�õ�����
// �ձ������������������Ӷ����ͬ�ľ��巽�������ǵ���һ�����󷽷������󷽷��ɻ�������ʵ���ʵ�֡��������Ը����󷽷���Ӹ�������ԣ�������־�ּ������ӣ�P124
// 10.8 �����еľ����ֶ�
// ���ʿ����ɾ����ֶΣ�ʹ�ø����ʵ��඼����һ���ֶ���֮��Ӧ����Щ�ֶβ��Ǳ��̳еģ�����ֻ�Ǳ��򵥱��ӵ������൱�У������еľ����ֶο������Ϊ����ֱ�������ж�����ֶΣ������Ǵӳ����м̳еģ�
// 10.9 �����еĳ����ֶ�
// 1.�����еľ��巽������ʹ�ó����ֶ� 2.���������д�����ֶ� 
// 10.10 ���ʹ���˳��
// ����Ҳ�й�����������˳������
// 1.�ȵ��ó���Ĺ�����
// 2.���ʹ������ڳ��๹����֮���๹����֮ǰִ��
// 3.���������ұ�����
// 4.ÿ�����ʵ��У��������ȱ�����
// 5.���������ʹ���һ�������ʣ����������Ѿ������꣬�򲻻��ٴι���
// 6.�������ʹ�����ϣ����౻����
// 10.11 ��ʼ�������е��ֶ�
// ��������ڹ�������ʹ����������Ҫʵ�ֵĳ����ֶ�
   trait Class1{val n:String;println(n)}
   class Class2 extends Class1{ val n = "test1" }
   val test1 = new Class2 //���ӡnull
// val test2 = new Class2 with Class1{ val n = "test2" } ����Ҳ���У������ǽ����˼̳�Class2����������
// ����취��1.ʹ�� ��ǰ���� ���ԣ���ǰ���巢���ڳ���Ĺ���ϵ��֮ǰ
   val test3 = new { val n="test3"} with Class1 //new ֮������ǰ�����
   class Class4 extends { val n="test4"} with Class1 {/*Class4���ʵ��*/} //extends������ǰ�����
// 2.ʹ����ֵ lazy,������ֵÿ��ʹ��ǰ�������Ƿ��Ѿ���ʼ�������Ǻܸ�Ч
// 10.12 ��չ�������
// ����Ҳ������չ�࣬����ཫ���Զ���Ϊ���л�������ʵĳ���
// ������Ѿ���չ����һ���࣬��ô�������������ʵĳ�������ࡣ�����ܻ����������
// trait LoggedException extends Exception with Logged { ... }
// 10.13 ��������
// ������ʹ��this:���� => ��ʼ����ʱ����ô��ֻ�ܱ�����������͵�����
   trait LoggedException extends Logged{
     this:Exception => def log(){log(getMessage())}
   }
// �����ʲ�������չException��,������һ����������Exception.��ֻ�ܱ�����Exception������.
// �����ʵķ�����,���ǿ��Ե��ø����������͵��κη���
// �������Ϳ��Խ�����ʼ��ѭ������(������������������),��������Ҳͬ�����Դ���ṹ����(structural type)
// �ṹ����ֻ���������ӵ�еķ���,�������������.
   trait LoggedException2 extends Logged{
     this:{ def getMessage():String} =>
       def log() { log(getMessage())}
   }
// ������ʿ��Ա������κ�ӵ��getMessage��������
// 10.14 ��������ʲô
// ֻ�г��󷽷������ʱ��򵥵ر��һ��java�ӿ�
// �����о���ķ���,scala������Ǵ�����һ��������,�ð������þ�̬����������ʵķ���
// ��Щ�����಻�����κ��ֶ�,�����е��ֶζ�Ӧ���ӿ��еĳ����getter��setter����,��ĳ����ʵ�ָ�����ʱ,�ֶα��Զ�����.����������һ����ʼ������,�����setter������ʼ��.
// �����ʱ��������ʱ��,�ཫ��õ�һ������getter��setter�Ķ�Ӧ�ֶ�,�Ǹ���Ĺ���������ó�ʼ������.
// ���������չ��ĳ������,������ಢ���̳��������,�ó���ᱻ�κ�ʵ�ָ����ʵ���̳�.
}