package kip.knoldus.database

object SQLiteConnection extends DatabaseConnection {

  val url = "jdbc:sqlite:/home/knoldus/test.db"
  val driver = "org.sqlite.JDBC"

}
