package com.scw.study.scala_for_the_impatient

object Charpter02 {
   def main(args: Array[String]) = {
     println("���ƽṹ�ͺ���")
   } 
   val x = 3;val y=4
   
// 2.1 �������ʽ
   val s = if(x > 0) 1 else -1
// ÿ�����ʽ�������ͣ�  ������˵
   if(x>0) 1 else -1
// ������֧�����Ͷ���Int
   if(x>0) "positive" else -1
// ���������ǹ��������ͣ�һ����֧��java.lang.String,һ����Int,������������Any
// ȱʧelse��Ҳ����ֵ�ã�scala����һ��Unit�࣬д��()����ʾ����ֵ����ռλ��������java��void
   if(x>0) 1 
   if(x>0) 1 else () //������ȼ�
// 2.3 ����ʽ�͸�ֵ
// ��scala�У�{}�����һϵ�б��ʽ������Ҳ��һ�����ʽ���������һ�����ʽ��ֵ���ǿ��ֵ
// ������Զ��ڶ�ĳ��val�ĳ�ʼ����Ҫ�ֶಽ��ɵ���������á�
   val dis = {val dx = x -2; val dy = 10 -x;dx + dy}
// ��ֵ����ֵ��Unit���͵ģ����Բ�Ҫ����һ��
// x=y=1 //������ô����y=1��ֵ��()
// 2.5 ѭ��
// scala��while��doѭ����java��ͬ
// scala��forѭ��Ϊfor(���� <- ���ʽ) 
   var isum=0
   for(i <- 0 to 3) isum +=i //0 to 3 ����һ��Range(����)���������ޣ�ֵΪ6
   for(i <- 0 until 3) isum +=i //���������ޣ�ֵΪ3
   for(ch <- "hello") isum +=ch //��ֱ�ӱ����ַ���
// scala��û��break��continue�˳�ѭ�����������1��ʹ��Boolean�͵Ŀ��Ʊ�����
// 2��ʹ��Ƕ�׺������ں�����return 3��ʹ��Breaks�����е�break���� ===>P19ҳ
// 2.6 �߼�forѭ����for�Ƶ�ʽ
// for��䣬����ԼӶ������<-���ʽ��
   for(i<-1 to 3;j<-1 to 3) print((10*i+j)+" ") //11 12 13 21 22 23 31 32 33
// Ҳ���Դ�һ������(��if��ͷ��Boolean���ʽ)��
   for(i<-1 to 3;j<-1 to 3 if i != j) print((10*i+j)+" ") //12 13 21 23 31 32
// Ҳ����ʹ�������Ķ��壬���������ѭ����ʹ�õı�����  
   for(i<-1 to 3;from=4-i;j<-from to 3) print((10*i+j)+" ")  //13 22 23 31 32 33
// for�Ƶ�ʽ����forѭ����ѭ������yield��ʼ�����ѭ���ṹ���һ�����ϣ�ÿ�ε������ɼ����е�һ��ֵ��
// for�Ƶ�ʽ���ɵļ��������ĵ�һ���������������Ǽ��ݵ�
   for(c<-"Hello";i<-0 to 1) yield (c+i).toChar //������"HIeflmlmop"
   for(i<-0 to 1;c<-"Hello") yield (c+i).toChar //Vector('H','e','l','l','o','I','f','m','m','p')
// 2.8 Ĭ�ϲ����ʹ�������
   def decorate(str:String,left:String="[",right:String="]")=left+str+right //��Ĭ�ϲ���
// �ɴ��벿�ֲ���
   decorate("str",right="right")
// 2.9 �䳤����
   def sum(args:Int*)={var result=0;for(arg<-args)result+=arg;result}
   val s2 = sum(1,4,9) //�������һ��Seq���͵Ĳ���
   sum(1 to 5:_*) //��1to5�����������д���sum(1 to 5)�Ǵ����
// 2.10 ����
// �������������ڻ����ŵ��е�û��ǰ���=�ţ���ô�������;���Unit������������������(procedure)
// 2.11 ��ֵ
// ��val������Ϊlazyʱ�����ĳ�ʼ�������Ƴ٣�ֱ���״ζ���ȡֵ������
   lazy val words = scala.io.Source.fromFile("�ļ�·��").mkString
   //lazy,val,def������val��words������ʱ����ȡֵ��lazy��words�״�ʹ��ʱȡֵ��def��ÿһ��words��ʹ��ʱȡֵ
// 2.12 �쳣
// �쳣������java.lang.Throwable���࣬û�С��ܼ족�쳣   
// throw���ʽ��������Nothing
   if(x>=0){x}else{throw new Exception("xx")}
// �쳣����ʹ��ģʽƥ���﷨,��ͨ�õ��쳣Ӧ�÷���Խ���棬����Ҫʹ�ò�����쳣�������_���������
// try{ xxxx }catch{
//   case _:MalformedURLException => xxx
//   case ex:IOException => ex.printStackTrace()
// }
// try/finally�����javaʹ����ͬ��Ҳ����finally���׳��쳣
   
   
}