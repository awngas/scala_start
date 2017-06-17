package com.scw.study.scala_for_the_impatient

import scala.beans.BeanProperty
import scala.annotation.meta.beanGetter
import java.io.IOException
import scala.annotation.unchecked.uncheckedVariance


object Charpter15 {
   def main(args: Array[String]) = {
     println("11")
   }
// ע��
// 15.1 ʲô��ע��
// ע����﷨��javaһ�������Զ�scala��ʹ��javaע�⡣
// scala��һЩ����ע�⡣javaע�Ⲣ��Ӱ���������ν�Դ�뷭����ֽ��룬
// ���ǽ������ֽ���������ݣ��Ա��ⲿ���߿�����������
// scala��ע�����Ӱ�������̣������5��@BeanProperty
// 15.2 ʲô���Ա�ע��
// ����Ϊ�ࡢ�������ֶΡ��ֲ������Ͳ������ע��
// ����ͬʱ��Ӷ��ע�⡣�Ⱥ����û��Ӱ��
// @BeanProperty @Id var username = _
// �������������ע�⣬��Ҫ�����ڹ�����֮ǰ��������һ��Բ����(ע�ⲻ������)
// class Credentials @Inject() (var u:String)
// (myMap.get(key):@unchecked ) match {..} //Ϊ���ʽ���ע��
// class Mycls[@specialized T]  //Ϊ���Ͳ������ע��
// String @cps[Unit] //���ʵ�����͵�ע��Ӧ��������������֮��@cps��һ�����Ͳ���
// 15.3 ע�����
// ע������д������������������ֻ��value�����ƿ�����ȥ�����ע�ⲻ��������Բ���ſ�����ȥ
// @Test(timeout = 100,expected = classOf[IOException])
// ��ͬ��java��scalaע��Ĳ����������κ�����
// 15.4 ע��ʵ��
// ע�������չAnnotation
// ע�������ѡ����չStaticAnnotation��ClassfileAnnotation
// Ĭ�������ע�ⶼ��Ӧ�÷�Χ��
// Ԫע��@param @field @getter @setter @beanGetter @beanSetter��ʹ��ע�ⱻ���ڱ�
// ����@Idע�⽫��Ӧ�õ�java��getId������
// @Entity class Credentials{
//   @(Id @beanGetter) @BeanProperty var id =0
// }
// 15.5 ���java���Ե�ע��
// 15.5.1 java���η�
   @volatile var d = false //�滻java volatile
   @transient var s = false; //�滻transient��˲̬�ֶβ��ᱻ���л�
// @strictfp def ca(x:Double) = .. //��Ӧstrictfp���η�
// @native �滻java native
// 15.5.2 ��ǽӿ�
// @cloneable �滻java Cloncable �ɱ���¡
// @remote �滻java.rmi.Remote Զ�̶���
// @SerialVersionUID ָ�����л��汾
// 15.5.3 �ܼ��쳣
// java������������ܼ��쳣��������java�����е���scala��������ǩ��Ӧ������Щ���ܱ��׳����ܼ��쳣
// ��@throwsע����������ȷ��ǩ��
   class Book{
     @throws(classOf[IOException]) def read(filename:String){}
   }
// java��ǩ��Ϊ��void read(String filename) throws IOException
// û��@throwsע�⣬java���뽫���ܲ�����쳣
// 15.5.4 �䳤����
// @varargsע���java����scala�Ĵ��б䳤�����ķ���
// 15.5.5 JavaBeans
// @scala.reflect.BeanProperty ע������javabeans����getter��setter����
// 15.6 �����Ż���ע��
// 15.6.1 β�ݹ�
// �ݹ������ʱ�ܱ�ת����ѭ������Լջ�ռ䡣
   def sum(xs:Seq[Int]):BigInt = if(xs.isEmpty) 0 else xs.head + sum(xs.tail)
// �������޷����Ż������һ���Ǽӷ������ǵݹ����
   def sum2(xs:Seq[Int],partial:BigInt):BigInt =
     if(xs.isEmpty) partial else sum2(xs.tail,xs.head+partial)
// �����ǿ��Ա��Ż��ġ����һ�����÷������任�ɵ��ط���������ѭ��������ջ���
// ������ʽ��ȷ��������ʹ��β�ݹ��Ż���@tailrecע�⣬����������ܱ��Ż��ᱨ�����磺sum2λ��ĳ���������ĳ�������У�������������Խ�����Ų�������У����߽�������Ϊprivate��final
// �����ݹ����ͨ�õĻ����ǡ��Ĵ���������ִ��һ��ѭ������ͣ�ص��ú�����ÿ������������һ���������õĺ�����β�ݹ���һ��������ÿ���������������Լ�
// scala��һ����ΪTailCalls�Ĺ��߶��󣬿�������ʵ�ֱĴ���
// �໥�ݹ�ĺ�����������ΪTailRec[A],Ҫô����done(result),Ҫô����tailcall(fun)��fun����һ�������ú������������һ���������������ͬ������TailRec[A]�ĺ���
// ���ӣ�
   import scala.util.control.TailCalls._
   def evenLength(xs:Seq[Int]):TailRec[Boolean] =
     if(xs.isEmpty) done(true) else tailcall(oddLength(xs.tail))
   def oddLength(xs:Seq[Int]):TailRec[Boolean] =
     if(xs.isEmpty) done(false) else tailcall(evenLength(xs.tail))
   evenLength(1 to 100000).result //��ȡ���ս��
// 15.6.2 ��ת������������
// ��c++��java�У�switch���ͨ�����Ա��������ת����һϵ��if/else����Ц��scalaҲ�д˹���
// @switchע����Լ��scala��match����ǲ�����ı����������ת��
// (str: @switch) match{....}
// ����
// @inline���������������
// @noinline���߱�������Ҫ����
// 15.6.3 ��ʡ�Է���
// @elidableע�����Щ�����������������Ƴ��ķ������ϱ��
// @elidable(500) ��һ������������ָ�������ļ���������һЩ������ ������ö���:scala -Xelid-below 2001 x.scala ������Ĭ������
// 15.6.4 �������͵����⻯
// ����ͽ���������͵�ֵ�ǲ���Ч��-���ڷ��ʹ����г���
// def allDifferent[T](x:T,y:T)=x!=y
// ����allDifferent(3,4),����������ǰ��ÿ������ֵ���ᱻ��װ��java.lang.Integer.
// ��Ȼ���ǿ��Ը���˸���������д���صİ汾��def allDifferent[T](x:Int,y:Int)=x!=y
// ʹ��@specialized�����Զ�������Щ����
   def allDifferent[@specialized(Long,Double) T](x:T,y:T) = x!=y
// ���ſ�ѡ������ָ����Unit,Boolean,Byte,Short,Char,Int,Long,Float,Double����
// 15.7 ���ڴ���;����ע��
// �ѹ�ʱ����
// @deprecatedע�⣬��ע��������ѡ�����message��since
// @deprecatedName���Ա�Ӧ�õ������ϣ�������һ���ò���֮ǰʹ�ù�������
   def draw(@deprecatedName('sz) size:Int,style:Int=0) {}
// �����'sz��һ�� ���� �Ե����ſ�ͷ�����ƣ�������ͬ�ķ���һ����Ψһ�ģ�����==�����������ж�����ԣ��ַ������Ǳȶ����ݣ����ű�ʾ���ǳ�����ĳ����Ŀ������
// @implicitNotFoundע��������ĳ����ʽ����������ʱ����������Ĵ�����ʾ���μ�21��
// @uncheckedע��������ƥ�䲻����ʱȡ��������Ϣ�����磺֪��ĳ���б������ǿգ����������ᱨ��˵��û��Nilѡ��
// (lst: @unchecked) match { case head :: tail => ...}
// @uncheckedVarianceע���ȡ�����ͱ���صĴ�����ʾ��������java.until.Comparator����Ӧ�������ġ����Stduends��Person����
// ��ôComparator[Student]ʱ������Ҳ������Comparator[Person]����java���Ͳ�֧���ͱ䣬ͨ����ע��������
   trait Comparator[-T] extends java.lang.Comparable[T @uncheckedVariance]
}