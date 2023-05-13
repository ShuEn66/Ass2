package my.edu.tarc.ass2.Profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import my.edu.tarc.ass2.AppDatabase
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databaseDao


class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private var databaseDao: databaseDao

    suspend fun setUserDetails(User: User){
        withContext(Dispatchers.IO) {
            databaseDao.setUserDetails(User)
        }
    }

    suspend fun getUserEmail(accNo:Long):String{
        return withContext(Dispatchers.IO) {
            val d = databaseDao.getUserEmail(accNo)
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



    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}


