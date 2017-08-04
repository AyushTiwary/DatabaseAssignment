package kip.knoldus.database

object DatabaseApplication extends App{

  val queryList: List[List[String]] = CSVFiles.readAndParse("/home/knoldus/IdeaProjects/DatabaseAssignment/MySQLDDL.csv")
  print(queryList)

  val con = MySQLConnection.connectToDatabase
  val time: List[Long] = queryList.map(MySQLConnection.executeQuery(con, _))
  MySQLConnection.closeConnection(con)

  val bool: Boolean = CSVFiles.writeToFile(queryList, time, "MySQLDDLoutput.csv")

  //println(bool)

  println("Time taken : "+ time )

  val query1 = CSVFiles.readAndParse("/home/knoldus/IdeaProjects/DatabaseAssignment/MySqlDML.csv")
  print(query1)

  val connection1 = MySQLConnection.connectToDatabase
  val time1: List[Long] = query1.map(MySQLConnection.executeQuery(connection1, _))
  MySQLConnection.closeConnection(connection1)

  val bool1: Boolean = CSVFiles.writeToFile(query1, time1, "MySQLDMLoutput.csv")

  //println(bool1)
  println("Time taken : "+ time1 )


  val query2 = CSVFiles.readAndParse("/home/knoldus/IdeaProjects/DatabaseAssignment/postDDL.csv")
  print(query2)

  val connection2 = PostGreSQLConnection.connectToDatabase
  val time2: List[Long] = query2.map(PostGreSQLConnection.executeQuery(connection2, _))

  PostGreSQLConnection.closeConnection(connection2)

  val bool2: Boolean = CSVFiles.writeToFile(query2, time2, "postDDLoutput.csv")

  //println(bool2)
  println("Time taken : "+ time2 )



}
