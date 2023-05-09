package my.edu.tarc.ass2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import my.tarc.mycontact.*
import java.sql.Driver

@Database(entities = [User::class, Payment::class, ElectricityAcc::class, Bill::class, Appliances::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): databaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my-database"
                )
                    .apply {
                        // Set the MySQL database URL, username, and password
                        val url = "jdbc:mysql://your-mysql-server-url:3306/your-database-name"
                        val username = "your-mysql-username"
                        val password = "your-mysql-password"

                        this.driver(Driver.load())
                        this.url(url)
                        this.username(username)
                        this.password(password)
                    }
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}