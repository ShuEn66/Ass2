package my.edu.tarc.ass2
import java.sql.*

fun main() {
    val url = "jdbc:mysql://guardianoflighting.000webhostapp.com:3306/id20710696_gol_database"
    val username = "guardianoflighting"
    val password = "7XWD]P7i^)OhI4~#"

    var connection: Connection? = null
    try {
        Class.forName("com.mysql.jdbc.Driver")
        connection = DriverManager.getConnection(url, username, password)
        println("Connected to database.")
    } catch (e: SQLException) {
        println("Failed to connect to database.")
        e.printStackTrace()
    }
    connection?.close()
}