package com.bbd.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by bbd on 2016/9/1.
  * 实时计算HDFS上数据
  *
  */
object StreamWCFromHDFS {
  def main(args: Array[String]) {
    if(args.length !=1 ){
      println("Usage: <inputpath> ")
      System.exit(1)
    }

    val conf = new SparkConf()
    conf.setAppName(StreamWCFromHDFS.getClass.getName())
    conf.setMaster("lcoal")

    //实例化一个StreamingContext对象
    val ssc = new StreamingContext(conf,Seconds(2))
    ssc.checkpoint("/tmp/sparksreaming0")

    //定义数据接收器
    val frdd = ssc.textFileStream(args(1))

    val wc = frdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    wc.foreachRDD(rdd => rdd.foreach(println _))
//    wc.print()

    ssc.start()
    ssc.awaitTermination()

  }
}
