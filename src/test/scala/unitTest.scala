package TondeuseGazon
import TondeuseGazon.Commandes.toCommande
import TondeuseGazon.Execution
import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class unitTest extends AnyFunSuite {
  test("check tondeuse initiation") {
    val filename = "src/main/Resources/instructions.txt"
    val lines = Source.fromFile(filename).getLines.toList
    val Listpelouse = lines.head.split(" ").map(_.trim).toList
    val pelouse = Pelouse(Listpelouse(0).toInt,Listpelouse(1).toInt)
    val results = new Execution().tondeuseInit(lines.tail,List[TondeuseInit](),pelouse)
    assertResult(PositionCardinale.W)(results)
  }
  test("check commands") {
    val commandeDroite = new Execution().droite(PositionCardinale.N)
    val commandeGauche = new Execution().gauche(PositionCardinale.N)

    assertResult(PositionCardinale.E)(commandeDroite)
    assertResult(PositionCardinale.W)(commandeGauche)
  }

  test("check avancer") {
    val initPosition = Position(1,2,PositionCardinale.N)
    val av = new Execution().avancer(initPosition)

    assertResult(Position(1,3,PositionCardinale.N))(av)
  }
test("check execution"){
  val pelouse = Pelouse(5,5)
  val initPosition = Position(1,2,PositionCardinale.N)
  val actions = "GAGAGAGAA"
  val listActions = actions.toList.map(c => toCommande(c))
  val finalPosition = new Execution().execution(listActions,initPosition,pelouse)
  assertResult(Position(1,3,PositionCardinale.N))(finalPosition)
}
}
