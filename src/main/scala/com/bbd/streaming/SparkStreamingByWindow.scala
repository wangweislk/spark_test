package com.bbd.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by bbd on 2016/9/2.
  */
object SparkStreamingByWindow {
  def main(args: Array[String]) {
    val ssc = new StreamingContext(new SparkConf(),Seconds(5))

    val lines = ssc.socketTextStream("localhost",9999,StorageLevel.MEMORY_ONLY)

    val word = lines.map((_,1))
    //窗口函数
    //第一个参数：合并函数
    //第二个参数：窗口时间间隔大小，必须是Streaming时间间隔的倍数
    //第三个参数：滑动时间间隔，即计算的时间
    //意思为：每隔15秒计算前一个30秒的数据
    val windowStreaming = word.reduceByKeyAndWindow((x:Int,y:Int) => (x+y),Seconds(30),Seconds(15))

    windowStreaming.print()

    ssc.start()
    ssc.awaitTermination()

  }
}
