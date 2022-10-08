import tondeuseGazon.{Execution, Pelouse}

import java.io.{FileNotFoundException, IOException}
import scala.io.Source

object Main{
  def main(args:Array[String]):Unit={

    val filename = "src/main/Resources/instructions.txt"

    try {
      val lines = Source.fromFile(filename).getLines.toList
      val Listpelouse = lines.head.split("\\s+")
      val pelouse = Pelouse(Listpelouse(0).toInt, Listpelouse(1).toInt)
      val tondeuses = Execution.tondeuseInit(lines.tail, pelouse)
      tondeuses.foreach(t => Execution.execution(t.instructions, t.position, t.pelouse))
    } catch {
      case e: FileNotFoundException => println(s"Couldn't find file: $filename")
      case e: IOException => println(s"Got an IOException!$e")
      case e: Throwable => println(s"Got exception: $e")
    }


  }
}