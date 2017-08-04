package kip.knoldus.database

import java.sql.{Connection, DriverManager, SQLException, Statement}
import java.util.concurrent.TimeUnit

import scala.concurrent.duration.TimeUnit

trait DatabaseConnection {

  val driver: String
  val url: String
  val username: String = ""
  val password: String = ""

  def connectToDatabase: Connection = {
    try {
      // make the connection
      Class.forName(driver)
      val connection: Connection = DriverManager.getConnection(url, username, password)
      connection
    }
    catch{
      case e: ClassNotFoundException => throw new ClassNotFoundException
      case e: SQLException => throw new SQLException
    }
  }

 def executeQuery(connection: Connection, queryList: List[String]): Long = {

    val statement: Statement = connection.createStatement()

    statement.execute(queryList(2))
    val start = System.nanoTime()
    statement.execute(queryList(3))
    val end = System.nanoTime()
    statement.execute(queryList(4))
    statement.close

    end - start

  }

  def closeConnection(connection: Connection): Unit = {
    connection.close()
  }

}
