package my.edu.tarc.ass2

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface databaseDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAllContact(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Query("DELETE FROM contact")
    suspend fun deleteAll()
}