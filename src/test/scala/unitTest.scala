package tondeuseGazon
import Instruction.toInstruction
import org.scalatest.funsuite.AnyFunSuite
import Instruction.{G,D,A}
import PositionCardinale.{E, N, S, W}
import scala.io.Source

class unitTest extends AnyFunSuite {
  // tests unitaires sur chaque fonction
  test("check tondeuse initiation") {
    val filename = "src/main/Resources/instructions.txt"
    val lines = Source.fromFile(filename).getLines.toList
    val Listpelouse = lines.head.split(" ").map(_.trim).toList
    val pelouse = Pelouse(Listpelouse(0).toInt,Listpelouse(1).toInt)
    val results = Execution.tondeuseInit(lines.tail,pelouse)
    val expected =List(TondeuseInit(Pelouse(5,5),Position(1,2,N),List(G,A,G,A,G,A,G,A,A)),
    TondeuseInit(Pelouse(5,5),Position(3,3,E),List(A,A,D,A,A,D,A,D,D,A)))
    assertResult(expected)(results)
  }
  test("check commands") {
    val commandeDroite = Execution.droite(N)
    val commandeGauche = Execution.gauche(N)

    assertResult(E)(commandeDroite)
    assertResult(W)(commandeGauche)
  }

  test("check avancer") {
    val initPosition = Position(1,2,N)
    val av = Execution.avancer(initPosition)

    assertResult(Position(1,3,N))(av)
  }
test("check execution"){
  val pelouse = Pelouse(5,5)
  val initPosition = Position(1,2,N)
  val actions = "GAGAGAGAA"
  val listActions = actions.toList.map(c => toInstruction(c.toString))
  val finalPosition = Execution.execution(listActions,initPosition,pelouse)
  assertResult(Position(1,3,N))(finalPosition)
}
}
