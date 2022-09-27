package TondeuseGazon
import TondeuseGazon.Commandes
import TondeuseGazon.Commandes.{A, D, G,commande}
import TondeuseGazon.PositionCardinale.{E, N, S, W, positionCardinale}

class Execution {

  def droite(pc: positionCardinale): positionCardinale = pc match {
    case W => N
    case N => E
    case E => S
    case S => W
  }

  def gauche(pc: positionCardinale): positionCardinale = pc match {
    case W => S
    case S => E
    case E => N
    case N => W
  }

  def avancer(position: Position): Position = position.pc match {
    case W => Position(position.x - 1, position.y, position.pc)
    case S => Position(position.x, position.y - 1, position.pc)
    case E => Position(position.x + 1, position.y, position.pc)
    case N => Position(position.x, position.y + 1, position.pc)
  }

  def execution(actions:List[commande], position:Position, pelouse: Pelouse):Position = actions match {
    case Nil =>
      println(f"Nil:$actions")
      position
    case action :: rest => action match{
      case G =>
        println(f"G:$actions")
        println(position)
        val newPosition = Position(position.x, position.y, gauche(position.pc))
        execution(rest, newPosition, pelouse)
      case D =>
        println(f"D:$actions")
        println(position)
        val newPosition = Position(position.x, position.y, droite(position.pc))
        execution(rest, newPosition, pelouse)
      case A =>
        println(f"A:$actions")
        println(position)
        val finalPosition = avancer(position)
        if (finalPosition.x <= pelouse.x && finalPosition.y <= pelouse.y) execution(rest, finalPosition, pelouse)
        else execution(rest, position, pelouse)
    }

  }



}
