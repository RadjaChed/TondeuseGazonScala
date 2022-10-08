package tondeuseGazon

object Instruction extends Enumeration {
  type instruction = Value
  val G, D, A = Value

  def toInstruction(c: String): instruction = c match {
    case "G" => G
    case "D" => D
    case "A" => A
    case _ => throw new Exception(s"wrong instruction: $c")
  }

}
