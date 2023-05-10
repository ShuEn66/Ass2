package my.edu.tarc.ass2.Dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.databaseDao
import my.tarc.mycontact.Bill


class DashboardViewModel(application: Application, private var databaseDao: databaseDao) : ViewModel() {
    fun getOverallUsage(accNo: Long, month: Int, year: Int) {
        viewModelScope.launch { databaseDao.getOverallUsage(accNo, month, year)}
    }

    fun getBillStatus(accNo: Long, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getBillStatus(accNo, month, year)}
    }

    fun getPaymentDue(accNo:Long, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getPaymentDue(accNo, month, year)}
    }

    fun getTotalAmount(accNo: Long, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getTotalAmount(accNo, month, year)}
    }

    fun getCurrentCharges(accNo: Long, month: Int, year: Int){
        viewModelScope.launch {databaseDao.getCurrentCharges(accNo, month, year)}
    }

    fun getOutstandingCharges(accNo: Long, month: Int, year: Int) {
        viewModelScope.launch {databaseDao.getOutstandingCharges(accNo, month, year)}
    }

    fun setBillingDetails(Bill: Bill){
        viewModelScope.launch {databaseDao.setBillingDetails(Bill)}
    }

    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}


