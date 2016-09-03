package com.bbd.scala

/**
  * Created by bbd on 2016/9/3.
  */
object FuncTest {

  //有返回值函数
  def add(x:Int,y:Int): Int = {
    x+y
  }

  //无返回值函数，Unit类型用()表示，类似于Java中的void
  def add2(x:Int,y:Int):Unit = {
    println(x+y)
  }

  def add3(x:Int,y:Int) = x+y



  def main(args: Array[String]):Unit = {
    println(add3(1,2))

  }
}
