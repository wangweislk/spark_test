package com.bbd.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by bbd on 2016/9/3.
  *
  * 通过spark sql 读取json文件数据进行查询
  */
object SparkSqlReadJsonFile {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName(SparkSqlReadJsonFile.getClass.getName).setMaster("local")
    val sc:SparkContext = new SparkContext(conf)

    //构建spark的上下文对象
    val sqlContext  = new SQLContext(sc)
//    val hiveContext = new HiveContext(sc)

    //读取数据源
//    val input = hiveContext.jsonFile("")
    //通过hiveContext对象进行加载，生成
    val input = sqlContext.read.json("D:\\work\\code\\spark_test\\src\\main\\scala\\com\\bbd\\sparksql\\person.json")
    input.printSchema()
    /*
      root
         |-- age: long (nullable = true)
         |-- name: string (nullable = true)
         |-- salary: long (nullable = true)
     */
    //要注册为一个SparkSQL表的前提必须为SchemaRDD(新版本叫DataFrame)
    input.registerTempTable("person")

    val result = sqlContext.sql("select * from person")
    result.foreach(println _)
    val datas = result.map(row => ("name="+row(1),"age="+row(0),"salary="+row(2)))
    datas.foreach(println _)







  }
}
