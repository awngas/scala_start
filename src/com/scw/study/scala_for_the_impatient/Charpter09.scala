package com.scw.study.scala_for_the_impatient

object Charpter09 {
  def main(args: Array[String]) = {
    println("�ļ���������ʽ")
  }
// 9.1 ��ȡ��
   import scala.io.Source
   val source1 = Source.fromFile("file.txt","UTF-8")
   val lineIterator = source1.getLines() // ������
   for( l <- lineIterator) println(l) // ��������
   val lines = source1.getLines().toArray // �ŵ�������
   val contents = source1.mkString // �ļ�ȫ�����ݷŵ��ַ�����
// 9.2 ��ȡ�ַ�
   for(c <- source1) {} // �����������ַ�
   val iter = source1.buffered //�鿴ĳ���ַ����ֲ��������������java��PushbackInputStreamReader��
// 9.3 ��ȡ�ʷ���Ԫ������
// ��������ȡ�Կո�ָ��ĸ������ļ�   
   val tokens = source1.mkString.split("\\S+") //���ٻ�ÿո�ָ����ַ���
   val numbers1 = for (w <- tokens ) yield w.toDouble //ת����double����
   val numbers2 = tokens.map(_.toDouble) //ת����double����
   val age = readInt() //�ӿ���̨�ж�ȡһ������
// 9.4 ��URL������Դ��ȡ
// Source.formURL,Source.formString,Source.stdin //��URL,String,��׼�����ȡ
// 9.5 ��ȡ�������ļ�
// Scala��ȡ�������ļ���Ҫ��Java���
   import java.io.{File,FileInputStream}
   val file = new File("a.txt")
   val in = new FileInputStream(file)
   val bytes = new Array[Byte](file.length.toInt)
   in.read(bytes)
   in.close()
// 9.6 д���ı��ļ�
// scalaû�ЃȽ�֧�֣�����java.io.PrintWriter
// 9.7 ����Ŀ¼
// ����1   
   import java.io.File
   def subdirs(dir:File):Iterator[File] ={
     val children = dir.listFiles.filter(_.isDirectory)
     children.toIterator ++ children.toIterator.flatMap(subdirs _)
   }
   val dir = new File("Ŀ¼")
   for(d <- subdirs(dir)){} //���������������������Ŀ¼
// ����2 ʹ��java.nio.file.Files��walkFileTree�����������õ���FileVisitor�ӿ�
// ��scala�У�����ͨ��ϲ���ú���������ָ���������ݣ������ǽӿ�
   import java.nio.file._
   implicit def makeFileVisitor(f:(Path) => Unit) = new SimpleFileVisitor[Path]{
     override def visitFile(p:Path,attrs:attribute.BasicFileAttributes) = {
       f(p)
       FileVisitResult.CONTINUE
     }
   }
   val dir2 = new java.io.File("")
   Files.walkFileTree(dir2.toPath, (f:Path) => println(f)) //��ӡ������Ŀ¼
   // 9.8 ���л�
// ����һ������Ա����л�
   @SerialVersionUID(42L) class Person extends Serializable
// ���л�
   val fred = new Person
   import java.io._
   val out = new ObjectOutputStream(new FileOutputStream("/���л�/t.obj"))
   out.writeObject(fred); out.close()
// �����л�
   val inObj = new ObjectInputStream(new FileInputStream("/���л�/t.obj"))
   val saveFred = inObj.readObject().asInstanceOf[Person]
// Scala�����඼�ǿ����л���
// 9.9 ���̿���
// ͨ��scala.sys.process�����������scala��дshell�ű����ð�����һ�����ַ�����ProcessBuilder�������ʽת��
   import sys.process.{ProcessLogger => _ ,_ }
   "ls -l" ! //ִ������������׼���
   val list = "ls -l" #| "grep sc" ! //����#>��#>>��#<�������ܵ�����
// ������P112���ļ��ض������룬���û�������Process��
// 9.10 ������ʽ
// scala.util.matching.Regex
   val xPattern = """\s+[0-9]+\s+""".r
   xPattern.findAllIn("�����ַ���") //����ƥ�����������findFirtIn���ص�һ��ƥ���ԣ�findPrefixOf����ַ�����ʼ�����ܷ�ƥ��
// 9.11 ������ʽ��
   val numitemPattern = "([0-9]+) ([a-z]+)".r
   val numitemPattern(num,item) = "�����ַ���" //ƥ���飬������������ȡ����ʹ�á�
   for( numitemPattern(num,item) <- numitemPattern.findAllIn("�����ַ���") ) {} //����num��Item
// """ """ ֮��������ַ�����Ҫת��   
}