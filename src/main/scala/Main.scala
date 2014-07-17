package converter

object Main extends App{
  def writeToFile(path : String, results : List[String]) = {
    import java.io._
    val file = new File(path)
    val printwriter = new PrintWriter(file)
    try results foreach(printwriter.print(_))
    finally printwriter.close()
  }
  
  try{
    val results = args match {
      case Array(stringToConvert) =>  AutoHotKeyLineGenerator getLinesFrom stringToConvert
    }
  
    val outputPath = args match {
      case Array(_,outputPath,_*) => outputPath
	  case _ => "output.txt"
    }
	
	writeToFile(outputPath, results toList)
  }
  catch{
    case exception : Throwable => writeToFile("error.txt", List(s"Exception Type:$exception; Exception Message: ${exception.getMessage()}"))
  }

}