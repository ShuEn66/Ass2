package my.edu.tarc.ass2.Billing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import my.edu.tarc.ass2.AppDatabase
import my.edu.tarc.ass2.Bill
import my.edu.tarc.ass2.databaseDao

class BillViewModel(application: Application) : AndroidViewModel(application) {
    private var databaseDao: databaseDao

    suspend fun getPaymentDue(accNo:Long, month: Int, year: Int): String {
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getPaymentDue(accNo, month, year)
            d
        }
    }

    suspend fun getTotalAmount(accNo: Long, month: Int, year: Int): Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getTotalAmount(accNo, month, year)
            d
        }
    }

    suspend fun getCurrentCharges(accNo: Long, month: Int, year: Int): Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getCurrentCharges(accNo, month, year)
            d
        }
    }

    suspend fun getOutstandingCharges(accNo: Long, month: Int, year: Int): Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getOutstandingCharges(accNo, month, year)
            d
        }
    }

    suspend fun getInvoiceDate(accNo: Long, month: Int, year: Int): String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getInvoiceDate(accNo, month, year)
            d
        }
    }

    suspend fun getOverdueCharges(accNo: Long,  month: Int,  year:Int):Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getOverdueCharges(accNo, month, year)
            d
        }
    }

    suspend fun getPaymentDate(accNo: Long,  month: Int,  year:Int):String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getPaymentDate(accNo, month, year)
            d
        }
    }

    suspend fun getPaymentStatus(accNo: Long,  month: Int,  year:Int):String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getPaymentStatus(accNo, month, year)
            d
        }
    }

    suspend fun getPaymentMethod(accNo: Long,  month: Int,  year:Int):String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getPaymentMethod(accNo, month, year)
            d
        }
    }

    suspend fun getOverallUsage(accNo: Long,  month: Int,  year:Int):Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getOverallUsage(accNo, month, year)
            d
        }
    }

    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}