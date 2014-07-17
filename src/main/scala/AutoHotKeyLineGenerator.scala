package converter

import scala.util.matching.Regex._

object AutoHotKeyLineGenerator{
  val parser =  "(F\\d{1,2}|up|down|left|right|home|end|pgup|pgdn|enter|esc|space|tab|backspace|bs|del|ins|\\s)(\\*\\d)?|(ctrl|alt|shift)\\+(F.|.)(\\*\\d)?|(.)(\\*\\d)?".r
  
  val sleepLine = "sleep 80\n"
	
  def getLinesFrom(stringToConvert : String) : List[String] = parser findAllMatchIn stringToConvert map toLine toList
	
  def toLine(matchedValue : Match) : String = {
    val (fullStringMatch, multiplierString) = matchedValue match { 
	  case parser(matched, multiplierString, null, null, null, null, null) => (matched, multiplierString)
	  case parser(null, null, modifier, matched, multiplierString, null, null) => (s"$modifier+$matched", multiplierString)
	  case parser(null, null, null, null, null, matched, multiplierString) => (matched, multiplierString)
	  case _ => throw new RuntimeException(s"invalid parser combination $matchedValue")
	}
	toLine(fullStringMatch, getMultiplier(multiplierString))
  }
	
  def getMultiplier(multiplierMatch : String) = Option(multiplierMatch) match { 
    case Some(multiplier) => removeAsteriskFromMultiplier(multiplier)
	case None => 1
  }
  
  def removeAsteriskFromMultiplier(multiplierString : String) = multiplierString substring 1 toInt
	
  def toLine(matchedString : String, multiplier : Int) : String = {
    val hotKeyLine = matchedString match {
	  case _ if containsModifier(matchedString) => toModifierLine(matchedString)
	  case " " => "send {space}"
	  case "\r" => "send {Enter}"
	  case "\n" => "send {Enter}"
	  case _ => s"send {$matchedString}"
	}
	s"$hotKeyLine\n$sleepLine" * multiplier
  }  
 
  def containsModifier(combo : String) = !(("(ctrl|alt|shift)\\+.*".r findFirstMatchIn combo) isEmpty)
  
  def toModifierLine(combo : String) = "(ctrl|alt|shift)\\+(F.|.)".r findFirstMatchIn combo match {
    case Some(comboMatch) => comboMatch subgroups match {
	  case List() => throw new RuntimeException(s"Empty - no match found for key combo -> $combo")
	  case "ctrl" :: rest => s"send ^{${listToTrueString(rest)}}"
	  case "alt" :: rest => s"send !{${listToTrueString(rest)}}"
	  case "shift" :: rest => s"send +{${listToTrueString(rest)}}"
	  case _ => throw new RuntimeException(s"Default - no match found for key combo -> $combo")
	}
    case None => throw new RuntimeException(s"None - no match found for key combo -> $combo")
  }
  
  def listToTrueString(list : List[String]) = list.foldLeft("")(_+_)
}