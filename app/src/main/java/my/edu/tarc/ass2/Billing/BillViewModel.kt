package my.edu.tarc.ass2.Billing

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
import my.edu.tarc.ass2.Payment
import my.edu.tarc.ass2.databaseDao

class BillViewModel(application: Application) : AndroidViewModel(application) {
    private var databaseDao: databaseDao

    suspend fun getBillStatus(accNo: Long, month: Int, year: Int): String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getBillStatus(accNo, month, year)
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
            val d = databaseDao.getTotalAmount(accNo, month, year) as Number?
            d?.toDouble() ?: 0.0
        }
    }

    suspend fun getCurrentCharges(accNo: Long, month: Int, year: Int): Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getCurrentCharges(accNo, month, year) as Number?
            d?.toDouble() ?: 0.0
        }
    }

    suspend fun getOutstandingCharges(accNo: Long, month: Int, year: Int): Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getOutstandingCharges(accNo, month, year) as Number?
            d?.toDouble() ?: 0.0
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
            val d = databaseDao.getOverdueCharges(accNo, month, year) as Number?
            d?.toDouble() ?: 0.0
        }
    }

    suspend fun getPaymentDate(paymentId: Long):String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getPaymentDate(paymentId)
            d
        }
    }

    suspend fun getPaymentStatus(paymentId: Long):String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getPaymentStatus(paymentId)
            d
        }
    }

    suspend fun getPaymentMethod(paymentId: Long):String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getPaymentMethod(paymentId)
            d
        }
    }

    suspend fun getOverallUsage(accNo: Long,  month: Int,  year:Int):Double {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getOverallUsage(accNo, month, year)
            d
        }
    }

    suspend fun getBillID(accNo: Long,  month: Int,  year:Int):String {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getBillID(accNo, month, year)
            d
        }
    }

    suspend fun updateBillStatus(billId: String){
        return withContext(Dispatchers.IO) {
            val d = databaseDao.updateBillStatus(billId)
            d
        }
    }

    suspend fun setPaymentDetails(Payment: Payment){
        withContext(Dispatchers.IO) {
            databaseDao.setPaymentDetails(Payment)
        }
    }

    suspend fun updateCurrentChanges(){
        withContext(Dispatchers.IO) {
            databaseDao.updateCurrentChanges()
        }
    }

    suspend fun updateOutstandingChanges(){
        withContext(Dispatchers.IO) {
            databaseDao.updateOutstandingChanges()
        }
    }

    suspend fun updateTotalAmount(){
                withContext(Dispatchers.IO) {
                    databaseDao.updateTotalAmount()
                }
    }

    suspend fun getAllBill(accNo: Long): LiveData<List<Bill>> {
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getAllBill(accNo)
            d
        }
    }


    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}