package com.scw.study.scala_for_the_impatient

object Charpter12 {
   def main(args: Array[String]) = {
     println("�߽׺���")
   }
// 12.1 ��Ϊֵ�ú���
// ��scala�У������ǡ�ͷ�ȹ��񡱣��ͺ�����һ����������ڱ����д�ź���
   import scala.math._
   val fun = ceil _ //fun������Ϊ (Double)=>Double
// �Ӽ����Ͻ���_��ceil����ת���˺�������scala�У����޷�ֱ�Ӳ��ݷ�������ֻ��ֱ�Ӳ��ݺ���
// ����ԶԺ������У�1.���� 2.������������ڱ�������Ϊ�������ݸ���һ������
   fun(3.14);
   Array(3.14,1.42,2.0).map(fun)
// 12.2 ��������
// scala�У�����Ҫ��ÿһ����������
   val triple = (x:Double) => 3 * x
   Array(3.14,1.42,2.0).map( (x:Double) => 3 * x ) //�����Array(9.42,4.26,6.0)
// Ҳ���Խ������������ڻ�������
   Array(3.14,1.42,2.0).map{ (x:Double) => 3 * x }
   Array(3.14,1.42,2.0) map { (x:Double) => 3 * x } //������ʹ�����ñ�ʾ��ʱ
// 12.3 �����������ĺ���
   def valueAtOneQuarter(f:(Double) => Double) = f(0.25) //�������ͣ�((Double) => Double) => Double
   valueAtOneQuarter(ceil _) //1.0
   valueAtOneQuarter(sqrt _) //0.5
// ����������д���� (��������) => �������
// ���ܺ��������ĺ������������߽׺�����higher-order function��
// �߽׺���Ҳ���Բ�����һ������
   def mulBy(factor:Double) = (x:Double) => factor * x //���ͣ�(Double) => ((Double) => Double)
// 12.4 ����(����)�ƶ�
// �������������ݸ���һ�������򷽷�ʱ��scala����������ƶϣ����Կ��Խ���һЩ��д
   valueAtOneQuarter((x:Double) => 3 * x)
   valueAtOneQuarter( (x) => 3*x) //ʡ������
   valueAtOneQuarter( x => 3*x) //ֻ��һ�������ĺ���������ʡ��()
   valueAtOneQuarter(3 * _) //���������=>�Ҳ�ֻ����һ�Σ�������_�滻����
// ��Щ��д��ʽ���ڲ���������֪���������Ч
// val fun1 = 3 * _ //�����޷��ƶϳ�����
   val fun2 =3 * (_:Double)
   val fun3:(Double)=>Double = 3*_
// 12.5 һЩ���õĸ߽׺���
   (1 to 9).map(0.1 * _) //���ٲ���0.1,0.2...0.9����
   (1 to 9).map("*" * _).foreach(println _) //���е�����ӡ��*��
   (1 to 9).filter(_ % 2 ==0) //2,4,6,8 ���ƥ���Ԫ��
   (1 to 9).reduceLeft(_ * _) //�������õ������е�����Ԫ�أ������ҡ� ��ͬ��:1*2*3*4*5*6*7*8*9
// 12.6 �հ�
// ��scala�У��������κ��������庯������������������һ�������򷽷����ں������ڣ�����Է��ʵ���Ӧ�������ڵ��κα�����
// �����и����⣺���������ڱ������ٴ�����������ʱ������
   def mulBy2(factor:Double) = (x:Double) => factor * x
   val t1 = mulBy2(3);val t2 = mulBy2(0.5);println(t1(14) + " "+t2(14)) //��ӡ42 7
// t1,t2�к�������factor�ֱ���Ϊ3��0.5����Ȼfactor�ں������غ󣬻���ջ�ϵ�����������ÿһ�����صĺ��������Լ���factor����
// �����ĺ����������հ�(closure)����Щ����ʵ����������Ķ���ʽʵ�ֵģ�������һ��ʵ������factor��һ�������˺������apply����
// 12.7 SAMת��
// scala��֧�ִ����������ĺ���
// java��ͨ���������ǽ���������һ��ʵ��ĳ�ӿڵ����У�Ȼ�󽫸����һ��ʵ�����ݸ���һ���������ܶ�ʱ����Щ�ӿڶ�ֻ�е������󷽷�����java�����Ǳ�����SAM���͡�
// ���磺�ڰ�ť�����ʱ����һ��������
   import javax.swing._
   import java.awt.event.ActionListener
   import java.awt.event.ActionEvent
   var counter =0
   val button = new JButton("��ť")
   button.addActionListener(
       new ActionListener{override def actionPerformed(event:ActionEvent){counter += 1}})
// ���ǿ���ʹ����ʽת������ʹ�ø����ĵ��÷���
   implicit def makeAction(action:(ActionEvent) => Unit) = 
     new ActionListener{override def actionPerformed(event:ActionEvent){action(event)}}
// ʹ��
   button.addActionListener((event:ActionEvent) => counter +=1) //�򵥵���
// 12.8 ���ﻯ��currying��
// ���ﻯָ���ǽ�ԭ���������������ĺ�������µĽ���һ�������ĺ����Ĺ��̣��µĺ�������һ����ԭ�еڶ���������Ϊ�����ĺ���
   def mulOneAtTime(x:Int) = (y:Int) => x*y //���ﻯ����
   mulOneAtTime(6)(7) //����
// scala֧�����¼�д��������ﻯ����
   def mulOneAtTime2(x:Int)(y:Int) = x*y
// ��ʱ������Ҫ�ÿ��ﻯ��ĳ����������������������ṩ�������������ƶϵ���Ϣ
// ���ӣ��Ƚ����������������Ա���������ͬ
   val a=Array("abc","ABC");val b=Array("Abc","Abc");
   a.corresponds(b)(_.equalsIgnoreCase(_)) //_.equalsIgnoreCase(_)Ϊ(a:String,b:String)=>a.equalsIgnoreCase(b)��д
// def corresponds[B](that: GenSeq[B])(p: (A,B) => Boolean): Boolean �÷���������
// 12.9 ���Ƴ���
// �޲����޷���ֵ�����ĵ���
   def runInThread(block:()=>Unit){new Thread{override def run(){block()}}.start()}
// ����
   runInThread {()=>println("Hi");Thread.sleep(1000);println("Bye") }
// ����ô�������ۣ��������¶���
   def runInThread2(block: =>Unit){new Thread{override def run(){block}}.start()}
   runInThread2 { println("Hi");Thread.sleep(1000);println("Bye") }
// ���ǿ��Թ������Ƴ��󣺿���ȥ���Ǳ�����Թؼ��ֵĺ���������until��䣬����ԭ������while
   def until(condition: =>Boolean)(block: =>Unit){
     if(!condition){block;until(condition)(block)}
   }
// ʹ��:
   var x=10
   until(x==10){
     x -= 1;println(x)
   }
// �����ĺ������������������ò������ͳ��������ͬ�������ڱ�����ʱ���������ʽ���ᱻ��ֵ��x==0���ᱻ��ֵ
// 12.10 return���ʽ
// scala����Ҫʹ��return��䷵�غ���ֵ���������������return����һ�����������з���ֵ������������������Ĵ�������
   def indexOf(str:String,ch:Char):Int ={
     var i=0
     until(i==str.length){
       if(str(i)==ch) return i;i+=1 //returnִ��ʱ��indexOf��ֹ������ֵ
     }
     return -1
   }
// ����������̵�ʵ������һ��������������return���ʽ���׳��������쳣�����쳣��until��������������indexOf��������
// ע��:����쳣�ڱ�������������ǰ����try������б������ˣ���ô��Ӧ��ֵ�Ͳ��ᱻ����
}