package my.edu.tarc.ass2.Profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import my.edu.tarc.ass2.*


class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private var databaseDao: databaseDao

    suspend fun setUserDetails(User: User){
        withContext(Dispatchers.IO) {
            databaseDao.setUserDetails(User)
        }
    }

    suspend fun updateUserName(email:String,newName:String){
        withContext(Dispatchers.IO) {
            databaseDao.updateUserName(email,newName)
        }
    }

    suspend fun updateUserIC(email:String,newIC:String){
        withContext(Dispatchers.IO) {
            databaseDao.updateUserIC(email,newIC)
        }
    }

    suspend fun updateUserMobile(email:String,newMobile:String){
        withContext(Dispatchers.IO) {
            databaseDao.updateUserMobile(email,newMobile)
        }
    }

    suspend fun updateUserPassword(email:String,newPassword:String){
        withContext(Dispatchers.IO) {
            databaseDao.updateUserPassword(email,newPassword)
        }
    }

    suspend fun updateAccName(email:String,newNickName:String){
        withContext(Dispatchers.IO) {
            databaseDao.updateAccName(email,newNickName)
        }
    }

    suspend fun updateAccNum(email:String,newAccNumber:Long){
        withContext(Dispatchers.IO) {
            databaseDao.updateAccNum(email,newAccNumber)
        }
    }



    suspend fun updateNoResident(email:String,newNo:Int){
        withContext(Dispatchers.IO) {
            databaseDao.updateNoResident(email,newNo)
        }
    }

    suspend fun updateMonthlyIncome(email:String,newIncome:Double){
        withContext(Dispatchers.IO) {
            databaseDao.updateMonthlyIncome(email,newIncome)
        }
    }

    suspend fun getUserbyEmail(email:String):User{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUserbyEmail(email)
            d
        }
    }

    suspend fun getUserEmail(accNo:Long):String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUserEmail(accNo)
            d
        }
    }

    suspend fun getUserPassword(email:String):String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUserPassword(email)
            d
        }
    }

    suspend fun getUserName(email:String):String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUserName(email)
            d
        }
    }

    suspend fun getUserIC(email:String):String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUserIC(email)
            d
        }
    }

    suspend fun getUserMobile(email:String):String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUserMobile(email)
            d
        }
    }

    suspend fun getAccNickName(email: String): String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getAccNickName(email)
            d
        }
    }

    suspend fun getNoResident(email: String): Int{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getNoResident(email)
            d
        }
    }

    suspend fun getMonthlyIncome(email: String): Double{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getMonthlyIncome(email)
            d
        }
    }

    suspend fun getAccNumber(email: String): Long{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getAccNumber(email)
            d
        }
    }

    suspend fun getUser(accNo: Long): User{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUser(accNo)
            d
        }
    }

    suspend fun deleteUser(email: String){
        return withContext(Dispatchers.IO) {
            val d = databaseDao.deleteUser(email)
            d
        }
    }

    suspend fun setAccDetails(ElectricityAcc: ElectricityAcc){
        withContext(Dispatchers.IO) {
            databaseDao.setAccDetails(ElectricityAcc)
        }
    }


    suspend fun getAccountProperty(accNo: Long): String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getAccountProperty(accNo)
            d
        }
    }

    suspend fun getAccountAddress(accNo: Long): String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getAccountAddress(accNo)
            d
        }
    }

    suspend fun getAccountOwner(accNo: Long): String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getAccountOwner(accNo)
            d
        }
    }

    suspend fun getAccount(accNo: Long): ElectricityAcc{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getAccount(accNo)
            d
        }
    }

    suspend fun InitializeCurrent(CurrentUser:CurrentUser){
        withContext(Dispatchers.IO) {
            databaseDao.InitializeCurrent(CurrentUser)
        }
    }

    suspend fun setCurrentUser(accNo: Long,email: String){
        withContext(Dispatchers.IO) {
            databaseDao.setCurrentUser(accNo,email)
        }
    }

    suspend fun setCurrentEmail(email: String){
        withContext(Dispatchers.IO) {
            databaseDao.setCurrentEmail(email)
        }
    }

    suspend fun getCurrentUser(): CurrentUser{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getCurrentUser()
            d
        }
    }

    suspend fun getCurrentEmail(): String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getCurrentEmail()
            d
        }
    }

    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}


