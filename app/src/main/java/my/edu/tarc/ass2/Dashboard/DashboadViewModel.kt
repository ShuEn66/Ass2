package my.edu.tarc.ass2.Dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import my.edu.tarc.ass2.AppDatabase
import my.edu.tarc.ass2.Bill
import my.edu.tarc.ass2.ElectricityAcc
import my.edu.tarc.ass2.databaseDao


class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private var databaseDao: databaseDao

    suspend fun getOverallUsage(accNo: Long, month: Int, year: Int):Double {
             return withContext(Dispatchers.IO) {
                val d= databaseDao.getOverallUsage(accNo, month, year)
                 d
            }
        }

    suspend fun getBillStatus(accNo: Long, month: Int, year: Int): String {
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getBillStatus(accNo, month, year)
            d
            }

    }

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

    suspend fun setBillingDetails(Bill: Bill){
            withContext(Dispatchers.IO) {
                databaseDao.setBillingDetails(Bill)
        }
    }

    suspend fun getBarData(accNo: Long): List<databaseDao.billBar>{
        return withContext(Dispatchers.IO) {
            val d =databaseDao.getBarData(accNo)
            d
        }
    }


    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}


