package com.scw.study.scala_for_the_impatient

object Charpter08 {
   def main(args: Array[String]) = {
     println("�̳�")
   }
   val a = new Ant
   println(a.env.length)
}


// 8.1 ��չ��
// final��extends�ؼ��ֺ�java��ͬ
// 8.2 ��д����
// ��д����������override����
// ���ó���ķ�����super
// 8.3 ���ͼ���ת��
// ����ĳ�������Ƿ�����ĳ���������༰�����࣬������isInstanceOf������
// ����.isInstanceOf[��]�����������null�򷵻�false
// ����ĳ����������ĳ���ࣨ���������ࣩ��p.getClass==classOf(��)
// scala��java�е����ͼ���ת��
// scala                  #Java
// obj.isInstanceOf[C1]   #obj instanceof C1
// obj.asInstanceOf[C1]   #(C1) obj
// classOf[C1]            #C1.class
// scala��ģʽƥ������ʺ����ͼ���ת��
// p.math{
//   case s:Employee => .. //��s��ΪEmployee����
//   case _ => //p����Employee
// }
// 8.4 �ܱ����ֶκͷ���
// �ɽ��ֶλ򷽷�����Ϊprotected���ó�Ա���Ա��κ�������ʣ�������������λ�ÿ���
// ��java��ͬ��protected��Ա�����������İ����ԣ��ǲ��ɼ��ġ�����Ҫ�ɼ������ð����η�������7�£�
// 8.5 ����Ĺ���
// ֻ�������������Ե��ó������������
// class SubClass1(n:String,a:Int,val s:Double) extends Class1(n,a)
// �öδ��붨����һ�����һ�����ó��๹��������������
// ��scala�У��ڹ������в���super(����)���ó��๹����
// scala�������չjava�࣬�������������������java�����ĳһ�����췽��
   class Class1(x:Int,y:Int,w:Int) extends java.awt.Rectangle(x,y,w,w)
// 8.6 ��д�ֶ�
// scala�ֶ�����һ��˽���ֶκ�getter/setter�������ɵġ�
// ���������һ��ͬ����val�ֶ���дһ��val�򲻴�����def���������һ��˽���ֶκ͹���getter�����������getter������д�˳����getter����
// ���ƣ�1.defֻ����д��һ��def 2.valֻ����д��һ��val�򲻴�����def 3.varֻ����д��һ�������var
// ����дval��def��var
// ��д˭        #��val                                  # ��def       # ��var
// ��дval  #������һ��˽���ֶ�(�볬���ֶ�������ͬ)      # ����                   # var������дgetter/setter�ԣ�ֻ��дgetter�ᱨ��
//          #getter������д����getter����                             # ��javaһ��      # 
// ��дdef  #������һ��˽���ֶΣ�getter������д���෽��                                    #
// ��дvar  #����                                                                                   # ����                  # ���������var�ǳ���Ĳſ���
// 8.7 ��������
// ��java��ͬ�������ͨ���������ж������д�Ĵ����ķ�ʽ����һ������������
   class Class2{
      val v1 = new Class1(1,2,3){
         def gret = "hehehe"
      }
   }
// �����Ͻ����⽫�ᴴ���һ���ṹ���͵Ķ���18�£��������ͱ��ΪPerson{def get:String}
// ����������������Ϊ�������͵Ķ��壺def meet(p:Person{def greeting:String}){ ... }
// 8.8 ������ 8.9 �����ֶ�
// ������ʹ��abstract�ؼ��������࣬��������Ҫ��abstract���Σ���д��������ǳ��󷽷�
// ������д������󷽷�/�ֶΣ�����Ҫʹ��override�ؼ���
// ���˳��󷽷��⣬�����ӵ�г����ֶΣ�û�г�ʼֵ���ֶΣ�
   abstract class Class3{
     def getId:Int //���󷽷�
     val cd:Int //�����ֶΣ�����һ�������getter����
     var name:String //�����ֶΣ����г���getter��setter����
   }
// ����ʵ�ֱ����ṩ������ֶ�
   class Class4 extends Class3{
     val cd = 1; var name = "hh"; def getId = 1
   }
// �������ʱ���������Ͷ�������ֶ� val fr = new Class3{ ... }
// 8.10 ����˳�����ǰ����
// ��������������дval�����ڳ���Ĺ�������ʹ�ø�ֵ�û����������ȷ�����ء����磺   
   class Creature{
     val range:Int =0
     val env:Array[Int] = new Array[Int](range)
   }
   class Ant extends Creature{ override val range = 2}
   
// ����õ�envΪ2�����鵫���������СΪ0
// ��Ҫ��ԭ���ǵ��ø������������д��range��getter����ʱ�������range��û�г�ʼ����ֵΪ0��,��������Ϊ0���������P98
// ����취��
// 1.��val����Ϊfinal���������У�����ȫ�����
// 2.��val����Ϊlazy���������У�����2�£�����ȫ����Ч
// 3.��������ʹ����ǰ�����﷨,����ǰ����ĵȺ��Ҳ�ֻ���������е���ǰ���壬����ʹ�����������ֶλ򷽷���
   class Ant2 extends { override val range=2 } with Creature
// 8.11 scala�̳в㼶
// Any ��������     Any����AnyVal��AnyRef
// AnyVal:����ֵ���͵ĸ���Boolean,Byte,Char,Double,Float,Int,Long,Short,Unit
// AnyRef:���������඼��AnyRef�����࣬����Java��Object����ͬ��ʡ�
// AnyRef��׷����wait��notify/notifyAll,�͵�ͬ��java��synchronized���synchronized����
// ����scala�඼ʵ��ScalaObject����
// Anyʵ��Nothing���ʣ�Nothing���������͵�������,��û��ʵ���������ڷ��ͽṹ
// AnyRefʵ��Null���ʣ�Null�������������͵������ͣ�Null���͵�Ψһʵ����null
// ��Scala�У�void��Unit���ͱ�ʾ��������ֻ��һ��ֵ��()����ȻUnit�����κ��������͵ĳ����ͣ����ǣ���������Ȼ�����κ�ֵ���滻��()
// 8.12 ���������
// ��Scala�У�AnyRef��eq����������������Ƿ�ָ��ͬһ������
