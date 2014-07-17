To create a console exe:
-From the console, run sbt assembly
-Download and install Launch4j
-Run Launch4j
--Output file: \Path\To\[NameYouWantExe].exe (I personally call it convert)
--Jar: [AutoHotKeyPath]\target\scala-2.11\KeyScriptGenerator.jar
--JRE/Min JRE version: 1.7.0_17
--Click on Build wrapper (Gear symbol)

To run exe (given exe is in path):
-Open console and type [NameYouWantExe] [StringToAutoHotkey] [OutputFileName (Default=output.txt)]