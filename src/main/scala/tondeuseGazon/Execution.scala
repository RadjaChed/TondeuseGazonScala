package tondeuseGazon

import tondeuseGazon.Commandes.{G,D,A, commande, toCommande}
import tondeuseGazon.PositionCardinale.{E, N, S, W, positionCardinale, toPC}

object Execution {

  def tondeuseInit(lines: List[String], pelouse: Pelouse, listTondeuseInit: List[TondeuseInit] = List()): List[TondeuseInit] =
    lines match {
      case Nil => listTondeuseInit
      case pos :: inst :: rest =>
        val p = pos.split("\\s+") // Split in white spaces
        val position = Position(p(0).toInt, p(1).toInt, toPC(p(2)))
        val instructions = inst.toList.map(c => toCommande(c.toString))
        val newTondeuseInit = List(TondeuseInit(pelouse, position, instructions))
        tondeuseInit(rest, pelouse, listTondeuseInit ::: newTondeuseInit)
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

  def execution(actions: List[commande], position: Position, pelouse: Pelouse): Position = actions match {
    case Nil =>
      println(position)
      position
    case action :: rest => action match {
      case G =>
        val newPosition = Position(position.x, position.y, gauche(position.pc))
        execution(rest, newPosition, pelouse)
      case D =>
        val newPosition = Position(position.x, position.y, droite(position.pc))
        execution(rest, newPosition, pelouse)
      case A =>
        val finalPosition = avancer(position)
        if (0 <= finalPosition.x
          && finalPosition.x <= pelouse.x
          && 0 <= finalPosition.y
          && finalPosition.y <= pelouse.y)
          execution(rest, finalPosition, pelouse)
        else execution(rest, position, pelouse)
    }

  }


}
