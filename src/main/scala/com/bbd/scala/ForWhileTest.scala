package com.bbd.scala

/**
  * Created by bbd on 2016/9/3.
  */
object ForWhileTest {
  def juege(a:Int):Int = {
    if (a>0){
      1
    }else if(a < 0){
      -1
    }else{
      0
    }
  }

  //for 循环
  def forloop(): Unit ={
    val arr = Array(1,2,3,4,5,6)
    println(1.to(10))     //Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(1 until 10)   //Range(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(1 to 10 reverse)  //Range(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
//    for(a <- 1 to 10){
//      println(a)
//    }

//    for(s <- "zhangsan"){
//      println(s)
//    }

    //高级for
//    for(i <- 1 to 10 if i%2==0){  //守卫
//      println(i)
//    }

//    for(i <- 1 to (10,2)){//修改步长
//      println(i)
//    }

//    for(i <- 1 to 10 ;j <- 1 to 5){ //双重循环
//      println(i+"-"+j)
//    }

    var l = for(i <- 1 to 10) yield {  // for的推倒式
      i*2
    }



  }


  def main(args: Array[String]) {
    forloop()

  }
}
