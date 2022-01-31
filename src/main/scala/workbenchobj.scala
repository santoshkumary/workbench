package org.workbench.application

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}
import pipeline.{datefunction=>D}


object workbenchobj  {

  def main(args: Array[String]): Unit = {

    val spark =  SparkSession.builder
      .config("spark.master", "local")
      .appName("Wrkbench")
      .config("spark.sql.warehouse.dir", "hdfs://127.0.0.1:9000/user/hive/warehouse")
      .enableHiveSupport()
      .getOrCreate()

    val df=spark.read.format("csv").option("header","true")
      .load("hdfs://127.0.0.1:9000//user/prod.csv")


df.show()


    df.select(col("Product"),D.dcnv(col("date"))).show()


    //df.select(col("Product"), dccnv(col("date"))).show()



    }


}
