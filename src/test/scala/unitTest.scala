package TondeuseGazon
import TondeuseGazon.Commandes.toValue
import TondeuseGazon.Execution
import org.scalatest.funsuite.AnyFunSuite

class unitTest extends AnyFunSuite {
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
  val listActions = actions.toList.map(c => toValue(c))
  val finalPosition = new Execution().execution(listActions,initPosition,pelouse)
  assertResult(Position(1,3,PositionCardinale.N))(finalPosition)
}
}
