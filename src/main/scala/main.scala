package TondeuseGazon

import TondeuseGazon.Commandes.toCommande
import TondeuseGazon.PositionCardinale.toPC

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main{
  def main(args:Array[String]):Unit={

    val filename = "src/main/Resources/instructions.txt"
    val lines = Source.fromFile(filename).getLines.toList

    println(lines)
    println(lines.head.split(" ").map(_.trim).toList)

    def tondeuseInit(lines:List[String], listTondeuse:List[TondeuseInit],pelouse: Pelouse):List[TondeuseInit] =
      lines match{
        case Nil => listTondeuse
        case pos :: inst :: rest =>
          val position= Position(pos(0),pos(1),toPC(pos(2)))
          val instructions= inst.toList.map(c => toCommande(c))
          val newTondeuseInit = List(TondeuseInit(pelouse,position,instructions))
          tondeuseInit(rest, listTondeuse ::: newTondeuseInit,pelouse )
      }

    /*val pelouse = Pelouse(5, 5)
    val initPosition = Position(3, 3, PositionCardinale.E)
    val actions = "AAADAADADDA"
    val listActions = actions.toList.map(c => toValue(c))
    val finalPosition = new Execution().execution(listActions, initPosition, pelouse)
    println(finalPosition)*/
  }
}