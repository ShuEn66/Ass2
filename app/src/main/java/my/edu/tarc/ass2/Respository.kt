package my.edu.tarc.ass2

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import my.tarc.mycontact.Bill

class BillRepository(private val dbDao: databaseDao){
    //Room execute all queries on a separate thread
    val b: LiveData<List<Bill>> = dbDao.getAllContact()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun add(Bill: Bill){
        dbDao.insert(Bill)
    }

    @WorkerThread
    suspend fun delete(Bill: Bill){
        dbDao.delete(BBill)
    }

    @WorkerThread
    suspend fun deleteAll(){
        dbDao.deleteAll()
    }

    @WorkerThread
    suspend fun update(Bill){
        dbDao.update(contact)
    }
}