package org.workbench.application

import org.apache.spark.sql.SparkSession
import org.apache.hadoop.fs._
import org.apache.spark.sql.functions.{col, udf}
import pipeline.{datefunction=>D}
import org.workbench.application.{SessionHandler=>S}
import org.workbench.application.Reader.Readinput
import org.workbench.application.Configuration



object workbenchobj  {

  def main(args: Array[String]): Unit = {

     val sprk=S.spark

    val df=sprk.read.format("csv").option("header","true")
      .load("hdfs://127.0.0.1:9000//user/prod.csv")


df.show()


   val df_t= df.select(col("Product"),D.dcnv(col("date")).alias("dateconversion"))
   df_t.show()
   df_t.printSchema()

    //df.select(col("Product"), dccnv(col("date"))).show()


    //configfile read
     //val prop=ConfigFactory.load()
    // val env=prop.getConfig(args(0))

    //val conf = com.typesafe.config.ConfigFactory.load("application.properties")

   // println("reading properties" + env.getString("dev.db"))




   // Reading source
    val src_Df=new Readinput().src("Dev")
    val file_Df=new Readinput().src("DEVFILE")


    // Writing to hive
    //val wrMap=Map("Hive"->src_Df)
    src_Df.show()
    file_Df.show()


    val fd=file_Df.select(col("Emp ID").alias("EmpID"))
    fd.write.mode("overwrite").option("path","hdfs://127.0.0.1:9000/user/hive/warehouse/Empdata")
      .saveAsTable("file_Df")


    }



}
