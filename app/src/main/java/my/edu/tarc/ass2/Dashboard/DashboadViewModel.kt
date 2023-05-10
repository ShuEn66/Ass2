package my.edu.tarc.ass2.Dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.databaseDao
import my.tarc.mycontact.Bill


class DashboardViewModel(application: Application, private var databaseDao: databaseDao) : ViewModel() {
    suspend fun getOverallUsage(accNo: Int, month: Int, year: Int) {
        viewModelScope.launch { databaseDao.getOverallUsage(accNo, month, year)}
    }

    suspend fun getBillStatus(accNo: Int, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getBillStatus(accNo, month, year)}
    }

    suspend fun getPaymentDue(accNo: Int, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getPaymentDue(accNo, month, year)}
    }

    suspend fun getTotalAmount(accNo: Int, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getTotalAmount(accNo, month, year)}
    }

    suspend fun getCurrentCharges(accNo: Int, month: Int, year: Int){
        viewModelScope.launch {databaseDao.getCurrentCharges(accNo, month, year)}
    }

    suspend fun getOutstandingCharges(accNo: Int, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getOutstandingCharges(accNo, month, year)}
    }

    suspend fun setBillingDetails(Bill: Bill){
        viewModelScope.launch {databaseDao.setBillingDetails(Bill)}
    }

    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}


