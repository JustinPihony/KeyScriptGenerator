import AssemblyKeys._

assemblySettings

name := "KeyScriptGenerator"

scalaVersion := "2.11.0"

version:= "0.1"

jarName in assembly := "KeyScriptGenerator.jar"

libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.11" % "2.2.0" % "test",
"com.novocode" % "junit-interface" % "0.9" % "test")