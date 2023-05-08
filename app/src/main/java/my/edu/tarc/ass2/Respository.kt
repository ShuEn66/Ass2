package my.edu.tarc.ass2

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ContactRepository(private val dbDao: databaseDao){
    //Room execute all queries on a separate thread
    val allContacts: LiveData<List<Contact>> = dbDao.getAllContact()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun add(contact: Contact){
        dbDao.insert(contact)
    }

    @WorkerThread
    suspend fun delete(contact: Contact){
        dbDao.delete(contact)
    }

    @WorkerThread
    suspend fun deleteAll(){
        dbDao.deleteAll()
    }

    @WorkerThread
    suspend fun update(contact: Contact){
        dbDao.update(contact)
    }
}