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
//    val rdd1 = sc.makeRDD(Array("A",1,2))
//    println(rdd1.first())

//    val rdd1 = sc.parallelize(Array(1,2,3,4,5,6,7),2)
//    println(rdd1.partitions.size)

    val data = List(("a",1),("b",1),("c",1),("a",2),("b",2),("c",2))
    val rdd = sc.parallelize(data)
    rdd.reduceByKey( _ + _).foreach(println _)
    rdd.groupByKey().foreach(println _)









  }
}
