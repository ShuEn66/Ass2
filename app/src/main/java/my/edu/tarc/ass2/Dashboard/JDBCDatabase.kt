//package my.edu.tarc.ass2.Dashboard
//
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.sql.Connection
//import java.sql.DriverManager
//import java.sql.SQLException
//import com.mysql.cj.jdbc.Driver
//
//class JDBCDatabase(private val url: String, private val username: String, private val password: String) {
//    private lateinit var connection: Connection
//
//    init {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver")
//        } catch (e: ClassNotFoundException) {
//            e.printStackTrace()
//        }
//    }
//
//    suspend fun connect() {
//        withContext(Dispatchers.IO) {
//            try {
//                connection = DriverManager.getConnection(url, username, password)
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//
//    suspend fun disconnect() {
//        withContext(Dispatchers.IO) {
//            try {
//                if (::connection.isInitialized && !connection.isClosed) {
//                    connection.close()
//                }
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    suspend fun performSelectQueryBill( column: String,accNo: Long, month: Int,  year:Int ): Any? {
//        return withContext(Dispatchers.IO) {
//            var results: Any? = null
//            connect()
//            //val statement = connection.createStatement()
//            val query = "SELECT ? FROM Bill WHERE AccNumber = ? AND BillingMonth = ? AND BillingYear = ?;"
//            val preparedStatement = connection.prepareStatement(query)
//            preparedStatement.setString(1, column)
//            preparedStatement.setLong(2, accNo)
//            preparedStatement.setInt(3, month)
//            preparedStatement.setInt(4, year)
//
//
//            val resultSet = preparedStatement.executeQuery()
//            while (resultSet.next()) {
//                results = resultSet.getObject(column)
//            }
//
//            resultSet.close()
//            preparedStatement.close()
//            disconnect()
//
//            results
//        }
//
//    }
//
//    suspend fun performSelectAllQuery() {
//        withContext(Dispatchers.IO) {
//            val statement = connection.createStatement()
//            val query = "SELECT * FROM your_table_name"
//            val resultSet = statement.executeQuery(query)
//
//            while (resultSet.next()) {
//                val id = resultSet.getInt("id")
//                val name = resultSet.getString("name")
//                // Retrieve other columns as needed
//                println("ID: $id, Name: $name")
//            }
//
//            resultSet.close()
//            statement.close()
//        }
//    }
//
//    fun performInsertQuery() {
//        // Perform insert query here using the connection
//        val query = "INSERT INTO ? (column1, column2) VALUES (?, ?)"
//        val preparedStatement = connection.prepareStatement(query)
//
//        preparedStatement.setString(1, "wut")
//        preparedStatement.setInt(2, 123)
//// Set other parameters as needed
//
//        val rowsAffected = preparedStatement.executeUpdate()
//
//        preparedStatement.close()
//    }
//
//    fun performUpdateQuery() {
//        // Perform update query here using the connection
//        val query = "UPDATE your_table_name SET column1 = ?, column2 = ? WHERE id = ?"
//        val preparedStatement = connection.prepareStatement(query)
//
//        preparedStatement.setString(1, "new_value1")
//        preparedStatement.setInt(2, 456)
//        preparedStatement.setInt(3, 1) // Assuming 'id' is the primary key
//// Set other parameters as needed
//
//        val rowsAffected = preparedStatement.executeUpdate()
//
//        preparedStatement.close()
//    }
//
//    fun performDeleteQuery() {
//        // Perform delete query here using the connection
//        val query = "DELETE FROM your_table_name WHERE id = ?"
//        val preparedStatement = connection.prepareStatement(query)
//
//        preparedStatement.setInt(1, 1) // Assuming 'id' is the primary key
//// Set other parameters as needed
//
//        val rowsAffected = preparedStatement.executeUpdate()
//
//        preparedStatement.close()
//    }
//}