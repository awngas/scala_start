package com.scw.study.scala_for_the_impatient

object Charpter14 {
   def main(args: Array[String]) = {
     println("ģʽƥ���������")
   abstract class Item
   case class Article(description:String,price:Double) extends Item
   case class Bundle(description:String,discount:Double,items:Item*) extends Item
   val bb = Bundle("123",20.0,
       Article("456",39.95),
//       Bundle("789",10.0,Article("abc",39.95),Article("def",39.95)),
       Article("456",39.95),
       Article("456",39.95)
//       Bundle( "hig",10.0,Article("abc",39.95) )
       )
   bb match {
      case Bundle(_,_,Article(descr,_),_*) =>println(0)
      case Bundle(_,_,art @ Article(_,_),rest) => println(art + "+++++" + rest)
      case Bundle(_,_,art @ Article(_,_),rest @ _*) => println(1)
     }
     
   def price(it:Item):Double = it match{
     case Article(_,p) => p
     case Bundle(_,disc,its @ _*) => its.map(price _).sum - disc
   }
   val a  = price(bb);
   println(a);
   
   }
// 14.1 ���õ�switch
   val sign = 'c' match {
     case '+' => 1
     case _ => 0  //��defalut��Ч��û�еĻ�����û��ģʽ��ƥ�䣬�׳�MatchErrorһ��
   }
// ģʽƥ�䲻�������һ����֧�����ʽ�п���ʹ���κ����ͣ���������������
// 14.2 ����
// ������ƥ�����������֣�����д�� case _ if Character.isDigit(ch) => ..
// �����������κ�Boolean������ģʽ���Ǵ�������ƥ�䣬��������������ģʽ����ƥ�䣬�򲶻����е�ģʽ(case _)�ᱻ�������Խ���ƥ��
// 14.3 ģʽ�еı���
// case _ �����д�������case�ؼ��ֺ������һ������������ôƥ��ı��ʽ�ᱻ��ֵ���Ǹ�����
// �������������ʹ�ñ���������������Сд��ĸ��ͷ��ĳ��ᱻ��Ϊ������Сд��ĸ��ͷ�ĳ���Ҫ���ڷ�������
   var digit = 0; 'a' match{ case ch => digit=Character.digit(ch,10) }
// 14.4 ����ģʽ
// ����ԶԱ��ʽ�����ͽ���ƥ��,���Ǹ�������ʹ��ģʽƥ�䣬������isInstanceOf������
// obj match{
//   case x : Int => x //���õ���ֵ������Int��x
//   case s : String => Integer.parseInt(s)
//   case BigInt => -1 //ƥ������ʱ���������һ����������������ö��������ƥ�䡣���ƥ������ΪClass��BigInt����
// }
// ƥ�䷢���������ڣ�java������з��͵�������Ϣ�Ǳ������ģ����Բ���ƥ���ض���Map����
// case m:Map[String,Int] => //���������������ǿ���ƥ��ͨ�õ�ӳ��case n:Map[_,_] => �����������Ԫ�ص�������Ϣ����õģ������ƥ�䵽ArrayList[Int]
// 14.5 ƥ�����顢�б��Ԫ��
// ������������ݣ���Array���ʽ
// case Array(0) //ƥ�����0������
// case Array(x,y) //ƥ���κδ�������Ԫ�ص����飬��������Ԫ�ذ󶨵�x��y
// case Array(0,_*) //ƥ���κ���0��ʼ������
// Ҳ����ʹ��List���ʽƥ��List.��Ҳ����ʹ��::������
// case 0::Nil;case x::y;case 0::tail
// ����Ԫ�飬������ģʽ��ʹ��Ԫ���ʾ��
// case (0,_); case (y,0);
// 14.6 ��ȡ��
// ģʽ��ƥ�����顢�б��Ԫ�飬����Ϊ��ȡ��(extractor)����--���дӶ�����ȡֵ��unapply��unapplySqe�����Ķ���
// 11���Ѿ����ܹ���unapply����������ȡ�̶������Ķ��󣬶�unapplySeq��ȡ����һ�����У��ɳ��ɶ̡�
// ���磺arr match{case Array(0,x)=>...} //������Array.unapplySeq(arr)����ֵ.����һ��ֵ��0���бȽϣ��ڶ���ֵ����ֵ��x
// 14.7 ���������е�ģʽ
// ģʽ���Դ��������ڱ���������ʹ��������ģʽ
   val (x,y) = (1,2) //ͬʱ��x����Ϊ1����y����Ϊ2�����ڷ��ض�ż�ĺ���������
   val (q,r) = BigInt(10) /% 3 //���ذ����̺������Ķ�ż
// ͬ�����﷨Ҳ���������κδ��б�����ģʽ
// val Array(first,second,_*) = arr
// 14.8 for���ʽ�е�ģʽ
// �������for�Ƶ�ʽ��ʹ�ô�������ģʽ��
   import scala.collection.JavaConversions.propertiesAsScalaMap
   for((k,v) <- System.getProperties()) println(k+"->"+v)
   for((k,"") <- System.getProperties()) println(k) //��ӡ����ֵΪ�հ׵ļ�����������
   for((k,v) <- System.getProperties() if v == "") println(k+"->"+v) //Ҳ����ʹ���ػ�
// 14.9 ������
// ��һ��������࣬���Ǿ����Ż��Ա�����ģʽƥ�䡣
// ��������case�ؼ������Σ��磺case class Nothing1 extends Amount
// ��������������ʱ��
// �������е�ÿһ����������Ϊval-����������ʽ������Ϊvar����������������
// �ڰ����������ṩapply����
// �ṩunapply������ģʽƥ����Թ���--��11��
// ����toString��equals��hashCode��copy������������ʽ�ĸ�����Щ�����Ķ���
// 14.10 copy�����ʹ�������
// �������copy���Ŵ���һ�������ж���ֵ��ͬ���¶���
// �����ʹ�ô����������޸�ĳЩ����
// val price = amt.copy(unit ="kk")
// 14.11 case����е����ñ�ʾ��
// ���unapply��������һ����ż���������case�����ʹ�����ñ�ʾ���������Ƕ��������������������࣬�����ʹ�����ñ�ʾ����ʾ��
// amt match {case a Currency u => ...} //��ͬ�� case Currency(a,u)
// ������Ա����Ҫƥ�����У�������˵ÿ��List����Ҫô��Nil,Ҫô��������::,��������
// case class ::[E](head:E,tail:List[E]) extends List[E] //��仰�����ܱ���ɹ�������ArrayBuffer�ɹ��ˣ�
// ��ˡ����������д lst match{ case h :: t=> ...} //��ͬ��case ::(h,t),������::.unapply(lst)   
// ��19���У��ῴ����������������һ���~�����ࡣ
// result match{case p -q -r =>..} ����д������~(~(p,q),r)
// ���ñ�ʾ���������κη��ض�ż��unapply����
// �����������ð�Ž�β�������Ǵ��������ϵ�
// 14.12 ƥ��Ƕ�׽ṹ
// �����ྭ��������Ƕ�׽ṹ�����磬ĳ���̵�������Ʒ����ʱ���ǻὫ��Ʒ������һ����۳���
   abstract class Item
   case class Article(description:String,price:Double) extends Item
   case class Bundle(description:String,discount:Double,items:Item*) extends Item
   val que = Bundle("123",20.0,Article("456",39.95),Bundle("789",10.0,Article("abc",39.95),Article("def",39.95)))
   que match {
     case Bundle(_,_,art @ Article(_,_),rest) => println(art + "+++++" + rest)
     case Bundle(_,_,art @ Article(_,_),rest @ _*) => println(1)
   }
// ģʽ����ƥ�䵽�ض���Ƕ�ף����磺
// case Bundle(_,_,Article(descr,_),_*) => .. //��descr�󶨵�Bundle�ĵ�һ��Article������
// Ҳ������@��ʾ����Ƕ�׵�ֵ�󶨵�����
// case Bundle(_,_,art @ Article(_,_),rest @ _*) //art����Bundle�еĵ�һ��Article,��rest����ʣ��Item������
// case Bundle(_,_,art @ Article(_,_),rest) //art��Bundle�еĵ�һ��Article,��rest����һ��Item�����࣬����ƥ��ֻ��ƥ�䣬������������Article���ͣ����ĸ�����ֻ����һ����������
// ʵ��Ӧ��,����ĳ��Item�۸�ĺ���
   def price(it:Item):Double = it match{
     case Article(_,p) => p
     case Bundle(_,disc,its @ _*) => its.map(price _).sum - disc
   }
// 14.13 ��������а�����
// �������ʺ��������ֱ�ǲ���ı�Ľṹ��scala��list������������ʵ�ֵ�
// �����ں��ʵĵط�������������װ��������������Ĵ��룬ԭ��:P204
// 14.14 �ܷ���
// ����������ģʽƥ��ʱ���������ñ���������ȷ�����Ѿ��г������п��ܵ�ѡ��
// ����Ҫ���������ͨ�ó�������Ϊsealed
   sealed abstract class Amount2
// �ܷ���������������������ܷ�����ͬ���ļ��ж���
// 14.15 ģ��ö�� ʹ��sealed + �� +ģʽƥ��  ģ��ö������
// 14.16 Option����
// ��׼����е�Option����������������ʾ���ֿ��ܴ��ڡ�Ҳ���ܲ����ڵ�ֵ��
// ���ʹ�ÿ��ַ�������ͼ������������ʹ��null����ʾȱ��ĳֵ���������Ӱ�ȫ
// ��������Some��װ��ĳ��ֵ������������Node��ʾû��ֵ��Option֧�ַ���
// Map���get��������һ��Option,�����Ӧ��û��ֵ��get����None,����Ϊ����ֵ��Some.
// ����Խ�Option����һ��ҪôΪ�գ�Ҫô���е���Ԫ�صļ���
// ����scs.get("a").foreach(println _) //���get����None�Ļ���ʲôҲ����
// 14.17 ƫ����
// �����ڻ������ڵ�һ��case�����һ��ƫ����---һ�����Ƕ���������ֵ���ж���ĺ���
// ����PartialFunction[A,B]���һ��ʵ����A��������B�������͡�
// ����������������apply��ƥ�䵽��ģʽ���㺯��ֵ��isDefinedAt����������ƥ������һ��ģʽʱ����true
   var f:PartialFunction[Char,Int] = {case '+' =>1;case '-' => -1} // f('-') ����f.apply����-1 f('0') //�׳�MatchError
// ��Щ��������PartialFunction��Ϊ������������˵��GenTraversable���ʵ�collect������һ��ƫ����Ӧ�õ������ڸ�ƫ�����ж����Ԫ�أ������ذ�����Щ���������
   "-3+4".collect{ case '+' => 1;case '-' => -1}
}