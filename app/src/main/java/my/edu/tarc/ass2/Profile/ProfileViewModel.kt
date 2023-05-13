package my.edu.tarc.ass2.Profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}


