package com.scw.study.scala_for_the_impatient

object Charpter05 {
   def main(args: Array[String]) = {
     println("类")
     
     
   }
}

// 5.1 简单类和无参方法
   class Counter{ //类是公有可见性
     private var value = 0 //字段必须初始化
     def increment() {value += 1} //方法默认是公有的
     def current() = value
// 调用无参方法是,可以写括号也可以不写
// 若定义方法时不带(),则调用函数时强制不带圆括号
     def current1 = value //myCounter.current1
// 5.2 带getter和setter的属性
     
     
   }