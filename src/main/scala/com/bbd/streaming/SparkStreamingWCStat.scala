package com.bbd.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by bbd on 2016/9/1.
  * 带状态的更新
  */
object SparkStreamingWCStat {

  //Seq[Int] : 适合存储有序但重复的数据
  //Option[Int] : 可选值的容器，如果值存在返回Some[Int]，如果不存在值返回None
  def updateFunction(words: Seq[Int], runningCount: Option[Int]): Option[Int] = {
    //先累加单词数量
    val currentCount = words.foldLeft(0)(_+_)  //在第一个参数的初始值基础上，累加序列
    //获取当前累加的状态，如果是第一次没有累加
    val lastCount = runningCount.getOrElse(0)
    //返回
    Some(currentCount + lastCount)
  }

  def main(args: Array[String]) {
    val ssc = new StreamingContext(new SparkConf(),Seconds(2))
    ssc.checkpoint("/tmp/spark111")
    val lines = ssc.socketTextStream("localhost",9999,StorageLevel.MEMORY_ONLY)

    //业务逻辑处理
    val word = lines.map((_,1)).reduceByKey(_+_)
    val stateStreaming = word.updateStateByKey(updateFunction)
    stateStreaming.print()

    ssc.start()
    ssc.awaitTermination()




  }
}
