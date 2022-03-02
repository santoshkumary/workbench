package org.workbench.application
package Reader
import org.workbench.application.workbenchobj
import org.workbench.application.{SessionHandler=>S}

import org.apache.spark.sql.DataFrame

class Readinput {

  val sprk=S.spark

  def src(Env:String): DataFrame = {
   val readdf:DataFrame= Env.toUpperCase() match {
      case "DEV"=> {
        val df=sprk.read.format("jdbc").options(Map("url"->"jdbc:mysql://localhost:3306",
        "user"->"hiveuser"
        ,"password"->"Mymachine"
        ,"dbtable"->"WRK.myTable"
        ,"driver"->"com.mysql.jdbc.Driver")).load()

       df
      }
      case "DEVFILE"=> {
        val df=sprk.read.format("csv").option("inferSchema","true").option("header","true")
          .load("file:///home/hduser/workarea/Empdata.csv")

        df
      }

    }
    readdf


  }

}
