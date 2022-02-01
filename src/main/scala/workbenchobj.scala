package org.workbench.application

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}
import pipeline.{datefunction=>D}
import org.workbench.application.{SessionHandler=>S}
import org.workbench.application.Reader.Readinput


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
   // Reading source
    val src_Df=new Readinput().src("Dev")


    // Writing to hive
    //val wrMap=Map("Hive"->src_Df)
    src_Df.show()



    }


}
