
package TondeuseGazon

object PositionCardinale extends Enumeration {
  type positionCardinale = Value
  val N,E,W,S= Value

  def toPC(c: Char): positionCardinale = c match {
    case 'N' => N
    case 'E' => E
    case 'W' => W
    case 'S' => S
  }
}
