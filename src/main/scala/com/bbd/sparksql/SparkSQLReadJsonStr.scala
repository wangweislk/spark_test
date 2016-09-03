package com.bbd.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by bbd on 2016/9/3.
  */
object SparkSQLReadJsonStr {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName(SparkSQLReadJsonStr.getClass.getName).setMaster("local")
    val sc:SparkContext = new SparkContext(conf)

    //构建spark的上下文对象
    val sqlContext  = new SQLContext(sc)
//    val hiveContext = new HiveContext(sc)

    val s = "{\"name\":\"wangwu\",\"age\":25,\"salary\":10000}"

    val rdd = sc.parallelize(List(s))
    val dfRdd = sqlContext.jsonRDD(rdd)

    dfRdd.registerTempTable("person")
    println(dfRdd)



  }
}
