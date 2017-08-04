package kip.knoldus.database


object MySQLConnection extends DatabaseConnection {

  val url = "jdbc:mysql://localhost:3306/test"
  val driver = "com.mysql.cj.jdbc.Driver"
  override val username = "root"
  override val password = "password"

}
