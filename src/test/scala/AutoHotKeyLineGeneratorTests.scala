package converter

import org.scalatest.FlatSpec
import scala.util.matching.Regex.Match

class AutoHotKeyLineGeneratorTests extends FlatSpec{
  val sleepLine = AutoHotKeyLineGenerator sleepLine
  
  "getLinesFrom one regular character " should 
  "return that character wrapped in a send" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("a")
	assert(generatedLines.head == s"send {a}\n$sleepLine")
  }
  
  "getLinesFrom two regular characters " should 
  "return each character wrapped in a send" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("ab")
	assert(generatedLines == List(s"send {a}\n$sleepLine", s"send {b}\n$sleepLine"))
  }
  
  "getLinesFrom one regular character and a three multiplier (*3) " should 
  "return that character wrapped in a send three times" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("a*3")
	assert(generatedLines.head == s"send {a}\n$sleepLine"*3)
  }
  
  "getLinesFrom ctrl+a " should 
  "return a send ^{a}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("ctrl+a")
	assert(generatedLines.head == s"send ^{a}\n$sleepLine")
  }
  
  "getLinesFrom shift+a " should 
  "return a send +{a}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("shift+a")
	assert(generatedLines.head == s"send +{a}\n$sleepLine")
  }
  
  "getLinesFrom alt+a " should 
  "return a send !{a}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("alt+a")
	assert(generatedLines.head == s"send !{a}\n$sleepLine")
  }
  
  "getLinesFrom a modifier and a two multiplier (*2) " should 
  "return that modifier line two times" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("alt+a*2")
	assert(generatedLines.head == s"send !{a}\n$sleepLine"*2)
  }
  
  "getLinesFrom a blank space " should 
  "return a send{space}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom (" ")
	assert(generatedLines.head == s"send {space}\n$sleepLine")
  }
  
  "getLinesFrom a blank space with a five multiplier (*5) " should 
  "return a send{space} five times" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom (" *5")
	assert(generatedLines.head == s"send {space}\n$sleepLine"*5)
  }
  
  "getLinesFrom a \\r " should 
  "return a send{Enter}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("\r")
	assert(generatedLines.head == s"send {Enter}\n$sleepLine")
  }
  
  "getLinesFrom a \\r with a five multiplier (*5) " should 
  "return a send{Enter} five times" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("\r*5")
	assert(generatedLines.head == s"send {Enter}\n$sleepLine"*5)
  }
  
  "getLinesFrom a \\n " should 
  "return a send{Enter}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("\n")
	assert(generatedLines.head == s"send {Enter}\n$sleepLine")
  }
  
  "getLinesFrom a \\n with a five multiplier (*5) " should 
  "return a send{Enter} five times" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("\n*5")
	assert(generatedLines.head == s"send {Enter}\n$sleepLine"*5)
  }

  "getLinesFrom a F1 " should 
  "return a send{F1}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("F1")
	assert(generatedLines.head == s"send {F1}\n$sleepLine")
  }
  
  "getLinesFrom a F12 " should 
  "return a send{F12}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("F12")
	assert(generatedLines.head == s"send {F12}\n$sleepLine")
  }
  
  "getLinesFrom an up " should 
  "return a send{up}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("up")
	assert(generatedLines.head == s"send {up}\n$sleepLine")
  }
  
  "getLinesFrom a down " should 
  "return a send{down}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("down")
	assert(generatedLines.head == s"send {down}\n$sleepLine")
  }
  
  "getLinesFrom a left " should 
  "return a send{left}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("left")
	assert(generatedLines.head == s"send {left}\n$sleepLine")
  }
  
  "getLinesFrom a right " should 
  "return a send{right}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("right")
	assert(generatedLines.head == s"send {right}\n$sleepLine")
  }
  
  "getLinesFrom a home " should 
  "return a send{home}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("home")
	assert(generatedLines.head == s"send {home}\n$sleepLine")
  }
  
  "getLinesFrom a end " should 
  "return a send{end}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("end")
	assert(generatedLines.head == s"send {end}\n$sleepLine")
  }
  
  "getLinesFrom a pgup " should 
  "return a send{pgup}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("pgup")
	assert(generatedLines.head == s"send {pgup}\n$sleepLine")
  }
  
  "getLinesFrom a pgdn " should 
  "return a send{pgdn}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("pgdn")
	assert(generatedLines.head == s"send {pgdn}\n$sleepLine")
  }
  
  "getLinesFrom a enter " should 
  "return a send{enter}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("enter")
	assert(generatedLines.head == s"send {enter}\n$sleepLine")
  }
  
  "getLinesFrom a esc " should 
  "return a send{esc}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("esc")
	assert(generatedLines.head == s"send {esc}\n$sleepLine")
  }
  
  "getLinesFrom a space " should 
  "return a send{space}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("space")
	assert(generatedLines.head == s"send {space}\n$sleepLine")
  }
  
  "getLinesFrom a tab " should 
  "return a send{tab}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("tab")
	assert(generatedLines.head == s"send {tab}\n$sleepLine")
  }
  
  "getLinesFrom a backspace " should 
  "return a send{backspace}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("backspace")
	assert(generatedLines.head == s"send {backspace}\n$sleepLine")
  }
  
  "getLinesFrom a bs " should 
  "return a send{bs}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("bs")
	assert(generatedLines.head == s"send {bs}\n$sleepLine")
  }
  
  "getLinesFrom a del " should 
  "return a send{del}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("del")
	assert(generatedLines.head == s"send {del}\n$sleepLine")
  }
  
  "getLinesFrom a ins " should 
  "return a send{ins}" in {
    val generatedLines =  AutoHotKeyLineGenerator getLinesFrom ("ins")
	assert(generatedLines.head == s"send {ins}\n$sleepLine")
  }
}