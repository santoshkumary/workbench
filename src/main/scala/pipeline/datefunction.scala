package org.workbench.application
package pipeline

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf
import scala.util.matching.Regex
import java.text.SimpleDateFormat

object datefunction extends idate {

  def dtcnv(dt_String:String): java.sql.Date={
    val datetimePattern: Regex = "^(\\d{4})-(\\d{2})-(\\d{2})[ T]((\\d{2}):(\\d{2}):(\\d{2}))$".r
    val datePatternYYYYMMDD: Regex = "^\\d{4}-\\d{2}-\\d{2}$".r
    val datePatternDDMMYYYY: Regex = "^\\d{2}-\\d{2}-\\d{4}$".r
    //val dt = new SimpleDateFormat("dd-mm-yyyy")
    //new java.sql.Date(dt.parse(dt_String).getTime)


    val dt_c: java.sql.Date = dt_String.trim match {
      case datePatternYYYYMMDD() => {
        val dt = new SimpleDateFormat("yyyy-MM-dd")
        new java.sql.Date(dt.parse(dt_String).getTime)
      }
      case datePatternDDMMYYYY() =>{
        val dt = new SimpleDateFormat("dd-MM-yyyy")
        new java.sql.Date(dt.parse(dt_String).getTime)
      }
      case datetimePattern() => {
        val dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        new java.sql.Date(dt.parse(dt_String).getTime)
      }
      case _ => throw new IllegalArgumentException("Wrong date/datetime format")
    }
    dt_c

  }
  val dcnv: UserDefinedFunction = {
    udf(dtcnv(_))
  }

}
