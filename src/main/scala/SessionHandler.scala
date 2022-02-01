package org.workbench.application

import org.apache.spark.sql.SparkSession

case object SessionHandler
{

  val spark =  SparkSession.builder
    .config("spark.master", "local")
    .appName("Wrkbench")
    .config("spark.sql.warehouse.dir", "hdfs://127.0.0.1:9000/user/hive/warehouse")
    .enableHiveSupport()
    .getOrCreate()

}