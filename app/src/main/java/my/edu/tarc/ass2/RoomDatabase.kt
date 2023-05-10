package my.edu.tarc.ass2.Dashboard

import androidx.room.Database
import androidx.room.RoomDatabase
import my.edu.tarc.ass2.databaseDao
import my.tarc.mycontact.*

@Database(entities = [User::class, Payment::class, ElectricityAcc::class, Bill::class, Appliances::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): databaseDao
}