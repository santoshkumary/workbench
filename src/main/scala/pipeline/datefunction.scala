package org.workbench.application
package pipeline

import org.apache.spark.sql.functions.udf
import scala.util.matching.Regex
import java.text.SimpleDateFormat

object datefunction extends idate {

  def dtcnv(dt_String:String): java.sql.Date={


    val dt = new SimpleDateFormat("dd-mm-yyyy")
    return new java.sql.Date(dt.parse(dt_String).getTime())

  }
  val dcnv=udf(dtcnv(_))

}
