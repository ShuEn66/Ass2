package my.edu.tarc.ass2.Dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import my.edu.tarc.ass2.AppDatabase
import my.edu.tarc.ass2.databaseDao
import my.tarc.mycontact.Bill


class DashboardViewModel(application: Application, private var databaseDao: databaseDao) : ViewModel() {
    suspend fun getOverallUsage(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getOverallUsage(accNo, month, year)
    }

    suspend fun getBillStatus(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getBillStatus(accNo, month, year)
    }

    suspend fun getPaymentDue(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getPaymentDue(accNo, month, year)
    }

    suspend fun getTotalAmount(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getTotalAmount(accNo, month, year)
    }

    suspend fun getCurrentCharges(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getCurrentCharges(accNo, month, year)
    }

    suspend fun getOutstandingCharges(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getOutstandingCharges(accNo, month, year)
    }

    suspend fun getInvoiceDate(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getInvoiceDate(accNo, month, year)
    }

    suspend fun getOverdueCharges(accNo: Int, month: Int, year: Int): LiveData<Bill> {
        return databaseDao.getOverdueCharges(accNo, month, year)
    }

    suspend fun updateBillStatus() {
        databaseDao.updateBillStatus()
    }

    init {
        val database = AppDatabase.getInstance(application)
        databaseDao = database.databaseDao()
    }


}


