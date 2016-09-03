package com.bbd.sparksql

import java.sql.DriverManager

/**
  * Created by bbd on 2016/9/3.
  */
object SparkJDBC {
  def main(args: Array[String]) {

    //加载驱动
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    //获取链接
    val conn = DriverManager.getConnection("jdbc.hive2://ip:10000/dbname")
    //获取Statement
    val stmt = conn.prepareStatement("select count(*) from test")
    val result = stmt.executeQuery()
    while(result.next()){
      println(result.getString(1))
    }

    //关闭链接
    result.close()
    stmt.close()
    conn.close()

  }
}
