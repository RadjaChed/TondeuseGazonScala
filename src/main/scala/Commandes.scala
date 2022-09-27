package TondeuseGazon

object Commandes extends Enumeration {
  type commande = Value
  val G,D,A= Value

  def toValue(c:Char):commande= c match{
    case 'G' => G
    case 'D' => D
    case 'A' => A
  }

}
