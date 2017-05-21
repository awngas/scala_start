package com.scw.study.scala_for_the_impatient

object Charpter21 {
   def main(args: Array[String]) = {
     println("��21�� ��ʽת��")
     
/**
21
21.1 ��ʽת��
��ν��ʽת������(implicit conversion function)ָ����������implicit�ؼ���
�����Ĵ��е��������ĺ����������ĺ������Զ�Ӧ�ã���ֵ��һ������ת��Ϊ��һ�����͡�
���ӣ�implicit def int2Fraction (n:Int)=Fraction(n,1)
�������ǾͿ��������±��ʽ��ֵ��val rst = 3 * Fraction(4,5)
Լ���׳Ƶĺ�������source2Target
21.3 ������ʽת��
scala�ῼ�����µ���ʽת��������
1.λ��Դ��Ŀ�����͵İ��������е���ʽ������
2.λ�ڵ�ǰ����������Ե�����ʶ��ָ������ʽ������
����int2Fraction���������Էŵ����İ��������У����������ܹ���ʹ����
���ߣ��������Ƿ�����FractionCover�����У����������λ��com.a.b����
�����ʹ�����ת��������Ҫ����FractionCover����
��������import com.a.b.FractionCover._ ����com.a.b.FractionCover.int2Fraction 
��ƾ����������������ǲ��е�import com.a.b.FractionCover
21.4 ��ʽת������
��һ������»ῼ�ǣ�
�����ʽ��������Ԥ�ڵ����Ͳ�ͬʱ
sqrt(Fraction(1,4)) sqrtԤ�ڵ���һ��Double,���Ի�ת��
���������һ�������ڵĳ�Աʱ��
new File("a").read
���������ĳ�����������÷����Ĳ��������봫�������ƥ��ʱ��
3 * Fraction(4,5)
������������������᳢��ʹ����ʽת����
��������ܹ��ڲ�ʹ����ʽת����ǰ����ͨ�����룬�򲻻�ʹ����ʽת����
���������᳢��ִ�ж��ת��������convert1(convert2(a))*b
���ڶ����Ե�ת���������ᱨ������convert1(a)*b��a*convert2(b)�Ϸ�
�����в��� scalac -Xprint:type class.scala ���ῴ����ʽת�����Դ��
21.5 ��ʽ����
�����򷽷����Դ���һ�����Ϊimplicit�Ĳ����б���������£��������������ȱʡֵ��
�ṩ���ú����򷽷���
ʾ����case class Deli(l:String,r:String);def q(what:String)(implicit d:Deli)=d.l + what + d.r;
���������ʡ����ʽ�����б�q("aa")
��������£��������������һ������ΪDeli����ʽֵ���������һ��������Ϊimplicit��ֵ��
���������������������ط���������һ������
�ڵ�ǰ���������п����õ�����ʶ��ָ������������Ҫ���val��def.
����Ҫ����������������͵İ�����������������Ͱ�����Ҫ�����ͱ����Լ��������Ͳ���(�������һ�������������͵Ļ�)��
���磺object FP{implicit val q = Deli("a","b")...}�������ǾͿ�������import FP._��import FP.q ������ʽ���ṩ����q����
21.6 ������ʽ����������ʽת��
���磺def s[T](a:T,b:T)=if(a<b) a else b ���벻ͨ������֪��a��b��û��<������
def s[T](a:T,b:T)(implicit order:T=>Ordered[T])=if (order(a) < b) a else b; ͨ��
����Ordered[T]������һ������T��Ϊ������<��������������ȷ
def s[T](a:T,b:T)(implicit order:T=>Ordered[T])=if (a < b) a else b; ͨ����������order(a)<b�����aû�д�<������
21.7 �����Ľ綨
���Ͳ���������һ����ʽΪT:M�������Ľ綨������M����һ���������͡�
��Ҫ���������д���һ������ΪM[T]����ʽֵ������ʽֵ���Ա����ڸ���ķ������С�
*/
  class Pair[T:Ordering](var first:T,val second:T){
   def smaller(implicit ord:Ordering[T]) = 
     if(ord.compare(first, second)<0) first else second
/**
* �������newһ��Pair(40,2),���������ƶϳ�������Ҫһ��Pair[Int]��
* scala.Predef ��һ������ΪOrdering[Int]����ʽֵ�����Int���������Ľ綨��
* �����Ը�⣬��Ҳ������Predef���implicitly������ȡ��ֵ��
*/
	def smaller2 =
	  if(implicitly[Ordering[T]].compare(first, second)<0) first else second
/**
* implicitly������Predef�ж������£�def implicitly[T](implicit e: T) = e
* ��������Ordered�����ж���Ĵ�Ordering��Ordered����ʽת���������ת�����Ϳ���ʹ�ù�ϵ������
*/
	def smaller3 = {
	    import Ordered._; if(first < second) first else second
	}
/**
* �����������ʱʵ����Pair[T],ֻҪ�����������ΪOrdering[T]����ʽֵ���������ɡ�
* ������˵���������Ҫһ��Pair[Point],�������֯һ����ʽ��Ordering[Point]ֵ:
* implicit object PointOrdering extends Ordering[Point]{
*   def compare(a:Point,b:Point) = ...
* }
*/
  }//Pair[T:Ordering] ���β
/**
* 21.8 ����֤��--����������
* ��17���У����ǿ�����������������Լ����T =:= U;T <:< U;T <%< U�ȡ�
* Ҫʹ������������Լ�����������ṩһ����ʽ������
*/
  def firstLast[A,C](it:C)(implicit ev:C <:< Iterable[A]) = (it.head,it.last)
/**
* <:<���Ǵ�����ʽֵ���࣬������Predef�����У����磺<:<�ӱ����Ͻ����ǣ�
*/
  abstract class <:<[-From,+To] extends Function1[From,To]
  
  object <:< {
    implicit def conforms[A] = new (A <:< A){def apply(x:A) =x}
  }
/**
* �ٶ���������Ҫ����Լ��implicit ev:String <:< AnyRef�������ڰ��������в���
* ����ΪString <:< AnyRef����ʽ����ע��<:<�����From�����ģ��������To��Э��á�
* ������¶���<:<.conforms[String]���Ա�����String <:< AnyRef��ʵ��ʹ�á�
* ���ǰ�ev����"����֤������"--���Ĵ���֤����String��AnyRef�������͡�
* ���������֤�������Ǻ�Ⱥ���(����Զ���ز���ԭֵ�ĺ���)��
* ������ʵ���ϲ���֪��C��һ��Iterable[A]��<:<��һ���಻���������ԡ����it.head��it.last
* �����ĵ��ò��Ϸ�����ev��һ�����е��������ĺ��������Ҳ��һ����C��Iterable[A]����ʽת����
* ����������Ӧ�������ʽת��������ev(it).head��ev(it).last
* 
* 21.9 @implicitNotFoundע��
* ��ע����߱������ڲ��ܹ�������и�ע������͵Ĳ���ʱ����������ʾ����������Ŀ���Ǹ�����Ա������Ĵ�����ʾ��
* ��P333ҳ
* 21.10 CanBuildFrom���
* ����map��������΢��˵��map����һ��Iterable[A,Repr]�ķ���
* def map[B,That](f:(A)=>B)(implicit bf:CanBuildFrom[Repr,B,That]):That={
*   val builder = bf()
*   val iter = iterator()
*   while(iter.hasNext) builder += f(iter.next())
*   builder.result
* }
* Repr����˼�ǡ�չ�����͡����ò��������ǿ���ѡ����ʵĹ�������������������Range��String�����ķǳ��漯�ϡ�
* 
*/


     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
     
     }
}


     
