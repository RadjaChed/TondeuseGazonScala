// on a créé un package "tondeuseGazon" contenant
// les objets et les classes nécéssaires à l'éxécution du programme


import tondeuseGazon.{Execution, Pelouse}

import java.io.{FileNotFoundException, IOException}
import scala.io.Source

object Main{
  def main(args:Array[String]):Unit={
    // l'emplacement du fichier d'instruction
    val filename = "src/main/Resources/instructions.txt"

    try {
      val lines = Source.fromFile(filename).getLines.toList
      // récupérer la première ligne du fichier
      // contient les dimensions de la pelouse
      val dimensionPelouse = lines.head.split("\\s+")
      // définition de l'objet pelouse
      val pelouse = Pelouse(dimensionPelouse(0).toInt, dimensionPelouse(1).toInt)
      // état initial des tondeuses
      val tondeuses = Execution.tondeuseInit(lines.tail, pelouse)
      // calcule des nouvelles positions de chaque tondeuse
      tondeuses.foreach(t => Execution.execution(t.instructions, t.position, t.pelouse))
    } catch {
       // gestion des erreurs
      case e: FileNotFoundException => println(s"Couldn't find file: $filename")
      case e: IOException => println(s"Got an IOException!$e")
      case e: Throwable => println(s"Got exception: $e")
    }


  }
}