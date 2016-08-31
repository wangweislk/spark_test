package main.scala.com.bbd

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
  * Created by bbd on 2016/8/30.
  * Author: wangwei
  */
object WordCount {
  def main(args: Array[String]) {
    val conf = new SparkConf
    conf.setAppName(WordCount.getClass().getName)
    conf.setMaster("local[4]")
    val sc = new SparkContext(conf)
//    sc.textFile(args(0)).flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _).saveAsTextFile(args(1))
    val tf = sc.textFile("D:\\BugReport.txt").flatMap(_.split(" ")).map(s => (s,1)).reduceByKey((a,b) => a+b).collect()
    tf.foreach(println _)

    sc.stop()
  }
}
