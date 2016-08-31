package com.bbd

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by bbd on 2016/8/31.
  */
object SuanziTest {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf
    conf.setAppName(SuanziTest.getClass.getName())
    conf.setMaster("local")

    val sc = new SparkContext(conf)
    val rdd1 = sc.makeRDD(Array("A",1,2))
    println(rdd1.first())




  }
}
