package TondeuseGazon

import TondeuseGazon.Commandes.toValue

object Main{
  def main(args:Array[String]):Unit={
    val pelouse = Pelouse(5, 5)
    val initPosition = Position(1, 2, PositionCardinale.N)
    val actions = "GAGAGAGAA"
    val listActions = actions.toList.map(c => toValue(c))
    val finalPosition = new Execution().execution(listActions, initPosition, pelouse)
    println(finalPosition)
  }
}