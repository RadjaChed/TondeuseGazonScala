package TondeuseGazon
import TondeuseGazon.Commandes.{A, D, G, commande, toCommande}
import TondeuseGazon.PositionCardinale.{E, N, S, W, positionCardinale, toPC}

class Execution {

  def tondeuseInit(lines: List[String], listTondeuse: List[TondeuseInit], pelouse: Pelouse): List[TondeuseInit] =
    lines match {
      case Nil => listTondeuse
      case pos :: inst :: rest =>
        val position = Position(pos(0), pos(1), toPC(pos(2)))
        val instructions = inst.toList.map(c => toCommande(c))
        val newTondeuseInit = List(TondeuseInit(pelouse, position, instructions))
        tondeuseInit(rest, listTondeuse ::: newTondeuseInit, pelouse)
    }
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
        if ( 0 <= finalPosition.x
          && finalPosition.x <= pelouse.x
          && 0<= finalPosition.y
          && finalPosition.y <= pelouse.y)
            execution(rest, finalPosition, pelouse)
        else execution(rest, position, pelouse)
    }

  }



}
