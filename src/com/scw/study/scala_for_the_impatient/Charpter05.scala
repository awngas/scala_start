package com.scw.study.scala_for_the_impatient

object Charpter05 {
   def main(args: Array[String]) = {
     val aa = new Counter();
     aa.age1 = 10
     aa.current1
     
     println("��")
     
     class Test(n:String){
       import scala.beans.BeanProperty
       @BeanProperty var name1:String = _
     }
     val a = new Test("_")
     println(a.name1)
   }
}

// 5.1 ������޲η���
   class Counter{ //���ǹ��пɼ���
     private var value = 0 //�ֶα����ʼ��
     def increment() {value += 1} //����Ĭ���ǹ��е�
     def current() = value
// �����޲η�����,����д����Ҳ���Բ�д
// �����巽��ʱ����(),����ú���ʱǿ�Ʋ���Բ����
   def current1 = value //myCounter.current1
// 5.2 ��getter��setter������
// scala��ÿ���ֶζ��ṩgetter��setter����
// ����:�¾������˽��age�ֶκ�getter��setter����
   var age = 0
// scala�е�get/set�ֱ����age��age_=
// �������ʽ�����¶����Լ���getter��setter����
   private var privateAge1 = 0
   def age1 = privateAge1
   def age1_=(newValue:Int){if(newValue > privateAge1) privateAge1 = newValue}
// scalaΪÿ���ֶ�����getter��setter����
// ����ֶ���˽�е�,��getter��setter����Ҳ��˽�е�
// ����ֶ���val,��ֻ����getter����
// �������Ҫ�κ�getter��setter,���Խ��ֶ�����Ϊprivate[this]
// 5.4 ����˽���ֶ�
// ��java��ͬ,�������Է��ʸ�������ж����˽���ֶ�.��:this.value = other.value
// scala��������ϸ��������,ͨ��private[this]���η�����(�ö����Ϊ:����˽�е�)
   private[this] var pvale = 0 //�ڸ��෽����, ��������ʵ������.pvale�ǲ������
// scala��������Ȩ�޸���ָ������,private[����]���η����Զ������ָ����ķ������Է��ʸ������ֶ�
// ���������ǵ�ǰ�������,�����ǰ���������ⲿ��,���������,������get/set����,�����ⲿ����ʸ��ֶ�   
   private[Counter] var pcale = 0
// 5.5 Bean����
// javaBeans�淶�涨java��get/set��������ΪgetXxx/setXxx,��scala�Զ����ɵĲ�ͬ
// ����ͨ��Ϊ�ֶα�ע@BeanProperty,��������javabean�淶�ķ������Զ�����
   import scala.beans.BeanProperty
   @BeanProperty var name1:String = _
// ��5.1 ����ֶ����ɵķ���
// scala�ֶ�                                           #���ɵķ���                                                      #��ʱʹ��
// val/var name               #���е�name;name_=(����var)       #ʵ��һ�����Ա��������ʲ��ұ��������ֶ���ʽ���������
// @BeanProperty val/var name #���е�name;getName();name_=(����var);setName(..)(����var)     #��javaBeans������
// private val/var name       #˽�е�name;name_=(����var)       #���ڽ��ֶη��������ڱ���ķ����������javaһ����
// private[this] val/var name #��                                                                      #���ڽ��ֶη���������ͬһ�������ϵ��õķ��������������õ�
// private[����] val/var name  #�����ھ���ʵ��                                             #������Ȩ�޸����ⲿ�࣬���������õ�
// ---------------------------------------------------
// 5.6 ����������
// class��һ���������������ɸ��������� 
// ����������1.����Ϊthis 2.���������������ȵ�����������������������������
   def this(name:String){ //����������
     this() //������������
     this.name1 = name
   }
// 5.7 ��������
// ����û����ʾ������������������һ���޲ε���������
   class Preson(val name:String = "",val age:Int = 0){ //����Ĭ�ϲ���
     println("��������") //����������ִ�����
   }
// 1.���������������ϲ� 2.����������ִ���ඨ���е��������
// ���������Ĳ���������������ʽ
// ������������                                                       #���ɵ��ֶ�/����
// name:String                       #����˽���ֶΣ���û�з���ʹ��name,��û�и��ֶ�
// private val/var name:String       #˽���ֶΣ�˽��getter/setter����
// val/var name:String               #˽���ֶΣ�����getter/setter����
// @BeanProperty val/var name:String #˽���ֶΣ�����scala���javaBeans���getter/setter����
// ---------------------------------------------------
// ���뽫�����������˽�еģ������¶���
   class Preson2 private(val id:Int){}
// 5.8 Ƕ����
// ��scala�У�������ں����ж��庯���������ж�����
   import scala.collection.mutable.ArrayBuffer
   class Network{
     class Member(val name:String) {val contacts = new ArrayBuffer[Member]}
     private val members = new ArrayBuffer[Member]
     def join(name:String) = {
       val m = new Member(name);members+=m;m
     }
   }
   
   val a = new Network
   val b = new Network
// ��java��ͬscala�С�ÿ��ʵ���������Լ���Member�࣬����a.Member��b.Member�ǲ�ͬ��������
   new a.Member("1") //��java��Ҫд��a.new Member()
// �����ϣ����ô�������������¼��ַ�������
// ��Member���Ƶ��𴦣����磺Network�İ���������
// ����ʹ������ͶӰNetwork#Member���京����"�κ�Network��Member"
   class Network1{
     class Member(val name:String){val c = new ArrayBuffer[Network1#Member]}
   }
// ����Ƕ���У�����ʹ���ⲿ��.this�ķ�ʽ�����ⲿ������ã�Ҳ�����������﷨
// ����һ��ָ������õı���
   class Network2(val n:String){ outer =>
     class Member(val name:String) {
       def d = name + outer.n //ʹ��
     }
   }
   
   }
   
  
   