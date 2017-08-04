package kip.knoldus.database
import java.io.{File, PrintWriter}
import scala.io.Source

object CSVFiles {

  def parseCSV(line: String): List[String] = {

    def parse(line: String, list: List[String],value: String, count: Int, len: Int): List[String] = {

      if(len == line.length)
      {
        list ::: List(value)
      }
      else {
        val character = line(len)

        if(character != ','){
          if(character != '"') {
            parse(line, list, value + character, count, len + 1)
          }
          else {
            parse(line,list, value, count + 1, len + 1)
          }
        }
        else {
          if(count % 2 == 0) {
            parse(line, list ::: List(value), "", count, len + 1)
          }
          else {
            parse(line, list, value + character, count, len + 1)
          }
        }
      }

    }
    parse(line, Nil, "", 0, 0)
  }

  def readFile(file: String): List[String] ={

    val source: File = new File(file)
    val content: String = Source.fromFile(source).getLines.mkString("\n")

    content.split("\n").toList
  }

  def readAndParse(file: String): List[List[String]] ={

    val lines: List[String] = readFile(file)
    val queryList: List[List[String]] = lines.map(l => parseCSV(l))
    queryList
  }

  def writeToFile(content: List[List[String]], time: List[Long], fileName: String): Boolean = {

    val Id: List[String]  = content.map(_.head)
    val description: List[String] = content.map(_(1))
    val query: List[String] = content.map(_(3))
    val timeList: List[String] = time.map(_.toString)

    val outputList: List[String] = Id zip description zip query zip timeList map {
      case (((a,b),c),d) => a + ", " + b + ", " + "\"" + c + "\", " + d
    }

    println("\n " + outputList.mkString("\n"))

    val writeToFile = new PrintWriter("/home/knoldus/IdeaProjects/DatabaseAssignment/" + fileName)

    try {

      writeToFile.write(outputList.mkString("\n"))
      writeToFile.close()
      true

    }

    catch {

      case e: Exception => false

    }
  }


}
