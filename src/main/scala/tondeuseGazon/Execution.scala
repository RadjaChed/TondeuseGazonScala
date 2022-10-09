package tondeuseGazon

import tondeuseGazon.Instruction.{A, D, G, instruction, toInstruction}
import tondeuseGazon.PositionCardinale.{E, N, S, W, positionCardinale, toPC}

object Execution {

  def tondeuseInit(lines: List[String], pelouse: Pelouse, listTondeuseInit: List[TondeuseInit] = List()): List[TondeuseInit] = {
    /*
    la fonction retourne l'état initial de chauqye tondeuse dans une liste TondeuseInit
    lines: List [String] => position de la tondeuse et les instructions à suivre
    pelouse: Pelouse =>  dimensions de la pelouse
    listTondeuseInit: List[TondeuseInit] => liste de tondeuses initialisées (positions et instructions de chaque tondeuse)
     */
    lines match {
      case Nil => listTondeuseInit
      case pos :: inst :: rest =>
        val p = pos.split("\\s+")
        // si la position initiale est en dehors de la pelouse, on renvoit une exception
        // sinon on initialise la tondeuse
        if (0 <= p(0).toInt
          && p(0).toInt <= pelouse.x
          && 0 <= p(1).toInt
          && p(1).toInt <= pelouse.y) {
          val position = Position(p(0).toInt, p(1).toInt, toPC(p(2)))
          val instructions = inst.toList.map(c => toInstruction(c.toString))
          val newTondeuseInit = List(TondeuseInit(pelouse, position, instructions))
          tondeuseInit(rest, pelouse, listTondeuseInit ::: newTondeuseInit)
        }
        else throw new Exception(s"Initial position out of range")

    }
  }
/*
changement de position cardinal en fonction de l'instruction
 */
  def droite(pc: positionCardinale): positionCardinale = pc match {
    case W => N
    case N => E
    case E => S
    case S => W
    case _ => throw new Exception(s"wrong cardinal position: $pc")
  }

  def gauche(pc: positionCardinale): positionCardinale = pc match {
    case W => S
    case S => E
    case E => N
    case N => W
    case _ => throw new Exception(s"wrong cardinal position: $pc")
  }

  def avancer(position: Position): Position = position.pc match {
    case W => Position(position.x - 1, position.y, position.pc)
    case S => Position(position.x, position.y - 1, position.pc)
    case E => Position(position.x + 1, position.y, position.pc)
    case N => Position(position.x, position.y + 1, position.pc)
    case _ => throw new Exception(s"wrong cardinal position: ${position.pc}")
  }

  def execution(instructions: List[instruction], position: Position, pelouse: Pelouse): Position =
  /* la fonction prend les instructions et la position en entrée et calcule la nouvelle position de la tondeuse
    instructions: List[instruction] => liste des instructions
    position: Position => position de la tondeuse
    pelouse: Pelouse => diemensions de la pelouse
     */
    instructions match {
    case Nil =>
      println(position)
      position
    case instruction :: rest => instruction match {
      case G =>
        val newPosition = Position(position.x, position.y, gauche(position.pc))
        execution(rest, newPosition, pelouse)
      case D =>
        val newPosition = Position(position.x, position.y, droite(position.pc))
        execution(rest, newPosition, pelouse)
      case A =>
        val newPosition = avancer(position)
        if (0 <= newPosition.x
          && newPosition.x <= pelouse.x
          && 0 <= newPosition.y
          && newPosition.y <= pelouse.y)
          execution(rest, newPosition, pelouse)
        else execution(rest, position, pelouse)
      case _ => throw new Exception(s"wrong instruction: $instruction")
    }

  }


}
