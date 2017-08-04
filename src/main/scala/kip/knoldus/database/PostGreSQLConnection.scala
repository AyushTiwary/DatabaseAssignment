package kip.knoldus.database

object PostGreSQLConnection extends DatabaseConnection {

  val url = "jdbc:postgresql://localhost:5432/test"
  val driver = "org.postgresql.Driver"
  override val username = "postgres"
  override val password = "password"

}
